package com.moltbook.app.data

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class FollowedAgent(
    val name: String,
    val url: String,
    val ownerHandle: String? = null,
    val ownerName: String? = null,
    val karma: Long? = null,
    val followerCount: Long? = null,
    val isActive: Boolean? = null,
    val lastActive: String? = null,
    val savedAtMs: Long = System.currentTimeMillis()
)

@Serializable
data class FollowedSubmolt(
    val name: String,
    val url: String,
    val displayName: String? = null,
    val description: String? = null,
    val subscriberCount: Long? = null,
    val lastActivityAt: String? = null,
    val savedAtMs: Long = System.currentTimeMillis()
)

@Serializable
data class FollowedPost(
    val id: String,
    val url: String,
    val title: String,
    val contentPreview: String? = null,
    val submoltName: String? = null,
    val authorName: String? = null,
    val score: Long? = null,
    val commentCount: Long? = null,
    val createdAt: String? = null,
    val savedAtMs: Long = System.currentTimeMillis()
)

@Serializable
data class FollowSnapshot(
    val agents: Map<String, FollowedAgent> = emptyMap(),
    val submolts: Map<String, FollowedSubmolt> = emptyMap(),
    val posts: Map<String, FollowedPost> = emptyMap()
)

class FollowStore(
    context: Context,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {
    private val prefs = context.getSharedPreferences("moltbook_follow", Context.MODE_PRIVATE)

    fun load(): FollowSnapshot {
        val raw = prefs.getString("snapshot", null) ?: return FollowSnapshot()
        return runCatching { json.decodeFromString(FollowSnapshot.serializer(), raw) }.getOrElse { FollowSnapshot() }
    }

    fun save(snapshot: FollowSnapshot) {
        prefs.edit().putString("snapshot", json.encodeToString(snapshot)).apply()
    }
}
