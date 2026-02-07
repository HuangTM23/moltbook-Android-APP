package com.moltbook.app.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomepageResponse(
    val success: Boolean,
    val stats: Stats = Stats(),
    val agents: List<Agent> = emptyList(),
    val submolts: List<Submolt> = emptyList(),
    val posts: List<Post> = emptyList(),
    @SerialName("topHumans") val topHumans: List<TopHuman> = emptyList(),
    @SerialName("has_more_posts") val hasMorePosts: Boolean = false
)

@Serializable
data class Stats(
    val agents: Long = 0,
    val submolts: Long = 0,
    val posts: Long = 0,
    val comments: Long = 0
)

@Serializable
data class Agent(
    val id: String,
    val name: String,
    val description: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("claimed_at") val claimedAt: String? = null,
    val karma: Long? = null,
    @SerialName("last_active") val lastActive: String? = null,
    @SerialName("is_active") val isActive: Boolean? = null,
    @SerialName("is_claimed") val isClaimed: Boolean? = null,
    @SerialName("follower_count") val followerCount: Long? = null,
    @SerialName("following_count") val followingCount: Long? = null,
    val owner: Owner? = null
)

@Serializable
data class Owner(
    @SerialName("x_handle") val xHandle: String? = null,
    @SerialName("x_name") val xName: String? = null,
    @SerialName("x_avatar") val xAvatar: String? = null,
    @SerialName("x_follower_count") val xFollowerCount: Long? = null,
    @SerialName("x_verified") val xVerified: Boolean? = null
)

@Serializable
data class Submolt(
    val id: String,
    val name: String,
    @SerialName("display_name") val displayName: String? = null,
    val description: String? = null,
    @SerialName("subscriber_count") val subscriberCount: Long? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("last_activity_at") val lastActivityAt: String? = null,
    @SerialName("featured_at") val featuredAt: String? = null
)

@Serializable
data class SubmoltDetailResponse(
    val success: Boolean,
    val submolt: SubmoltDetail
)

@Serializable
data class SubmoltDetail(
    val id: String,
    val name: String,
    @SerialName("display_name") val displayName: String? = null,
    val description: String? = null,
    @SerialName("subscriber_count") val subscriberCount: Long? = null,
    @SerialName("created_at") val createdAt: String? = null
)

@Serializable
data class Post(
    val id: String,
    val title: String,
    val content: String? = null,
    val url: String? = null,
    val upvotes: Long = 0,
    val downvotes: Long? = 0,
    @SerialName("comment_count") val commentCount: Long = 0,
    @SerialName("created_at") val createdAt: String? = null,
    val submolt: SubmoltRef? = null,
    val author: Author? = null
)

@Serializable
data class SubmoltRef(
    val id: String? = null,
    val name: String? = null,
    @SerialName("display_name") val displayName: String? = null
)

@Serializable
data class Author(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val karma: Long? = null,
    @SerialName("follower_count") val followerCount: Long? = null,
    @SerialName("following_count") val followingCount: Long? = null,
    val owner: Owner? = null
)

@Serializable
data class TopHuman(
    val id: String,
    @SerialName("x_handle") val xHandle: String? = null,
    @SerialName("x_name") val xName: String? = null,
    @SerialName("x_avatar") val xAvatar: String? = null,
    @SerialName("x_follower_count") val xFollowerCount: Long? = null,
    @SerialName("x_verified") val xVerified: Boolean? = null,
    @SerialName("bot_name") val botName: String? = null,
    @SerialName("bot_avatar") val botAvatar: String? = null,
    val rank: Int? = null
)

@Serializable
data class PostsResponse(
    val success: Boolean,
    val posts: List<Post> = emptyList(),
    @SerialName("has_more") val hasMore: Boolean = false
)

@Serializable
data class PostDetailResponse(
    val success: Boolean,
    val post: Post
)

@Serializable
data class CommentsResponse(
    val success: Boolean,
    @SerialName("post_id") val postId: String? = null,
    val comments: List<Comment> = emptyList()
)

@Serializable
data class Comment(
    val id: String,
    val content: String? = null,
    @SerialName("parent_id") val parentId: String? = null,
    val upvotes: Long = 0,
    val downvotes: Long? = 0,
    @SerialName("created_at") val createdAt: String? = null,
    val author: CommentAuthor? = null,
    val replies: List<Comment> = emptyList()
)

@Serializable
data class CommentAuthor(
    val id: String? = null,
    val name: String? = null
)

@Serializable
data class AgentStats(
    val postCount: Long = 0,
    val commentCount: Long = 0
)

@Serializable
data class AgentProfileResponse(
    val success: Boolean,
    val agent: Agent,
    @SerialName("recentPosts") val recentPosts: List<Post> = emptyList(),
    @SerialName("recentComments") val recentComments: List<AgentProfileComment> = emptyList()
)

@Serializable
data class AgentProfileComment(
    val id: String,
    val content: String? = null,
    val upvotes: Long = 0,
    val downvotes: Long? = 0,
    @SerialName("created_at") val createdAt: String? = null,
    val post: AgentProfilePostRef
)

@Serializable
data class AgentProfilePostRef(
    val id: String,
    val title: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("comment_count") val commentCount: Long? = null,
    val submolt: SubmoltRef? = null
)

@Serializable
data class AgentsRecentResponse(
    val success: Boolean,
    val agents: List<Agent> = emptyList(),
    @SerialName("total_count") val totalCount: Long = 0
)

@Serializable
data class SubmoltsDirectoryResponse(
    val success: Boolean,
    val submolts: List<Submolt> = emptyList(),
    val count: Long = 0,
    @SerialName("total_posts") val totalPosts: Long = 0,
    @SerialName("total_comments") val totalComments: Long = 0
)

@Serializable
data class SearchResponse(
    val success: Boolean,
    val results: List<SearchResult> = emptyList(),
    val error: String? = null
)

@Serializable
data class SearchResult(
    val type: String? = null,
    val id: String? = null,
    val title: String? = null,
    val content: String? = null,
    val name: String? = null,
    val handle: String? = null,
    val upvotes: Long? = null,
    val downvotes: Long? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("post_id") val postId: String? = null,
    val author: Author? = null,
    val submolt: SubmoltRef? = null
)
