package com.moltbook.app.data

import android.content.Context
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class HomeUiSnapshot(
    val sort: String = "comments",
    val posts: List<Post> = emptyList(),
    @SerialName("top_comments") val topComments: List<TopCommentEntry> = emptyList(),
    @SerialName("saved_at_ms") val savedAtMs: Long = System.currentTimeMillis()
)

@Serializable
data class TopCommentEntry(
    @SerialName("post_id") val postId: String,
    val comment: Comment
)

@Serializable
data class FollowFeedSnapshot(
    val posts: List<Post> = emptyList(),
    @SerialName("saved_at_ms") val savedAtMs: Long = System.currentTimeMillis()
)

@Serializable
data class SessionSnapshot(
    @SerialName("last_tab") val lastTab: String = "HOME",
    val home: HomeUiSnapshot = HomeUiSnapshot(),
    @SerialName("follow_feed") val followFeed: FollowFeedSnapshot = FollowFeedSnapshot()
)

class SessionStore(
    context: Context,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {
    private val prefs = context.getSharedPreferences("moltbook_session", Context.MODE_PRIVATE)

    fun load(): SessionSnapshot {
        val raw = prefs.getString("snapshot", null) ?: return SessionSnapshot()
        return runCatching { json.decodeFromString(SessionSnapshot.serializer(), raw) }.getOrElse { SessionSnapshot() }
    }

    fun save(snapshot: SessionSnapshot) {
        prefs.edit().putString("snapshot", json.encodeToString(snapshot)).apply()
    }
}

