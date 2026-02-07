package com.moltbook.app.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

class MoltbookApi(
    private val baseUrl: String = "https://www.moltbook.com/api/v1",
    private val json: Json = Json { ignoreUnknownKeys = true }
) {
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    suspend fun fetchHomepage(): HomepageResponse = get("/homepage?shuffle=${System.currentTimeMillis()}")

    suspend fun fetchPosts(sort: String, limit: Int, offset: Int? = null): PostsResponse {
        val offsetPart = if (offset != null && offset > 0) "&offset=$offset" else ""
        return get("/posts?limit=$limit&sort=$sort$offsetPart")
    }

    suspend fun fetchPostsByAuthor(author: String, limit: Int = 20): PostsResponse {
        val encoded = java.net.URLEncoder.encode(author, "UTF-8")
        return get("/posts?author=$encoded&limit=$limit")
    }

    suspend fun fetchPostsByAuthorId(authorId: String, limit: Int = 20): PostsResponse {
        val encoded = java.net.URLEncoder.encode(authorId, "UTF-8")
        return get("/posts?author_id=$encoded&limit=$limit")
    }

    suspend fun fetchPostsBySubmolt(submolt: String, limit: Int = 20): PostsResponse {
        val encoded = java.net.URLEncoder.encode(submolt, "UTF-8")
        return get("/posts?submolt=$encoded&limit=$limit")
    }

    suspend fun fetchPostDetail(postId: String): PostDetailResponse {
        return get("/posts/$postId")
    }

    suspend fun fetchTopComment(postId: String): CommentsResponse {
        return get("/posts/$postId/comments?limit=1&sort=top")
    }

    suspend fun fetchComments(postId: String, limit: Int = 120, sort: String = "new"): CommentsResponse {
        return get("/posts/$postId/comments?limit=$limit&sort=$sort")
    }

    suspend fun fetchSubmoltDetail(name: String): SubmoltDetailResponse {
        return get("/submolts/$name")
    }

    suspend fun fetchSubmolts(limit: Int = 100, offset: Int = 0): SubmoltsDirectoryResponse {
        return get("/submolts?limit=$limit&offset=$offset")
    }

    suspend fun fetchAgentProfile(name: String): AgentProfileResponse {
        val encoded = java.net.URLEncoder.encode(name.trim(), "UTF-8")
        return get("/agents/profile?name=$encoded")
    }

    suspend fun fetchRecentAgents(limit: Int = 50, sort: String = "recent"): AgentsRecentResponse {
        return get("/agents/recent?limit=$limit&sort=$sort")
    }

    suspend fun search(query: String, type: String): SearchResponse {
        val encoded = java.net.URLEncoder.encode(query.trim(), "UTF-8")
        return get("/search?q=$encoded&type=$type&limit=20")
    }

    suspend fun fetchHtml(url: String): String = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IllegalStateException("HTTP ${response.code}")
            }
            response.body?.string().orEmpty()
        }
    }

    private suspend inline fun <reified T> get(path: String): T = withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("$baseUrl$path")
            .get()
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IllegalStateException("HTTP ${response.code}")
            }
            val body = response.body?.string().orEmpty()
            json.decodeFromString(body)
        }
    }
}
