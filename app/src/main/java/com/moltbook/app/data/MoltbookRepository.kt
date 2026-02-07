package com.moltbook.app.data

import android.content.Context
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MoltbookRepository(
    context: Context,
    private val api: MoltbookApi = MoltbookApi(),
    private val json: Json = Json { ignoreUnknownKeys = true }
) {
    private val mutex = Mutex()
    private var cachedHomepage: HomepageResponse? = null
    private val authorSlugCache = mutableMapOf<String, String>()
    private val prefs = context.getSharedPreferences("moltbook_cache", Context.MODE_PRIVATE)
    private val prefsStats = context.getSharedPreferences("moltbook_stats", Context.MODE_PRIVATE)
    private val prefsSearch = context.getSharedPreferences("moltbook_search", Context.MODE_PRIVATE)

    fun addKnownAgents(names: Iterable<String>) {
        val cleaned = names.mapNotNull { it.trim().takeIf { s -> s.isNotBlank() } }
        if (cleaned.isEmpty()) return
        val current = prefsSearch.getStringSet("known_agents", emptySet()).orEmpty().toMutableSet()
        current.addAll(cleaned)
        // Keep it bounded.
        val bounded = current.take(500).toSet()
        prefsSearch.edit().putStringSet("known_agents", bounded).apply()
    }

    fun addKnownSubmolts(names: Iterable<String>) {
        val cleaned = names.mapNotNull { it.trim().takeIf { s -> s.isNotBlank() } }
        if (cleaned.isEmpty()) return
        val current = prefsSearch.getStringSet("known_submolts", emptySet()).orEmpty().toMutableSet()
        current.addAll(cleaned)
        val bounded = current.take(500).toSet()
        prefsSearch.edit().putStringSet("known_submolts", bounded).apply()
    }

    fun getKnownAgents(): Set<String> = prefsSearch.getStringSet("known_agents", emptySet()).orEmpty()
    fun getKnownSubmolts(): Set<String> = prefsSearch.getStringSet("known_submolts", emptySet()).orEmpty()

    fun getCachedHomepage(): HomepageResponse? {
        if (cachedHomepage != null) return cachedHomepage
        val raw = prefs.getString("homepage", null) ?: return null
        return runCatching { json.decodeFromString(HomepageResponse.serializer(), raw) }.getOrNull()
    }

    fun getCachedPosts(sort: String): PostsResponse? {
        val raw = prefs.getString("posts_$sort", null) ?: return null
        return runCatching { json.decodeFromString(PostsResponse.serializer(), raw) }.getOrNull()
    }

    suspend fun getHomepage(forceRefresh: Boolean = false): HomepageResponse {
        return mutex.withLock {
            if (!forceRefresh && cachedHomepage != null) {
                return cachedHomepage!!
            }
            val fresh = api.fetchHomepage()
            cachedHomepage = fresh
            prefs.edit().putString("homepage", json.encodeToString(fresh)).apply()
            fresh
        }
    }

    suspend fun getPosts(sort: String, limit: Int, offset: Int? = null): PostsResponse {
        val fresh = api.fetchPosts(sort = sort, limit = limit, offset = offset)
        prefs.edit().putString("posts_$sort", json.encodeToString(fresh)).apply()
        return fresh
    }

    suspend fun getPostsByAuthor(author: String, limit: Int = 20): PostsResponse {
        return api.fetchPostsByAuthor(author, limit)
    }

    suspend fun getPostsByAuthorId(authorId: String, limit: Int = 20): PostsResponse {
        return api.fetchPostsByAuthorId(authorId, limit)
    }

    suspend fun getPostsBySubmolt(submolt: String, limit: Int = 20): PostsResponse {
        return api.fetchPostsBySubmolt(submolt, limit)
    }

    suspend fun getPostDetail(postId: String): PostDetailResponse {
        return api.fetchPostDetail(postId)
    }

    suspend fun getTopComment(postId: String): CommentsResponse {
        return api.fetchTopComment(postId)
    }

    suspend fun getComments(postId: String): CommentsResponse {
        return api.fetchComments(postId)
    }

    suspend fun getComments(postId: String, limit: Int): CommentsResponse {
        return api.fetchComments(postId, limit = limit)
    }

    suspend fun getSubmoltDetail(name: String): SubmoltDetailResponse {
        return api.fetchSubmoltDetail(name)
    }

    fun getCachedSubmoltsDirectory(): SubmoltsDirectoryResponse? {
        val raw = prefs.getString("submolts_dir", null) ?: return null
        return runCatching { json.decodeFromString(SubmoltsDirectoryResponse.serializer(), raw) }.getOrNull()
    }

    suspend fun getSubmoltsDirectory(limit: Int = 100, offset: Int = 0, forceRefresh: Boolean = false): SubmoltsDirectoryResponse {
        if (!forceRefresh && offset == 0) {
            getCachedSubmoltsDirectory()?.let { return it }
        }
        val fresh = api.fetchSubmolts(limit = limit, offset = offset)
        if (offset == 0) {
            prefs.edit().putString("submolts_dir", json.encodeToString(fresh)).apply()
        }
        return fresh
    }

    fun getCachedSubmoltMemberships(): Long? {
        val ts = prefsStats.getLong("submolt_memberships_ts", 0L)
        val v = prefsStats.getLong("submolt_memberships", -1L)
        if (v < 0) return null
        // Cache for 12 hours.
        if (System.currentTimeMillis() - ts > 12L * 60L * 60L * 1000L) return null
        return v
    }

    suspend fun computeSubmoltMemberships(): Long {
        // "Memberships" is interpreted as the sum of subscriber_count across all submolts.
        val limit = 100
        val first = api.fetchSubmolts(limit = limit, offset = 0)
        val offsets = (0 until first.count.toInt() step limit).toList()
        val sem = Semaphore(permits = 8)
        val total = coroutineScope {
            offsets.map { off ->
                async {
                    sem.acquire()
                    try {
                        val page = if (off == 0) first else api.fetchSubmolts(limit = limit, offset = off)
                        page.submolts.sumOf { it.subscriberCount ?: 0 }
                    } finally {
                        sem.release()
                    }
                }
            }.awaitAll().sum()
        }
        prefsStats.edit()
            .putLong("submolt_memberships", total)
            .putLong("submolt_memberships_ts", System.currentTimeMillis())
            .apply()
        return total
    }

    fun getCachedAgentProfile(name: String): AgentProfileResponse? {
        val key = name.trim().lowercase()
        if (key.isBlank()) return null
        val raw = prefs.getString("agent_profile_$key", null) ?: return null
        return runCatching { json.decodeFromString(AgentProfileResponse.serializer(), raw) }.getOrNull()
    }

    suspend fun getAgentProfile(name: String): AgentProfileResponse {
        val resp = api.fetchAgentProfile(name)
        // Some endpoints omit `author` in recentPosts; fill it so PostCard can always show agent name.
        val filledPosts = resp.recentPosts.map { p ->
            if (p.author?.name.isNullOrBlank()) {
                p.copy(author = Author(id = resp.agent.id, name = resp.agent.name))
            } else {
                p
            }
        }
        val normalized = resp.copy(recentPosts = filledPosts)
        val key = name.trim().lowercase()
        if (key.isNotBlank()) {
            prefs.edit().putString("agent_profile_$key", json.encodeToString(normalized)).apply()
        }
        return normalized
    }

    fun getCachedRecentAgents(): AgentsRecentResponse? {
        val raw = prefs.getString("agents_recent", null) ?: return null
        return runCatching { json.decodeFromString(AgentsRecentResponse.serializer(), raw) }.getOrNull()
    }

    suspend fun getRecentAgents(limit: Int = 50, sort: String = "recent", forceRefresh: Boolean = false): AgentsRecentResponse {
        if (!forceRefresh) {
            getCachedRecentAgents()?.let { return it }
        }
        val fresh = api.fetchRecentAgents(limit = limit, sort = sort)
        prefs.edit().putString("agents_recent", json.encodeToString(fresh)).apply()
        return fresh
    }

    suspend fun search(query: String, type: String): SearchResponse {
        return api.search(query, type)
    }

    suspend fun resolveAuthorSlugFromPost(post: Post): String? {
        val postId = post.id
        authorSlugCache[postId]?.let { return it }
        return runCatching {
            val detail = getPostDetail(postId)
            val slug = detail.post.author?.name ?: post.author?.name
            if (!slug.isNullOrBlank()) {
                authorSlugCache[postId] = slug
            }
            slug
        }.getOrNull()
    }
}
