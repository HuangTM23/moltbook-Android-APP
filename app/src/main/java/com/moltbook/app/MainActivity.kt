package com.moltbook.app

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.TrendingUp
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.material.icons.rounded.Handshake
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.RepeatMode
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.ui.zIndex
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isUnspecified
import androidx.core.os.LocaleListCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.compose.material3.Badge
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentifier
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.google.mlkit.nl.translate.Translation
import com.moltbook.app.data.Comment
import com.moltbook.app.data.CommentAuthor
import com.moltbook.app.data.HomepageResponse
import com.moltbook.app.data.MoltbookRepository
import com.moltbook.app.data.Post
import com.moltbook.app.data.Author
import com.moltbook.app.data.Stats
import com.moltbook.app.data.Submolt
import com.moltbook.app.data.SubmoltDetail
import com.moltbook.app.data.TopHuman
import com.moltbook.app.data.FollowStore
import com.moltbook.app.data.FollowSnapshot
import com.moltbook.app.data.FollowedAgent
import com.moltbook.app.data.FollowedSubmolt
import com.moltbook.app.data.FollowedPost
import com.moltbook.app.data.SessionStore
import com.moltbook.app.data.SessionSnapshot
import com.moltbook.app.data.HomeUiSnapshot
import com.moltbook.app.data.TopCommentEntry
import com.moltbook.app.data.FollowFeedSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.withContext
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Apply persisted app language early (Android 12+ supported via AppCompat).
        runCatching {
            val settings = getSharedPreferences("moltbook_settings", Context.MODE_PRIVATE)
            val tag = settings.getString("ui_lang", null)?.trim().orEmpty()
            if (tag.isNotBlank()) {
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(tag))
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MoltBookApp()
        }
    }
}

private enum class MainTab(
    val titleRes: Int,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    HOME(R.string.tab_home, Icons.Outlined.Home),
    RANKINGS(R.string.tab_explore, Icons.Outlined.Explore),
    EXPLORE(R.string.tab_following, Icons.Outlined.Favorite),
    ACCOUNT(R.string.tab_my_agent, Icons.Outlined.AccountCircle)
}

private sealed class Screen {
    object Intro : Screen()
    data class Onboarding(val payload: IntroPayload?) : Screen()
    data class Main(val tab: MainTab) : Screen()
    data class PostDetail(val post: Post) : Screen()
    data class UserDetail(val name: String) : Screen()
    data class SubmoltDetail(val name: String) : Screen()
    data class PairingDetail(val pairing: TopHuman) : Screen()
    data class AgentDetail(val agent: com.moltbook.app.data.Agent) : Screen()
    data class WebPage(val url: String, val title: String? = null) : Screen()
    object AgentsDirectory : Screen()
    object SubmoltsDirectory : Screen()
    object CommunityPlaza : Screen()
}

private data class BrandPalette(
    val background: Color = Color(0xFF0F1113),
    val surface: Color = Color(0xFF1A1A1B),
    val surfaceAlt: Color = Color(0xFF2D2D2E),
    val onSurface: Color = Color(0xFFF6F5F2),
    val onSurfaceMuted: Color = Color(0xFF9C9C9C),
    val accent: Color = Color(0xFFE01B24),
    val accentAlt: Color = Color(0xFF00D4AA)
)

private val LocalIsDark = staticCompositionLocalOf { true }

private sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

private data class IntroPreload(
    val posts: Map<String, List<Post>>,
    val comments: Map<String, Map<String, Comment?>>,
    val timestamps: Map<String, Long>
)

private data class IntroPayload(
    val home: HomepageResponse,
    val defaultPosts: List<Post>,
    val defaultComments: Map<String, Comment?>,
    val preload: IntroPreload
)

private data class SubmoltScreenSnapshot(
    val detail: SubmoltDetail?,
    val posts: List<Post>,
    val topComments: Map<String, Comment?>
)

@Composable
private fun MoltBookApp() {
    val palette = remember { BrandPalette() }
    val context = LocalContext.current
    val repository = remember { MoltbookRepository(context) }
    val followStore = remember { FollowStore(context) }
    val sessionStore = remember { SessionStore(context) }
    val sessionSnapshot = remember { sessionStore.load() }
    val settingsPrefs = remember { context.getSharedPreferences("moltbook_settings", Context.MODE_PRIVATE) }
    var appFontScale by remember { mutableStateOf(settingsPrefs.getFloat("font_scale", 1.0f).coerceIn(0.9f, 1.2f)) }
    var themeMode by remember { mutableStateOf(settingsPrefs.getString("theme_mode", "system") ?: "system") }
    val initialTab = remember {
        val raw = settingsPrefs.getString("last_tab", MainTab.HOME.name)?.trim().orEmpty()
        runCatching { MainTab.valueOf(raw) }.getOrDefault(MainTab.HOME)
    }
    var currentTab by remember { mutableStateOf(initialTab) }
    val skipIntroOnce = remember { settingsPrefs.getBoolean("skip_intro_once", false) }
    val navStack = remember {
        mutableStateListOf<Screen>(
            if (skipIntroOnce) Screen.Main(initialTab) else Screen.Intro
        )
    }
    LaunchedEffect(Unit) {
        if (skipIntroOnce) settingsPrefs.edit().putBoolean("skip_intro_once", false).apply()
    }
    LaunchedEffect(currentTab) {
        settingsPrefs.edit().putString("last_tab", currentTab.name).apply()
    }
    val screen = navStack.last()
    fun push(s: Screen) {
        navStack.add(s)
    }
    fun replaceTop(s: Screen) {
        navStack[navStack.lastIndex] = s
    }
    fun pop() {
        if (navStack.size > 1) {
            navStack.removeAt(navStack.lastIndex)
        } else {
            (context as? Activity)?.finish()
        }
    }
    fun resetToMain(tab: MainTab) {
        navStack.clear()
        navStack.add(Screen.Main(tab))
    }
    var searchPanelVisible by remember { mutableStateOf(false) }
    var xHandleToOpen by remember { mutableStateOf<String?>(null) }
    var openAccountConfig by remember { mutableStateOf(false) }

    // Global Search State
    var searchQuery by remember { mutableStateOf("") }
    var searchHistory by remember { mutableStateOf(listOf<String>()) }
    
    // Load cache immediately
    val cachedHomepage = remember { repository.getCachedHomepage() }
    val cachedPostsData = remember { repository.getCachedPosts("top")?.posts }
    val sessionHomeTopComments = remember(sessionSnapshot) {
        sessionSnapshot.home.topComments.associate { it.postId to it.comment }
    }
    
    var homepageState by remember {
        mutableStateOf<UiState<HomepageResponse>>(
            cachedHomepage?.let { UiState.Success(it) } ?: UiState.Loading
        )
    }
    var homePostsState by remember {
        mutableStateOf<UiState<List<Post>>>(
            sessionSnapshot.home.posts.takeIf { it.isNotEmpty() }?.let { UiState.Success(it) }
                ?: cachedPostsData?.let { UiState.Success(it) }
                ?: UiState.Loading
        )
    }
    
    var searchState by remember { mutableStateOf<UiState<SearchPayload>>(UiState.Success(SearchPayload())) }

    val scope = rememberCoroutineScope()

    // Detail page snapshots to keep "back" experience static.
    val submoltSnapshots = remember { mutableStateMapOf<String, SubmoltScreenSnapshot>() }

    // Follow state (posts + communities + agents)
    var followSnapshot by remember { mutableStateOf(followStore.load()) }
    val followedAgents = remember { mutableStateMapOf<String, FollowedAgent>() }
    val followedSubmolts = remember { mutableStateMapOf<String, FollowedSubmolt>() }
    val followedPosts = remember { mutableStateMapOf<String, FollowedPost>() }
    val followedFeedPosts = remember {
        mutableStateListOf<Post>().apply {
            val seed = sessionSnapshot.followFeed.posts.takeIf { it.isNotEmpty() } ?: emptyList()
            addAll(seed.distinctBy { it.id }.take(80))
        }
    }

    fun persistFollow(snapshot: FollowSnapshot) {
        followSnapshot = snapshot
        followStore.save(snapshot)
        followedAgents.clear(); followedAgents.putAll(snapshot.agents)
        followedSubmolts.clear(); followedSubmolts.putAll(snapshot.submolts)
        followedPosts.clear(); followedPosts.putAll(snapshot.posts)
    }

    LaunchedEffect(Unit) {
        // Hydrate in-memory maps from disk once.
        persistFollow(followSnapshot)
    }

    suspend fun loadFollowFeedIntoCache() {
        val agentNames = followSnapshot.agents.keys.toList()
        val submoltNames = followSnapshot.submolts.keys.toList()
        val postIds = followSnapshot.posts.keys.toList()
        if (agentNames.isEmpty() && submoltNames.isEmpty() && postIds.isEmpty()) {
            followedFeedPosts.clear()
            return
        }
        val posts = coroutineScope {
            val agentPostsDeferred = agentNames.map { name ->
                async(Dispatchers.IO) {
                    runCatching { repository.getAgentProfile(name).recentPosts }.getOrDefault(emptyList())
                }
            }
            val submoltPostsDeferred = submoltNames.map { name ->
                async(Dispatchers.IO) {
                    runCatching { repository.getPostsBySubmolt(name, limit = 20).posts }.getOrDefault(emptyList())
                }
            }
            val followedPostDetailsDeferred = postIds.map { id ->
                async(Dispatchers.IO) {
                    runCatching { repository.getPostDetail(id).post }.getOrNull()
                }
            }
            val listPosts = (agentPostsDeferred + submoltPostsDeferred).awaitAll().flatten()
            val followedDetails = followedPostDetailsDeferred.awaitAll().filterNotNull()
            listPosts + followedDetails
        }
        val merged = posts
            .distinctBy { it.id }
            .sortedByDescending { parseInstantOrZero(it.createdAt) }
        followedFeedPosts.clear()
        followedFeedPosts.addAll(merged)
    }

    fun prefetchFollowFeed() {
        scope.launch { runCatching { loadFollowFeedIntoCache() } }
    }

    suspend fun refreshFollowFeed() {
        loadFollowFeedIntoCache()
    }

    var followStatsInFlight by remember { mutableStateOf(false) }
    var followStatsLastRefreshMs by remember { mutableStateOf(0L) }

    suspend fun refreshFollowStatsIntoCache() {
        val snapshot = followSnapshot
        val agentNames = snapshot.agents.keys.toList()
        val submoltNames = snapshot.submolts.keys.toList()
        if (agentNames.isEmpty() && submoltNames.isEmpty()) return

        val sem = Semaphore(permits = 6)
        val agentResults = coroutineScope {
            agentNames.map { name ->
                async(Dispatchers.IO) {
                    sem.acquire()
                    try {
                        name to runCatching { repository.getAgentProfile(name).agent }.getOrNull()
                    } finally {
                        sem.release()
                    }
                }
            }.awaitAll()
        }
        val submoltResults = coroutineScope {
            submoltNames.map { name ->
                async(Dispatchers.IO) {
                    sem.acquire()
                    try {
                        name to runCatching { repository.getSubmoltDetail(name) }.getOrNull()
                    } finally {
                        sem.release()
                    }
                }
            }.awaitAll()
        }

        val nextAgents = snapshot.agents.toMutableMap()
        agentResults.forEach { (name, agent) ->
            val old = nextAgents[name] ?: return@forEach
            if (agent == null) return@forEach
            nextAgents[name] = old.copy(
                ownerHandle = agent.owner?.xHandle ?: old.ownerHandle,
                ownerName = agent.owner?.xName ?: old.ownerName,
                karma = agent.karma ?: old.karma,
                followerCount = agent.followerCount ?: old.followerCount,
                isActive = agent.isActive ?: old.isActive,
                lastActive = agent.lastActive ?: old.lastActive
            )
        }

        val nextSubmolts = snapshot.submolts.toMutableMap()
        submoltResults.forEach { (name, resp) ->
            val old = nextSubmolts[name] ?: return@forEach
            if (resp == null) return@forEach
            val detail = resp.submolt
            nextSubmolts[name] = old.copy(
                displayName = detail.displayName ?: old.displayName,
                description = detail.description ?: old.description,
                subscriberCount = detail.subscriberCount ?: old.subscriberCount
            )
        }

        // One-shot commit to avoid incremental UI jumps.
        persistFollow(snapshot.copy(agents = nextAgents, submolts = nextSubmolts))
    }

    fun prefetchFollowStats() {
        val now = System.currentTimeMillis()
        // Keep it light: at most once per 5 minutes.
        if (followStatsInFlight) return
        if (followStatsLastRefreshMs > 0L && now - followStatsLastRefreshMs < 5L * 60L * 1000L) return
        followStatsInFlight = true
        scope.launch {
            try {
                refreshFollowStatsIntoCache()
                followStatsLastRefreshMs = System.currentTimeMillis()
            } finally {
                followStatsInFlight = false
            }
        }
    }

    // Background refresh: hydrate follow feed (latest posts from followed agents + communities + followed posts).
    LaunchedEffect(followSnapshot.agents.keys, followSnapshot.submolts.keys, followSnapshot.posts.keys) {
        runCatching { loadFollowFeedIntoCache() }
    }
    LaunchedEffect(followSnapshot.agents.keys, followSnapshot.submolts.keys) {
        // Keep followed agent/submolt stats warm in the background.
        prefetchFollowStats()
    }

    fun toggleFollowAgent(agent: com.moltbook.app.data.Agent) {
        val key = agent.name.trim()
        val nextAgents = followSnapshot.agents.toMutableMap()
        if (nextAgents.containsKey(key)) nextAgents.remove(key)
        else {
            // The agent object coming from Top Pairings can be partial (no karma/followers).
            // Seed follow snapshot with the best-known values, then hydrate once in background.
            val cached = repository.getCachedAgentProfile(key)?.agent
            val mergedOwner = agent.owner ?: cached?.owner
            val merged = agent.copy(
                owner = mergedOwner,
                karma = agent.karma ?: cached?.karma,
                followerCount = agent.followerCount ?: cached?.followerCount,
                isActive = agent.isActive ?: cached?.isActive,
                lastActive = agent.lastActive ?: cached?.lastActive
            )
            nextAgents[key] = FollowedAgent(
                name = key,
                url = "https://www.moltbook.com/u/$key",
                ownerHandle = merged.owner?.xHandle,
                ownerName = merged.owner?.xName,
                karma = merged.karma,
                followerCount = merged.followerCount,
                isActive = merged.isActive,
                lastActive = merged.lastActive
            )
        }
        persistFollow(followSnapshot.copy(agents = nextAgents))

        // If we just followed with missing stats, hydrate once and commit a single update.
        if (nextAgents.containsKey(key)) {
            val current = nextAgents[key]
            val missingStats = (current?.karma == null) || (current.followerCount == null)
            if (missingStats) {
                scope.launch(Dispatchers.IO) {
                    val refreshed = runCatching { repository.getAgentProfile(key).agent }.getOrNull() ?: return@launch
                    val latest = followSnapshot.agents[key] ?: return@launch
                    // Don't overwrite if user unfollowed while fetch was in-flight.
                    if (!followSnapshot.agents.containsKey(key)) return@launch
                    val patched = latest.copy(
                        ownerHandle = refreshed.owner?.xHandle ?: latest.ownerHandle,
                        ownerName = refreshed.owner?.xName ?: latest.ownerName,
                        karma = refreshed.karma ?: latest.karma,
                        followerCount = refreshed.followerCount ?: latest.followerCount,
                        isActive = refreshed.isActive ?: latest.isActive,
                        lastActive = refreshed.lastActive ?: latest.lastActive
                    )
                    val next = followSnapshot.agents.toMutableMap()
                    next[key] = patched
                    // Commit on main thread.
                    scope.launch(Dispatchers.Main) {
                        persistFollow(followSnapshot.copy(agents = next))
                    }
                }
            }
        }
    }

    fun toggleFollowSubmolt(detail: SubmoltDetail) {
        val key = detail.name.trim()
        val next = followSnapshot.submolts.toMutableMap()
        if (next.containsKey(key)) next.remove(key)
        else {
            next[key] = FollowedSubmolt(
                name = key,
                url = "https://www.moltbook.com/m/$key",
                displayName = detail.displayName,
                description = detail.description,
                subscriberCount = detail.subscriberCount,
                lastActivityAt = null
            )
        }
        persistFollow(followSnapshot.copy(submolts = next))
    }

    fun toggleFollowPost(post: Post) {
        val key = post.id
        val next = followSnapshot.posts.toMutableMap()
        if (next.containsKey(key)) next.remove(key)
        else {
            next[key] = FollowedPost(
                id = key,
                url = post.url ?: "https://www.moltbook.com/p/$key",
                title = post.title,
                contentPreview = normalizeContent(post.content).take(240).ifBlank { null },
                submoltName = post.submolt?.name,
                authorName = post.author?.name,
                score = (post.upvotes - (post.downvotes ?: 0)),
                commentCount = post.commentCount,
                createdAt = post.createdAt
            )
        }
        persistFollow(followSnapshot.copy(posts = next))
    }
    
    // Pre-fill comments if cache exists
    val homeTopComments = remember { mutableStateMapOf<String, Comment?>() }
    LaunchedEffect(Unit) {
        if (homeTopComments.isEmpty() && sessionHomeTopComments.isNotEmpty()) {
            homeTopComments.putAll(sessionHomeTopComments)
        }
    }
    LaunchedEffect(cachedPostsData) {
        cachedPostsData?.let { posts ->
            if (homeTopComments.isEmpty()) {
                scope.launch {
                    val map = loadTopCommentsForPosts(repository, posts)
                    homeTopComments.putAll(map)
                }
            }
        }
    }

    LaunchedEffect(searchQuery, searchPanelVisible) {
        if (!searchPanelVisible) return@LaunchedEffect
        if (searchQuery.isBlank()) {
            searchState = UiState.Success(SearchPayload())
            return@LaunchedEffect
        }
        delay(300)
        searchState = UiState.Loading
        searchState = performSearch(repository, searchQuery, homepageState)
    }

    var homeSort by remember { mutableStateOf(sessionSnapshot.home.sort.ifBlank { "comments" }) }
    val homePostsList = remember {
        mutableStateListOf<Post>().apply {
            val seed = sessionSnapshot.home.posts.takeIf { it.isNotEmpty() }
                ?: cachedPostsData
                ?: emptyList()
            addAll(seed.distinctBy { it.id })
        }
    }
    val homeListState = androidx.compose.foundation.lazy.rememberLazyListState()
    var homeRefreshing by remember { mutableStateOf(false) }
    
    var homeRefreshTick by remember { mutableStateOf(0) }
    var homeSortChangeTick by remember { mutableStateOf(0) }
    val preloadPosts = remember { mutableStateMapOf<String, List<Post>>() }
    val preloadComments = remember { mutableStateMapOf<String, Map<String, Comment?>>() }
    val preloadTimestamps = remember { mutableStateMapOf<String, Long>() }
    val preloadInFlight = remember { mutableStateMapOf<String, Boolean>() }
    var isSwitchingSort by remember { mutableStateOf(false) }
    val agentPreloadPosts = remember { mutableStateMapOf<String, List<Post>>() }
    val agentPreloadComments = remember { mutableStateMapOf<String, List<AgentCommentItem>>() }
    val agentPreloadInFlight = remember { mutableStateMapOf<String, Boolean>() }
    val agentDebugInfo = remember { mutableStateMapOf<String, String>() }
    val agentStatsMap = remember { mutableStateMapOf<String, com.moltbook.app.data.AgentStats>() }
    val knownAuthorIds = remember { mutableStateMapOf<String, String>() }
    var scrapeTarget by remember { mutableStateOf<String?>(null) }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                val displayedHomePosts = homePostsList.toList().take(60)
                val topEntries = homeTopComments
                    .mapNotNull { (postId, c) -> c?.let { TopCommentEntry(postId = postId, comment = it) } }
                    .take(80)
                val snap = SessionSnapshot(
                    lastTab = currentTab.name,
                    home = HomeUiSnapshot(
                        sort = homeSort,
                        posts = displayedHomePosts,
                        topComments = topEntries
                    ),
                    followFeed = FollowFeedSnapshot(
                        posts = followedFeedPosts.toList().take(80)
                    )
                )
                sessionStore.save(snap)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    MoltBookTheme(palette, fontScale = appFontScale, themeMode = themeMode) {
        val view = LocalView.current
        val isDark = when (themeMode) {
            "dark" -> true
            "light" -> false
            else -> androidx.compose.foundation.isSystemInDarkTheme()
        }
        val systemBarArgb = MaterialTheme.colorScheme.background.toArgb()
        SideEffect {
            val window = (view.context as? Activity)?.window ?: return@SideEffect
            window.statusBarColor = systemBarArgb
            window.navigationBarColor = systemBarArgb
            WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = !isDark
            WindowInsetsControllerCompat(window, view).isAppearanceLightNavigationBars = !isDark
        }
        val appContext = LocalContext.current
        val scope = rememberCoroutineScope()
        val agentIdMap = remember(homepageState) {
            (homepageState as? UiState.Success)
                ?.data
                ?.agents
                ?.associateBy({ it.name }, { it.id })
                .orEmpty()
        }
        LaunchedEffect(homepageState) {
            val posts = (homepageState as? UiState.Success)?.data?.posts.orEmpty()
            posts.forEach { post ->
                val author = post.author
                if (author?.id != null && !author.name.isNullOrBlank()) {
                    knownAuthorIds[author.name] = author.id
                }
            }
        }
        LaunchedEffect(homePostsState) {
            val posts = (homePostsState as? UiState.Success)?.data.orEmpty()
            posts.forEach { post ->
                val author = post.author
                if (author?.id != null && !author.name.isNullOrBlank()) {
                    knownAuthorIds[author.name] = author.id
                }
            }
        }
        val openAgentByName: (String, String?) -> Unit = openAgentByName@{ name, _ ->
            val clean = name.removePrefix("u/").trim()

            // Enter Agent page immediately; data will hydrate from /agents/profile in background.
            val placeholder = Screen.AgentDetail(
                com.moltbook.app.data.Agent(
                    id = clean,
                    name = clean,
                    description = null,
                    avatarUrl = null,
                    createdAt = null,
                    owner = null
                )
            )
            val top = navStack.lastOrNull()
            if (top !is Screen.AgentDetail || top.agent.name != clean) {
                push(placeholder)
            }

            if (agentPreloadInFlight[clean] == true) return@openAgentByName
            agentPreloadInFlight[clean] = true

            scope.launch {
                try {
                    val profile = repository.getAgentProfile(clean)
                    knownAuthorIds[clean] = profile.agent.id

                    agentStatsMap[clean] = com.moltbook.app.data.AgentStats(
                        postCount = profile.recentPosts.size.toLong(),
                        commentCount = profile.recentComments.size.toLong()
                    )

                    agentPreloadPosts[clean] = profile.recentPosts
                    agentPreloadComments[clean] = profile.recentComments.map { rc ->
                        val postRef = rc.post
                        val post = Post(
                            id = postRef.id,
                            title = postRef.title ?: "(untitled)",
                            content = null,
                            url = null,
                            upvotes = 0,
                            downvotes = 0,
                            commentCount = postRef.commentCount ?: 0,
                            createdAt = postRef.createdAt,
                            submolt = postRef.submolt,
                            author = Author(id = profile.agent.id, name = profile.agent.name)
                        )
                        val comment = Comment(
                            id = rc.id,
                            content = rc.content,
                            parentId = null,
                            upvotes = rc.upvotes,
                            downvotes = rc.downvotes,
                            createdAt = rc.createdAt,
                            author = CommentAuthor(id = profile.agent.id, name = profile.agent.name),
                            replies = emptyList()
                        )
                        AgentCommentItem(post = post, comment = comment)
                    }

                    agentDebugInfo[clean] = "profile posts=${profile.recentPosts.size} comments=${profile.recentComments.size}"

                    val currentTop = navStack.lastOrNull()
                    if (currentTop is Screen.AgentDetail && currentTop.agent.name == clean) {
                        replaceTop(Screen.AgentDetail(profile.agent))
                    }
                } catch (e: Exception) {
                    agentDebugInfo[clean] = "profile error: ${e.localizedMessage ?: "unknown"}"
                } finally {
                    agentPreloadInFlight[clean] = false
                }
            }
        }
        when (val active = screen) {
            Screen.Intro -> {
                IntroScreen(
                    repository = repository,
                    fallbackHomePosts = homePostsList.toList(),
                    fallbackHomeTopComments = homeTopComments.toMap(),
                    onDone = { payload ->
                        // Prime caches first, then enter the app once.
                        homepageState = UiState.Success(payload.home)
                        if (payload.preload.posts.isNotEmpty()) {
                            homeSort = "comments"
                        }
                        preloadPosts.clear()
                        preloadComments.clear()
                        preloadPosts.putAll(payload.preload.posts)
                        preloadComments.putAll(payload.preload.comments)
                        preloadTimestamps.putAll(payload.preload.timestamps)
                        if (payload.defaultComments.isNotEmpty()) {
                            homeTopComments.clear()
                            homeTopComments.putAll(payload.defaultComments)
                        }
                        if (payload.defaultPosts.isNotEmpty()) {
                            homePostsState = UiState.Success(payload.defaultPosts)
                            homePostsList.clear()
                            homePostsList.addAll(payload.defaultPosts.distinctBy { it.id })
                        }
                        currentTab = MainTab.HOME
                        resetToMain(MainTab.HOME)
                    },
                    onOnboarding = { payload ->
                        push(Screen.Onboarding(payload))
                    }
                )
            }
            is Screen.Onboarding -> {
                OnboardingScreen(
                    initialX = context.getSharedPreferences("moltbook_account", Context.MODE_PRIVATE)
                        .getString("x_handle", "") ?: "",
                    initialAgent = context.getSharedPreferences("moltbook_account", Context.MODE_PRIVATE)
                        .getString("agent_name", "") ?: "",
                    onSubmit = { xHandle, agentName ->
                        val prefs = context.getSharedPreferences("moltbook_account", Context.MODE_PRIVATE)
                        val now = System.currentTimeMillis()
                        if (prefs.getLong("first_open_ms", 0L) == 0L) {
                            prefs.edit().putLong("first_open_ms", now).apply()
                        }
                        prefs.edit()
                            .putString("x_handle", xHandle.trim().removePrefix("@"))
                            .putString("agent_name", agentName.trim())
                            .apply()

                        context.getSharedPreferences("moltbook_onboarding", Context.MODE_PRIVATE)
                            .edit()
                            .putBoolean("has_onboarded", true)
                            .apply()

                        val p = active.payload
                        if (p != null) {
                            homepageState = UiState.Success(p.home)
                            homeSort = "comments"
                            preloadPosts.clear()
                            preloadComments.clear()
                            preloadPosts.putAll(p.preload.posts)
                            preloadComments.putAll(p.preload.comments)
                            preloadTimestamps.putAll(p.preload.timestamps)
                            homeTopComments.clear()
                            homeTopComments.putAll(p.defaultComments)
                            homePostsState = UiState.Success(p.defaultPosts)
                            homePostsList.clear()
                            homePostsList.addAll(p.defaultPosts.distinctBy { it.id })
                        } else {
                            homepageState = repository.getCachedHomepage()?.let { UiState.Success(it) } ?: UiState.Loading
                            homePostsState = UiState.Success(emptyList())
                            homePostsList.clear()
                        }

                        currentTab = MainTab.HOME
                        resetToMain(MainTab.HOME)
                    },
                    onLater = {
                        // Mark onboarding complete but keep account empty; user can configure later in Account page.
                        context.getSharedPreferences("moltbook_onboarding", Context.MODE_PRIVATE)
                            .edit()
                            .putBoolean("has_onboarded", true)
                            .apply()

                        val p = active.payload
                        if (p != null) {
                            homepageState = UiState.Success(p.home)
                            homeSort = "comments"
                            preloadPosts.clear()
                            preloadComments.clear()
                            preloadPosts.putAll(p.preload.posts)
                            preloadComments.putAll(p.preload.comments)
                            preloadTimestamps.putAll(p.preload.timestamps)
                            homeTopComments.clear()
                            homeTopComments.putAll(p.defaultComments)
                            homePostsState = UiState.Success(p.defaultPosts)
                            homePostsList.clear()
                            homePostsList.addAll(p.defaultPosts.distinctBy { it.id })
                        } else {
                            homepageState = repository.getCachedHomepage()?.let { UiState.Success(it) } ?: UiState.Loading
                            homePostsState = UiState.Success(emptyList())
                            homePostsList.clear()
                        }
                        currentTab = MainTab.HOME
                        resetToMain(MainTab.HOME)
                    }
                )
            }
            is Screen.Main -> {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = stringResource(currentTab.titleRes),
                            showSearch = currentTab == MainTab.HOME,
                            searchOnly = false,
                            leftVector = null,
                            leftImageRes = R.drawable.moltbook_mascot,
                            rightIcon = if (currentTab == MainTab.ACCOUNT) Icons.Rounded.Settings else null,
                            onRightClick = if (currentTab == MainTab.ACCOUNT) ({ openAccountConfig = true }) else null,
                            onSearchClick = { searchPanelVisible = true }
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier.height(54.dp)
                        ) {
                            MainTab.values().forEach { tab ->
                                NavigationBarItem(
                                    selected = currentTab == tab,
                                    onClick = {
                                        currentTab = tab
                                        resetToMain(tab)
                                    },
                                    icon = {
                                        Box(
                                            modifier = Modifier
                                                .padding(top = 6.dp)
                                        ) {
                                            if (tab == MainTab.EXPLORE) {
                                                BadgedBox(badge = { Badge() }) {
                                                    Icon(
                                                        tab.icon,
                                                        contentDescription = stringResource(tab.titleRes),
                                                        modifier = Modifier.size(26.dp)
                                                    )
                                                }
                                            } else {
                                                Icon(
                                                    tab.icon,
                                                    contentDescription = stringResource(tab.titleRes),
                                                    modifier = Modifier.size(26.dp)
                                                )
                                            }
                                        }
                                    },
                                    alwaysShowLabel = false
                                )
                            }
                        }
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        LaunchedEffect(currentTab) {
                            if (currentTab == MainTab.EXPLORE) {
                                prefetchFollowStats()
                            }
                        }
                        @Composable
                        fun KeepAlivePage(visible: Boolean, content: @Composable () -> Unit) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .zIndex(if (visible) 1f else 0f)
                                    .alpha(if (visible) 1f else 0f)
                            ) {
                                content()
                            }
                        }

                        KeepAlivePage(visible = currentTab == MainTab.HOME) {
                            HomeScreen(
                                repository = repository,
                                homepageState = homepageState,
                                postsState = homePostsState,
                                postsList = homePostsList,
                                topComments = homeTopComments,
                                listState = homeListState,
                                sort = homeSort,
                                refreshing = homeRefreshing,
                                refreshTick = homeRefreshTick,
                                sortChangeTick = homeSortChangeTick,
                                switchingSort = isSwitchingSort,
                                preloadPosts = preloadPosts,
                                preloadComments = preloadComments,
                                preloadTimestamps = preloadTimestamps,
                                preloadInFlight = preloadInFlight,
                                followedPosts = followedPosts,
                                onToggleFollowPost = { toggleFollowPost(it) },
                                onRefresh = {
                                    scope.launch {
                                        homeRefreshing = true
                                        try {
                                            val apiSort = homeSort

                                            val homeDeferred = async { repository.getHomepage(forceRefresh = true) }
                                            val postsDeferred = async {
                                                if (apiSort == "random") {
                                                    repository.getHomepage(forceRefresh = true).posts.take(10)
                                                } else {
                                                    repository.getPosts(sort = apiSort, limit = 10).posts
                                                }
                                            }

                                            val home = homeDeferred.await()
                                            val posts = postsDeferred.await()
                                            val map = loadTopCommentsForPosts(repository, posts)

                                            homeTopComments.clear()
                                            homeTopComments.putAll(map)
                                            homePostsState = UiState.Success(posts)
                                            homepageState = UiState.Success(home)
                                        } catch (e: Exception) {
                                            homepageState = UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
                                        }
                                        homeRefreshTick += 1
                                        homeRefreshing = false
                                    }
                                },
                                onSortChange = { newSort ->
                                    homeSort = newSort
                                    homeSortChangeTick += 1
                                    isSwitchingSort = true
                                    val cachedPosts = preloadPosts[newSort]
                                    val cachedComments = preloadComments[newSort]
                                    if (cachedPosts != null && cachedComments != null) {
                                        homeTopComments.clear()
                                        homeTopComments.putAll(cachedComments)
                                        homePostsState = UiState.Success(cachedPosts)
                                        homeRefreshTick += 1
                                        isSwitchingSort = false
                                    } else {
                                        scope.launch {
                                            val apiSort = newSort
                                            homePostsState = try {
                                                val posts = if (apiSort == "random") {
                                                    repository.getHomepage(forceRefresh = true).posts.take(10)
                                                } else {
                                                    repository.getPosts(sort = apiSort, limit = 10).posts
                                                }
                                                val map = loadTopCommentsForPosts(repository, posts)
                                                homeTopComments.clear()
                                                homeTopComments.putAll(map)
                                                UiState.Success(posts)
                                            } catch (e: Exception) {
                                                UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
                                            }
                                            homeRefreshTick += 1
                                            isSwitchingSort = false
                                        }
                                    }
                                },
                                searchVisible = searchPanelVisible,
                                onDismissSearch = { searchPanelVisible = false },
                                onOpenPost = { push(Screen.PostDetail(it)) },
                                onOpenUser = { name, authorId -> openAgentByName(name, authorId) },
                                onOpenSubmolt = { push(Screen.SubmoltDetail(it)) },
                                onOpenAgent = { push(Screen.AgentDetail(it)) }
                            )
                        }

                        KeepAlivePage(visible = currentTab == MainTab.RANKINGS) {
                            RankingsScreen(
                                homepageState = homepageState,
                                onOpenX = { handle -> xHandleToOpen = handle },
                                onOpenPairing = {
                                    push(
                                        Screen.AgentDetail(
                                            com.moltbook.app.data.Agent(
                                                id = it.id,
                                                name = it.botName ?: "Agent",
                                                owner = com.moltbook.app.data.Owner(
                                                    xHandle = it.xHandle,
                                                    xName = it.xName,
                                                    xAvatar = it.xAvatar,
                                                    xFollowerCount = it.xFollowerCount,
                                                    xVerified = it.xVerified
                                                )
                                            )
                                        )
                                    )
                                },
                                onOpenSubmolt = { push(Screen.SubmoltDetail(it)) },
                                onOpenAgent = { push(Screen.AgentDetail(it)) },
                                onOpenCommunityPlaza = { push(Screen.SubmoltsDirectory) },
                                onOpenUrl = { url, title ->
                                    if (url == "moltbook://agents_directory") {
                                        push(Screen.AgentsDirectory)
                                    } else {
                                        push(Screen.WebPage(url, title))
                                    }
                                }
                            )
                        }

                        KeepAlivePage(visible = currentTab == MainTab.EXPLORE) {
                            ExploreScreen(
                                repository = repository,
                                homepageState = homepageState,
                                followedAgents = followedAgents,
                                followedSubmolts = followedSubmolts,
                                followedPosts = followedPosts,
                                followedFeedPosts = followedFeedPosts,
                                onPrefetchFollowFeed = { prefetchFollowFeed() },
                                onRefreshFollowFeed = { refreshFollowFeed() },
                                onToggleFollowPost = { toggleFollowPost(it) },
                                onOpenAgent = { push(Screen.AgentDetail(it)) },
                                onOpenUser = { name, id -> openAgentByName(name, id) },
                                onOpenSubmolt = { push(Screen.SubmoltDetail(it)) },
                                onOpenPost = { push(Screen.PostDetail(it)) },
                                onOpenX = { handle -> xHandleToOpen = handle }
                            )
                        }

                        KeepAlivePage(visible = currentTab == MainTab.ACCOUNT) {
                            AccountScreen(
                                repository = repository,
                                followedPosts = followedPosts,
                                onToggleFollowPost = { toggleFollowPost(it) },
                                onOpenPost = { push(Screen.PostDetail(it)) },
                                onOpenUser = { name, id -> openAgentByName(name, id) },
                                onOpenSubmolt = { push(Screen.SubmoltDetail(it)) },
                                onOpenX = { handle -> xHandleToOpen = handle },
                                currentFontScale = appFontScale,
                                onUpdateFontScale = { next ->
                                    val clamped = next.coerceIn(0.9f, 1.2f)
                                    appFontScale = clamped
                                    settingsPrefs.edit().putFloat("font_scale", clamped).apply()
                                },
                                currentThemeMode = themeMode,
                                onUpdateThemeMode = { next ->
                                    themeMode = next
                                    settingsPrefs.edit().putString("theme_mode", next).apply()
                                },
                                openConfig = openAccountConfig,
                                onConfigHandled = { openAccountConfig = false }
                            )
                        }
                    }
                }
            }
            is Screen.PostDetail -> {
                PostDetailScreen(
                    post = active.post,
                    repository = repository,
                    isFollowed = followedPosts.containsKey(active.post.id),
                    onToggleFollow = { toggleFollowPost(active.post) },
                    onBack = { pop() },
                    onOpenUser = { name, id -> openAgentByName(name, id) },
                    onOpenSubmolt = { push(Screen.SubmoltDetail(it)) }
                )
            }
            is Screen.UserDetail -> {
                UserDetailScreen(
                    name = active.name,
                    repository = repository,
                    homepageState = homepageState,
                    postsState = homePostsState,
                    followedPosts = followedPosts,
                    onToggleFollowPost = { p -> toggleFollowPost(p) },
                    onBack = { pop() },
                    onOpenPost = { push(Screen.PostDetail(it)) },
                    onOpenSubmolt = { push(Screen.SubmoltDetail(it)) },
                    onOpenUser = { name, id -> openAgentByName(name, id) }
                )
            }
            is Screen.SubmoltDetail -> {
                val snapshot = submoltSnapshots[active.name]
                SubmoltDetailScreen(
                    name = active.name,
                    repository = repository,
                    homepageState = homepageState,
                    postsState = homePostsState,
                    isFollowed = followedSubmolts.containsKey(active.name),
                    onToggleFollow = { detail -> toggleFollowSubmolt(detail) },
                    followedPosts = followedPosts,
                    onToggleFollowPost = { p -> toggleFollowPost(p) },
                    onBack = { pop() },
                    onOpenPost = { push(Screen.PostDetail(it)) },
                    onOpenUser = { name, id -> openAgentByName(name, id) },
                    onOpenSubmolt = { push(Screen.SubmoltDetail(it)) },
                    snapshot = snapshot,
                    onUpdateSnapshot = { submoltSnapshots[active.name] = it }
                )
            }
            is Screen.PairingDetail -> {
                PairingDetailScreen(
                    pairing = active.pairing,
                    onBack = { pop() },
                    onOpenX = { handle -> xHandleToOpen = handle }
                )
            }
            is Screen.AgentDetail -> {
                AgentDetailScreen(
                    agent = active.agent,
                    repository = repository,
                    homepageState = homepageState,
                    postsState = homePostsState,
                    preloadedPosts = agentPreloadPosts,
                    preloadedComments = agentPreloadComments,
                    agentStats = agentStatsMap[active.agent.name],
                    debugInfo = agentDebugInfo[active.agent.name],
                    isFollowed = followedAgents.containsKey(active.agent.name),
                    onToggleFollow = { toggleFollowAgent(active.agent) },
                    followedPosts = followedPosts,
                    onToggleFollowPost = { p -> toggleFollowPost(p) },
                    onBack = { pop() },
                    onOpenPost = { push(Screen.PostDetail(it)) },
                    onOpenX = { handle -> xHandleToOpen = handle },
                    onOpenUser = { name, id -> openAgentByName(name, id) },
                    onOpenSubmolt = { push(Screen.SubmoltDetail(it)) }
                )
            }
            is Screen.CommunityPlaza -> {
                CommunityPlazaScreen(
                    homepageState = homepageState,
                    onBack = { pop() },
                    onOpenSubmolt = { push(Screen.SubmoltDetail(it)) }
                )
            }
            is Screen.WebPage -> {
                WebPageScreen(
                    url = active.url,
                    title = active.title,
                    onBack = { pop() }
                )
            }
            Screen.AgentsDirectory -> {
                AgentsDirectoryScreen(
                    repository = repository,
                    onBack = { pop() },
                    onOpenAgent = { push(Screen.AgentDetail(it)) },
                    onOpenX = { handle -> xHandleToOpen = handle }
                )
            }
            Screen.SubmoltsDirectory -> {
                SubmoltsDirectoryScreen(
                    repository = repository,
                    onBack = { pop() },
                    onOpenSubmolt = { name -> push(Screen.SubmoltDetail(name)) }
                )
            }
        }

        if (searchPanelVisible) {
            BackHandler {
                searchPanelVisible = false
            }
            SearchPanel(
                query = searchQuery,
                history = searchHistory,
                homepageState = homepageState,
                searchState = searchState,
                onQueryChange = { searchQuery = it },
                onSearch = {
                    if (searchQuery.isBlank()) return@SearchPanel
                    searchHistory = (listOf(searchQuery) + searchHistory).distinct().take(6)
                    scope.launch {
                        searchState = performSearch(repository, searchQuery, homepageState)
                    }
                },
                onClearHistory = { searchHistory = emptyList() },
                onDismiss = { searchPanelVisible = false },
                onOpenSubmolt = { push(Screen.SubmoltDetail(it)); searchPanelVisible = false },
                onOpenPost = { push(Screen.PostDetail(it)); searchPanelVisible = false },
                onOpenAgent = { push(Screen.AgentDetail(it)); searchPanelVisible = false }
            )
        }

        scrapeTarget?.let { target ->
            ScrapeWebView(
                name = target,
                repository = repository,
                onPosts = { posts ->
                    if (posts.isNotEmpty()) {
                        agentPreloadPosts[target] = posts
                        posts.forEach { post ->
                            val author = post.author
                            if (author?.id != null && !author.name.isNullOrBlank()) {
                                knownAuthorIds[author.name] = author.id
                            }
                        }
                        scope.launch {
                            val items = mutableListOf<AgentCommentItem>()
                            posts.take(10).forEach { post ->
                                val response = runCatching { repository.getComments(post.id) }.getOrNull()
                                val comments = response?.comments.orEmpty()
                                comments.filter { it.author?.name == target }.forEach { comment ->
                                    items.add(AgentCommentItem(post = post, comment = comment))
                                }
                            }
                            agentPreloadComments[target] = items
                            agentDebugInfo[target] = "webview=${posts.size}"
                        }
                    }
                },
                onStats = { stats ->
                    agentStatsMap[target] = stats
                },
                onDebugLog = { msg ->
                    agentDebugInfo[target] = msg
                },
                onDone = {
                    agentPreloadInFlight[target] = false
                    scrapeTarget = null
                }
            )
        }

        val context = LocalContext.current
        xHandleToOpen?.let { handle ->
            AlertDialog(
                onDismissRequest = { xHandleToOpen = null },
                title = { Text(stringResource(R.string.open_x_title)) },
                text = { Text(stringResource(R.string.open_x_message, handle)) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                android.net.Uri.parse("https://x.com/$handle")
                            )
                            context.startActivity(intent)
                            xHandleToOpen = null
                        }
                    ) {
                        Text(stringResource(R.string.open_x_confirm))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { xHandleToOpen = null }) {
                        Text(stringResource(R.string.open_x_cancel))
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppTopBar(
    title: String,
    showSearch: Boolean,
    searchOnly: Boolean,
    leftVector: androidx.compose.ui.graphics.vector.ImageVector? = null,
    leftImageRes: Int? = null,
    rightIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    onRightClick: (() -> Unit)? = null,
    onSearchClick: () -> Unit
) {
    val isDark = LocalIsDark.current
    val bg = if (isDark) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
    val fg = if (isDark) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onBackground
    val titleStyle = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp
    )
    // Ensure status bar -> app bar has no visible transition in light mode.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg)
            .statusBarsPadding()
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor = Color.Transparent,
                titleContentColor = fg,
                actionIconContentColor = fg,
                navigationIconContentColor = fg
            ),
            title = {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Centered Logo and Title
                    Row(
                        modifier = Modifier.align(Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (!searchOnly) {
                            if (leftVector != null) {
                                Icon(
                                    imageVector = leftVector,
                                    contentDescription = null,
                                    tint = fg,
                                    modifier = Modifier.size(22.dp)
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = leftImageRes ?: R.drawable.moltbook_mascot),
                                    contentDescription = "moltbook",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = titleStyle,
                                color = fg
                            )
                        }
                    }

                    // Right-aligned Search / Action Icon
                    if (rightIcon != null && onRightClick != null) {
                        IconButton(
                            onClick = onRightClick,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 4.dp)
                                .size(40.dp)
                        ) {
                            Icon(
                                imageVector = rightIcon,
                                contentDescription = "Action",
                                modifier = Modifier.size(22.dp),
                                tint = fg
                            )
                        }
                    } else if (showSearch || searchOnly) {
                        IconButton(
                            onClick = onSearchClick,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 4.dp)
                                .size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = "Search",
                                modifier = Modifier.size(24.dp),
                                tint = fg
                            )
                        }
                    }
                }
            },
            actions = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
private fun HomeScreen(
    repository: MoltbookRepository,
    homepageState: UiState<HomepageResponse>,
    postsState: UiState<List<Post>>,
    postsList: MutableList<Post>,
    topComments: MutableMap<String, Comment?>,
    listState: androidx.compose.foundation.lazy.LazyListState,
    sort: String,
    refreshing: Boolean,
    refreshTick: Int,
    sortChangeTick: Int,
    switchingSort: Boolean,
    preloadPosts: MutableMap<String, List<Post>>,
    preloadComments: MutableMap<String, Map<String, Comment?>>,
    preloadTimestamps: MutableMap<String, Long>,
    preloadInFlight: MutableMap<String, Boolean>,
    followedPosts: Map<String, FollowedPost>,
    onToggleFollowPost: (Post) -> Unit,
    onRefresh: () -> Unit,
    onSortChange: (String) -> Unit,
    searchVisible: Boolean,
    onDismissSearch: () -> Unit,
    onOpenPost: (Post) -> Unit,
    onOpenUser: (String, String?) -> Unit,
    onOpenSubmolt: (String) -> Unit,
    onOpenAgent: (com.moltbook.app.data.Agent) -> Unit
) {
    val translations = remember { mutableStateMapOf<String, TranslationState>() }
    val scope = rememberCoroutineScope()
    val languageIdentifier = rememberLanguageIdentifier()

    var isLoadingMore by remember { mutableStateOf(false) }
    var hasMore by remember { mutableStateOf(true) }
    var prefetchingNext by remember { mutableStateOf(false) }
    var nextPageCache by remember { mutableStateOf<List<Post>?>(null) }
    var nextPageComments by remember { mutableStateOf<Map<String, Comment?>>(emptyMap()) }
    var nextPageHasMore by remember { mutableStateOf(true) }

    LaunchedEffect(postsState, refreshTick) {
        if (postsState is UiState.Success) {
            if (postsList.isEmpty() || refreshTick > 0) {
                postsList.clear()
                postsList.addAll(postsState.data.distinctBy { it.id })
                hasMore = true
            }
        }
    }

    LaunchedEffect(sortChangeTick) {
        if (sortChangeTick > 0) {
            hasMore = true
        }
    }

    val sortKeys = remember { listOf("new", "top", "comments", "random") }
    LaunchedEffect(sort) {
        val now = System.currentTimeMillis()
        sortKeys.filter { it != sort }.forEach { key ->
            val last = preloadTimestamps[key] ?: 0L
            val inFlight = preloadInFlight[key] == true
            if (!inFlight && (now - last > 60_000L || !preloadPosts.containsKey(key))) {
                preloadInFlight[key] = true
                scope.launch {
                    val posts = if (key == "random") {
                        repository.getHomepage(forceRefresh = false).posts.take(10)
                    } else {
                        repository.getPosts(sort = key, limit = 10).posts
                    }
                    val map = loadTopCommentsForPosts(repository, posts)
                    preloadPosts[key] = posts
                    preloadComments[key] = map
                    preloadTimestamps[key] = System.currentTimeMillis()
                    preloadInFlight[key] = false
                }
            }
        }
    }

    LaunchedEffect(listState, sort) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastIndex ->
                if (lastIndex == null) return@collect
                val shouldLoad = lastIndex >= postsList.size - 2
                if (shouldLoad && !isLoadingMore && hasMore) {
                    if (sort == "random") return@collect
                    if (nextPageCache != null) {
                        isLoadingMore = true
                        topComments.putAll(nextPageComments)
                        val existing = postsList.map { it.id }.toHashSet()
                        val newItems = nextPageCache.orEmpty().filter { existing.add(it.id) }
                        postsList.addAll(newItems)
                        hasMore = nextPageHasMore
                        nextPageCache = null
                        nextPageComments = emptyMap()
                        isLoadingMore = false
                        return@collect
                    }
                    isLoadingMore = true
                    val result = try {
                        repository.getPosts(sort = sort, limit = 20, offset = postsList.size)
                    } catch (_: Exception) {
                        null
                    }
                    if (result != null && result.posts.isNotEmpty()) {
                        val map = loadTopCommentsForPosts(repository, result.posts)
                        topComments.putAll(map)
                        val existing = postsList.map { it.id }.toHashSet()
                        val newItems = result.posts.filter { existing.add(it.id) }
                        postsList.addAll(newItems)
                        hasMore = result.hasMore
                    } else {
                        hasMore = false
                    }
                    isLoadingMore = false
                }
                val shouldPrefetch = lastIndex >= postsList.size - 6
                if (shouldPrefetch && !prefetchingNext && hasMore && nextPageCache == null && sort != "random") {
                    prefetchingNext = true
                    scope.launch {
                        val result = runCatching {
                            repository.getPosts(sort = sort, limit = 10, offset = postsList.size)
                        }.getOrNull()
                        if (result != null && result.posts.isNotEmpty()) {
                            val map = loadTopCommentsForPosts(repository, result.posts)
                            nextPageCache = result.posts
                            nextPageComments = map
                            nextPageHasMore = result.hasMore
                        }
                        prefetchingNext = false
                    }
                }
            }
    }

    val pullRefreshState = androidx.compose.material.pullrefresh.rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh
    )

    val agentMap = remember(homepageState) {
        (homepageState as? UiState.Success)
            ?.data
            ?.agents
            ?.associateBy { it.name }
            .orEmpty()
    }

    val postsReady = postsList.isNotEmpty() && postsList.all { post ->
        post.commentCount == 0L || topComments.containsKey(post.id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                when (val state = homepageState) {
                    UiState.Loading -> SectionCard { Text(stringResource(R.string.loading_home)) }
                    is UiState.Error -> SectionCard { Text(state.message, color = MaterialTheme.colorScheme.error) }
                    is UiState.Success -> StatsRow(state.data.stats)
                }
            }

            item {
                PostSortChips(sort = sort, onSortChange = { onSortChange(it) })
            }

            when (val state = postsState) {
                UiState.Loading -> item { SectionCard { Text(stringResource(R.string.loading_posts)) } }
                is UiState.Error -> item { SectionCard { Text(state.message, color = MaterialTheme.colorScheme.error) } }
                is UiState.Success -> {
                    if (!postsReady || switchingSort) {
                        item {
                            SectionCard {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        strokeWidth = 2.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    } else {
                        itemsIndexed(postsList, key = { _, post -> post.id }) { index, post ->
                            PostCard(
                                post = post,
                                topComment = topComments[post.id],
                                translation = translations[post.id],
                                isFavorited = followedPosts.containsKey(post.id),
                                isAlt = index % 2 == 1,
                                onToggleFavorite = {
                                    onToggleFollowPost(post)
                                },
                                onOpenDetails = { onOpenPost(post) },
                                onOpenUser = { name, authorId ->
                                    onOpenUser(name, authorId)
                                },
                                onOpenSubmolt = { name ->
                                    onOpenSubmolt(name)
                                },
                                onLoadTopComment = { id ->
                                    if (!topComments.containsKey(id)) {
                                        topComments[id] = null
                                        scope.launch {
                                            val comment = loadTopCommentBatch(repository, id)
                                            topComments[id] = comment
                                        }
                                    }
                                },
                                onTranslate = { request ->
                                    translations[post.id] = TranslationState(isLoading = true)
                                    requestTranslation(request, languageIdentifier) { translations[post.id] = it }
                                }
                            )
                        }
                    if (isLoadingMore) {
                        item {
                            SectionCard {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        strokeWidth = 2.dp,
                                        modifier = Modifier.size(18.dp),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                    }
                }
            }
        }

        androidx.compose.material.pullrefresh.PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            contentColor = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun CommunityPlazaScreen(
    homepageState: UiState<HomepageResponse>,
    onBack: () -> Unit,
    onOpenSubmolt: (String) -> Unit
) {
    BackHandler(onBack = onBack)
    val title = stringResource(R.string.submolts_title)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DetailTopBar(title = title, onBack = onBack)
        
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        stringResource(R.string.submolts_directory_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatColumn("15,864", stringResource(R.string.submolts_count_label))
                        StatColumn("177,529", stringResource(R.string.submolts_posts_label))
                        StatColumn("9,897", stringResource(R.string.members))
                    }
                }
            }
            
            item {
                Divider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)
                )
            }
            
            when (homepageState) {
                is UiState.Success -> {
                    val allSubmolts = homepageState.data.submolts
                    val featuredSubmolts = allSubmolts.take(4) // Assume first 4 are featured
                    
                    // --- FEATURED SECTION ---
                    item {
                        SectionHeader(text = "⭐ ${stringResource(R.string.submolts_featured)}", color = Color(0xFFFFD700))
                    }
                    
                    items(featuredSubmolts) { submolt ->
                        FeaturedCommunityCard(
                            submolt = submolt,
                            onClick = { onOpenSubmolt(submolt.name) }
                        )
                    }
                    
                    // --- ALL COMMUNITIES SECTION ---
                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                        SectionHeader(text = "🦞 ${stringResource(R.string.all_submolts)}", color = MaterialTheme.colorScheme.primary)
                    }
                    
                    items(allSubmolts.chunked(2)) { rowItems ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            rowItems.forEach { submolt ->
                                CommunityCard(
                                    submolt = submolt,
                                    modifier = Modifier.weight(1f),
                                    onClick = { onOpenSubmolt(submolt.name) }
                                )
                            }
                            if (rowItems.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
                UiState.Loading -> item {
                    Box(Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(strokeWidth = 3.dp, color = MaterialTheme.colorScheme.primary)
                    }
                }
                is UiState.Error -> item {
                    Text(
                        "${stringResource(R.string.load_failed)}: ${homepageState.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String, color: Color) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.ExtraBold,
        color = color,
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
    )
}

@Composable
private fun FeaturedCommunityCard(submolt: Submolt, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("⭐", fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "m/${submolt.name}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${formatCount(submolt.subscriberCount ?: 0)} ${stringResource(R.string.members)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
            )
        }
    }
}

@Composable
private fun StatColumn(number: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text = number,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun CommunityCard(submolt: Submolt, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val isDark = LocalIsDark.current
    Card(
        modifier = modifier
            .height(110.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = if (isDark) 0.10f else 0.45f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("🦞", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "m/${submolt.name}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${formatCount(submolt.subscriberCount ?: 0)} ${stringResource(R.string.members)}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RankingsScreen(
    homepageState: UiState<HomepageResponse>,
    onOpenX: (String) -> Unit,
    onOpenPairing: (TopHuman) -> Unit,
    onOpenSubmolt: (String) -> Unit,
    onOpenAgent: (com.moltbook.app.data.Agent) -> Unit,
    onOpenCommunityPlaza: () -> Unit,
    onOpenUrl: (String, String?) -> Unit
) {
    var submoltExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        item {
            when (val state = homepageState) {
                UiState.Loading -> SectionCard { Text(stringResource(R.string.loading_agents_online)) }
                is UiState.Error -> SectionCard { Text(state.message, color = MaterialTheme.colorScheme.error) }
                is UiState.Success -> RecentAgentsStrip(
                    state.data.agents,
                    onOpenX = onOpenX,
                    onOpenAgent = onOpenAgent
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val agentsTitle = stringResource(R.string.agents_title)
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    RankingsSectionTitle(
                        text = stringResource(R.string.explore_top_pairings),
                        iconContent = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Rounded.SmartToy, contentDescription = null, modifier = Modifier.size(22.dp), tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(Icons.Rounded.Person, contentDescription = null, modifier = Modifier.size(22.dp), tint = MaterialTheme.colorScheme.secondary)
                            }
                        }
                    )
                }
                Text(
                    text = stringResource(R.string.more),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { onOpenUrl("moltbook://agents_directory", agentsTitle) }
                )
            }
        }
        when (val state = homepageState) {
            UiState.Loading -> item { SectionCard { Text(stringResource(R.string.loading_rankings)) } }
            is UiState.Error -> item { SectionCard { Text(state.message, color = MaterialTheme.colorScheme.error) } }
            is UiState.Success -> {
                item {
                    PairingsBoard(state.data.topHumans, onOpenPairing, onOpenX)
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    RankingsSectionTitle(
                        text = stringResource(R.string.explore_submolts),
                        iconContent = {
                            Icon(Icons.Rounded.Handshake, contentDescription = null, modifier = Modifier.size(26.dp), tint = MaterialTheme.colorScheme.primary)
                        }
                    )
                }
                Text(
                    text = stringResource(R.string.more),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { onOpenCommunityPlaza() }
                )
            }
        }
        when (val state = homepageState) {
            UiState.Loading -> item { SectionCard { Text(stringResource(R.string.loading_submolts)) } }
            is UiState.Error -> item { SectionCard { Text(state.message, color = MaterialTheme.colorScheme.error) } }
            is UiState.Success -> {
                val list = if (submoltExpanded) state.data.submolts else state.data.submolts.take(6)
                item { SubmoltBoard(list, onOpenSubmolt) }
            }
        }
    }
}

@Composable
private fun RankingsSectionTitle(
    text: String,
    iconContent: @Composable () -> Unit
) {
    val titleStyle = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 17.sp
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        iconContent()
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, style = titleStyle)
    }
}

@Composable
@OptIn(androidx.compose.material.ExperimentalMaterialApi::class)
private fun ExploreScreen(
    repository: MoltbookRepository,
    homepageState: UiState<HomepageResponse>,
    followedAgents: Map<String, FollowedAgent>,
    followedSubmolts: Map<String, FollowedSubmolt>,
    followedPosts: Map<String, FollowedPost>,
    followedFeedPosts: List<Post>,
    onPrefetchFollowFeed: () -> Unit,
    onRefreshFollowFeed: suspend () -> Unit,
    onToggleFollowPost: (Post) -> Unit,
    onOpenAgent: (com.moltbook.app.data.Agent) -> Unit,
    onOpenUser: (String, String?) -> Unit,
    onOpenSubmolt: (String) -> Unit,
    onOpenPost: (Post) -> Unit,
    onOpenX: (String) -> Unit
) {
    val listState = androidx.compose.foundation.lazy.rememberLazyListState()
    var visibleFeed by remember { mutableStateOf(followedFeedPosts.toList()) }
    var refreshing by remember { mutableStateOf(false) }
    val topComments = remember { mutableStateMapOf<String, Comment?>() }
    val translations = remember { mutableStateMapOf<String, TranslationState>() }
    val languageIdentifier = rememberLanguageIdentifier()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // Kick a background prefetch (do not mutate visibleFeed).
        onPrefetchFollowFeed()
    }

    // If we entered before cache was warm, hydrate once when cache becomes available.
    LaunchedEffect(followedFeedPosts.size) {
        if (visibleFeed.isEmpty() && followedFeedPosts.isNotEmpty()) {
            visibleFeed = followedFeedPosts.toList()
        }
    }

    // One more background prefetch when user starts scrolling around.
    var scrollPrefetched by remember { mutableStateOf(false) }
    LaunchedEffect(listState.firstVisibleItemIndex) {
        if (!scrollPrefetched && listState.firstVisibleItemIndex >= 2) {
            scrollPrefetched = true
            onPrefetchFollowFeed()
        }
    }

    val pullRefreshState = androidx.compose.material.pullrefresh.rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            scope.launch {
                refreshing = true
                runCatching { onRefreshFollowFeed() }
                val next = followedFeedPosts.toList()
                val topMap = runCatching { loadTopCommentsForPosts(repository, next.take(20)) }.getOrDefault(emptyMap())
                topComments.clear()
                topComments.putAll(topMap)
                visibleFeed = next
                refreshing = false
            }
        }
    )

    val followedPostItems = followedPosts.values.map {
        Post(
            id = it.id,
            title = it.title,
            content = it.contentPreview,
            url = it.url,
            upvotes = it.score ?: 0,
            downvotes = 0,
            commentCount = it.commentCount ?: 0,
            createdAt = it.createdAt,
            submolt = it.submoltName?.let { n -> com.moltbook.app.data.SubmoltRef(name = n) },
            author = it.authorName?.let { n -> com.moltbook.app.data.Author(name = n) }
        )
    }
    val mergedPosts = (visibleFeed + followedPostItems)
        .distinctBy { it.id }
        .sortedByDescending { parseInstantOrZero(it.createdAt) }
    val targetForTop = mergedPosts.take(20)
    // Keep the list stable: do not auto-load top comments on entry.
    // Top comments will be fetched during user-initiated refresh and then applied once.

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        item {
            SectionTitle(stringResource(R.string.following_agents))
        }
        item {
            if (followedAgents.isEmpty()) {
                SectionCard(modifier = Modifier.height(74.dp + 74.dp + 8.dp)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(stringResource(R.string.empty_following_agents))
                    }
                }
            } else {
                // Two-row max; horizontal scroll to reveal more.
                val agents = followedAgents.values.toList().sortedBy { it.name.lowercase() }
                val half = (agents.size + 1) / 2
                val row1 = agents.take(half)
                val row2 = agents.drop(half)
                val cardWidth = 190.dp
                val cardMinHeight = 74.dp

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardMinHeight + cardMinHeight + 8.dp)
                ) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 2.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(row1.size, key = { idx -> row1[idx].name }) { idx ->
                            Column(
                                modifier = Modifier.width(cardWidth),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                @Composable
                                fun RenderAgentCard(a: FollowedAgent) {
                                    val agent = com.moltbook.app.data.Agent(
                                        id = a.name,
                                        name = a.name,
                                        description = null,
                                        avatarUrl = null,
                                        createdAt = null,
                                        karma = a.karma,
                                        lastActive = a.lastActive,
                                        isActive = a.isActive,
                                        followerCount = a.followerCount,
                                        owner = com.moltbook.app.data.Owner(xHandle = a.ownerHandle, xName = a.ownerName)
                                    )
                                    AgentGridCard(
                                        agent = agent,
                                        presence = (a.isActive to a.lastActive),
                                        onNeedPresence = {},
                                        onOpenAgent = onOpenAgent,
                                        onOpenX = { h -> onOpenX(h) }
                                    )
                                }

                                RenderAgentCard(row1[idx])
                                if (idx < row2.size) {
                                    RenderAgentCard(row2[idx])
                                } else {
                                    Spacer(modifier = Modifier.height(cardMinHeight))
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            SectionTitle(stringResource(R.string.following_submolts))
        }
        item {
            if (followedSubmolts.isEmpty()) {
                SectionCard(modifier = Modifier.height(74.dp)) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(stringResource(R.string.empty_following_submolts))
                    }
                }
            } else {
                val subs = followedSubmolts.values.toList().sortedBy { it.name.lowercase() }
                val cardWidth = 190.dp
                val cardMinHeight = 74.dp
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardMinHeight)
                ) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 2.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(subs, key = { it.name }) { s ->
                            Box(modifier = Modifier.width(cardWidth)) {
                                val sub = Submolt(
                                    id = s.name,
                                    name = s.name,
                                    displayName = s.displayName,
                                    description = s.description,
                                    subscriberCount = s.subscriberCount
                                )
                                SubmoltGridCard(submolt = sub, onOpenSubmolt = { onOpenSubmolt(it) })
                            }
                        }
                    }
                }
            }
        }

            item {
                SectionTitle(stringResource(R.string.following_posts))
            }
            if (mergedPosts.isEmpty()) {
                item {
                    SectionCard(modifier = Modifier.height(240.dp)) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(stringResource(R.string.empty_following_posts))
                        }
                    }
                }
            } else {
                itemsIndexed(mergedPosts, key = { _, p -> p.id }) { index, post ->
                    val authorName = post.author?.name?.trim().orEmpty()
                    val submoltName = post.submolt?.name?.trim().orEmpty()
                    val isContextFollowed =
                        followedPosts.containsKey(post.id) ||
                            (authorName.isNotBlank() && followedAgents.containsKey(authorName)) ||
                            (submoltName.isNotBlank() && followedSubmolts.containsKey(submoltName))
                    PostCard(
                        post = post,
                        topComment = topComments[post.id],
                        translation = translations[post.id],
                        isFavorited = isContextFollowed,
                        isAlt = index % 2 == 1,
                        onToggleFavorite = { onToggleFollowPost(post) },
                        onOpenDetails = { onOpenPost(post) },
                        onOpenUser = { name, id -> onOpenUser(name, id) },
                        onOpenSubmolt = { onOpenSubmolt(it) },
                        onLoadTopComment = {},
                        onTranslate = { request ->
                            translations[post.id] = TranslationState(isLoading = true)
                            requestTranslation(request, languageIdentifier) { translations[post.id] = it }
                        }
                    )
                }
            }
        }
        androidx.compose.material.pullrefresh.PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun AccountScreen(
    repository: MoltbookRepository,
    followedPosts: Map<String, FollowedPost>,
    onToggleFollowPost: (Post) -> Unit,
    onOpenPost: (Post) -> Unit,
    onOpenUser: (String, String?) -> Unit,
    onOpenSubmolt: (String) -> Unit,
    onOpenX: (String) -> Unit,
    currentFontScale: Float,
    onUpdateFontScale: (Float) -> Unit,
    currentThemeMode: String,
    onUpdateThemeMode: (String) -> Unit,
    openConfig: Boolean,
    onConfigHandled: () -> Unit
) {
    val context = LocalContext.current
    val isDark = when (currentThemeMode) {
        "dark" -> true
        "light" -> false
        else -> androidx.compose.foundation.isSystemInDarkTheme()
    }
    val prefs = remember { context.getSharedPreferences("moltbook_account", Context.MODE_PRIVATE) }
    val settingsPrefs = remember { context.getSharedPreferences("moltbook_settings", Context.MODE_PRIVATE) }

    val firstOpenMs = remember {
        val existing = prefs.getLong("first_open_ms", 0L)
        if (existing > 0L) existing else {
            val now = System.currentTimeMillis()
            prefs.edit().putLong("first_open_ms", now).apply()
            now
        }
    }

    var agentName by remember { mutableStateOf(prefs.getString("agent_name", "") ?: "") }
    var xHandle by remember { mutableStateOf(prefs.getString("x_handle", "") ?: "") }
    var tab by remember { mutableStateOf("posts") } // "posts" | "comments"
    var profileState by remember { mutableStateOf<UiState<com.moltbook.app.data.AgentProfileResponse?>>(UiState.Success(null)) }

    var configDialog by remember { mutableStateOf(false) }
    var configName by remember { mutableStateOf(agentName) }
    var configX by remember { mutableStateOf(xHandle) }
    val topComments = remember(agentName.trim()) { mutableStateMapOf<String, Comment?>() }
    val translations = remember(agentName.trim()) { mutableStateMapOf<String, TranslationState>() }
    val languageIdentifier = rememberLanguageIdentifier()

    fun persistConfig(name: String, x: String) {
        val normalizedAgent = normalizeAgentNameInput(name)
        val normalizedX = x.trim().removePrefix("@")
        prefs.edit()
            .putString("agent_name", normalizedAgent)
            .putString("x_handle", normalizedX)
            .apply()
    }

    fun setLanguage(tag: String) {
        val cleaned = tag.trim()
        settingsPrefs.edit().putString("ui_lang", cleaned).apply()
        // Locale switch requires recreation for resource-based strings; skip splash to keep it feeling in-place.
        settingsPrefs.edit().putBoolean("skip_intro_once", true).apply()
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(cleaned))
        // Ensure resources update immediately for this running process.
        (context as? Activity)?.recreate()
    }

    fun setThemeMode(mode: String) {
        val cleaned = mode.trim().ifBlank { "system" }
        onUpdateThemeMode(cleaned)
    }

    LaunchedEffect(agentName.trim()) {
        val name = normalizeAgentNameInput(agentName)
        if (name.isBlank()) {
            profileState = UiState.Success(null)
            return@LaunchedEffect
        }
        // If user pasted a URL or "u/xxx", fix it for future loads.
        if (name != agentName.trim()) {
            agentName = name
            prefs.edit().putString("agent_name", name).apply()
        }
        val cached = repository.getCachedAgentProfile(name)
        profileState = cached?.let { UiState.Success(it) } ?: UiState.Loading
        val refreshed = runCatching { repository.getAgentProfile(name) }.getOrNull()
        if (refreshed != null) {
            profileState = UiState.Success(refreshed)
        } else if (cached == null) {
            profileState = UiState.Error(context.getString(R.string.load_failed))
        }
    }

    val headerGradient = remember(isDark) {
        if (isDark) {
            listOf(Color(0xFF0B1220), Color(0xFF1D4ED8), Color(0xFFEA580C))
        } else {
            listOf(Color(0xFFDBEAFE), Color(0xFFE0F2FE), Color(0xFFFFEDD5))
        }
    }
    val joinedText = remember(firstOpenMs) {
        val cal = java.util.Calendar.getInstance().apply { timeInMillis = firstOpenMs }
        "%04d/%d/%d".format(
            cal.get(java.util.Calendar.YEAR),
            cal.get(java.util.Calendar.MONTH) + 1,
            cal.get(java.util.Calendar.DAY_OF_MONTH)
        )
    }

    val profile = (profileState as? UiState.Success)?.data
    val ownerHandle = profile?.agent?.owner?.xHandle?.takeIf { !it.isNullOrBlank() }
    val configuredOwner = xHandle.trim().removePrefix("@").takeIf { it.isNotBlank() }
    val effectiveOwner = configuredOwner ?: ownerHandle
    val needSetup = agentName.trim().isBlank() && configuredOwner.isNullOrBlank()

    LaunchedEffect(openConfig) {
        if (!openConfig) return@LaunchedEffect
        configName = agentName
        configX = xHandle
        configDialog = true
        onConfigHandled()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 10.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isDark) Color(0xFF0B1220) else Color(0xFFD6DEE8)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    configName = agentName
                    configX = xHandle
                    configDialog = true
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.linearGradient(colors = headerGradient))
                    .padding(horizontal = 14.dp, vertical = 9.dp)
            ) {
                val rowGap = 6.dp
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(rowGap)
                    ) {
                        val nameStyle = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        )
                        val agentDisplay = agentName.trim()
                            .ifBlank { profile?.agent?.name?.trim().orEmpty() }
                            .ifBlank { "Agent" }
                        val humanDisplay = configuredOwner?.trim()?.removePrefix("@")?.takeIf { it.isNotBlank() }
                            ?: "Human"

                        // Line 1: robot icon + name (centered as a unit).
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.SmartToy,
                                contentDescription = null,
                                tint = (if (isDark) Color.White else MaterialTheme.colorScheme.onSurface).copy(alpha = 0.88f),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = agentDisplay,
                                style = nameStyle,
                                color = if (isDark) Color.White else MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        // Line 2: human icon + username (centered as a unit).
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = null,
                                tint = (if (isDark) Color.White else MaterialTheme.colorScheme.onSurface).copy(alpha = 0.88f),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = humanDisplay,
                                style = nameStyle,
                                color = if (isDark) Color.White else MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        // Line 3: intro.
                        Text(
                            text = "We are friend!",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = (if (isDark) Color.White else MaterialTheme.colorScheme.onSurface).copy(alpha = 0.72f),
                            textAlign = TextAlign.Center
                        )

                        // Line 4: capsules + share (same as Agent header style).
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            @Composable
                            fun Capsule(text: String, bg: Color) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(999.dp))
                                    .background(bg)
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = text,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (isDark) Color.White else MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.ExtraBold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        val karma = profile?.agent?.karma ?: 0
                        val followers = profile?.agent?.followerCount ?: 0
                        val following = profile?.agent?.followingCount ?: 0
                        Capsule("💪 ${formatCount(karma)}", Color(0xFFEA580C).copy(alpha = 0.35f))
                        Capsule("🎂 $joinedText", Color(0xFF2563EB).copy(alpha = 0.35f))
                        Capsule("🤖 ${formatCount(followers)}", Color(0xFF16A34A).copy(alpha = 0.35f))
                        Capsule("🏹 ${formatCount(following)}", Color(0xFFA855F7).copy(alpha = 0.30f))

                            IconButton(
                                onClick = {
                                    val a = agentName.trim()
                                    if (a.isNotBlank()) shareText(context, "Agent", "https://www.moltbook.com/u/$a")
                                },
                                enabled = agentName.trim().isNotBlank(),
                                modifier = Modifier.size(22.dp)
                            ) {
                                Icon(
                                    Icons.Rounded.Share,
                                    contentDescription = "Share",
                                    tint = if (isDark) Color.White else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        // Line 5: X account (always one line; clickable only when configured).
                        Text(
                            text = "X: @${configuredOwner ?: "human"}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = (if (isDark) Color.White else MaterialTheme.colorScheme.onSurface).copy(
                                alpha = if (configuredOwner.isNullOrBlank()) 0.60f else 0.90f
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(999.dp))
                                .background(
                                    if (isDark) Color.White.copy(alpha = 0.10f)
                                    else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
                                )
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                .clickable(enabled = !configuredOwner.isNullOrBlank()) { configuredOwner?.let { onOpenX(it) } }
                        )
                    }
                }
            }
        }

        if (configDialog) {
            var draftFontScale by remember(configDialog) { mutableStateOf(currentFontScale) }
            var draftThemeMode by remember(configDialog) { mutableStateOf(currentThemeMode) }
            var draftLang by remember(configDialog) {
                mutableStateOf(
                    settingsPrefs.getString("ui_lang", null)?.trim().orEmpty().ifBlank {
                        if (isAppZh()) "zh-CN" else "en"
                    }
                )
            }
            AlertDialog(
                onDismissRequest = { configDialog = false },
                modifier = Modifier
                    .fillMaxWidth(0.94f)
                    .widthIn(max = 520.dp),
                title = { Text(stringResource(R.string.config_account_title), fontWeight = FontWeight.ExtraBold) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                        Text(
                            text = stringResource(R.string.settings_account_section),
                            fontWeight = FontWeight.ExtraBold
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = configX,
                                onValueChange = { configX = it },
                                singleLine = true,
                                textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                placeholder = { Text(stringResource(R.string.config_x_placeholder), fontWeight = FontWeight.Bold) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                leadingIcon = { Text("@", fontWeight = FontWeight.ExtraBold) }
                            )
                            OutlinedTextField(
                                value = configName,
                                onValueChange = { configName = it },
                                singleLine = true,
                                textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                placeholder = { Text(stringResource(R.string.config_agent_placeholder), fontWeight = FontWeight.Bold) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp)
                            )
                        }

                        Divider()

                        Text(
                            text = stringResource(R.string.settings_system_section),
                            fontWeight = FontWeight.ExtraBold
                        )

                        @Composable
                        fun OptionPill(
                            text: String,
                            selected: Boolean,
                            onClick: () -> Unit
                        ) {
                            Card(
                                modifier = Modifier
                                    .height(34.dp)
                                    .clickable { onClick() },
                                shape = RoundedCornerShape(999.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)
                                    else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                                ),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
                                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.06f)
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .fillMaxHeight(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = text,
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.ExtraBold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }

                        val labelStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.ExtraBold)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.width(72.dp)) {
                                Text(text = stringResource(R.string.settings_font_label), style = labelStyle)
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OptionPill(
                                    text = stringResource(R.string.font_small),
                                    selected = draftFontScale <= 0.95f,
                                    onClick = { draftFontScale = 0.92f }
                                )
                                OptionPill(
                                    text = stringResource(R.string.font_normal),
                                    selected = draftFontScale in 0.96f..1.05f,
                                    onClick = { draftFontScale = 1.0f }
                                )
                                OptionPill(
                                    text = stringResource(R.string.font_large),
                                    selected = draftFontScale > 1.05f,
                                    onClick = { draftFontScale = 1.12f }
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.width(72.dp)) {
                                Text(text = stringResource(R.string.settings_language_label), style = labelStyle)
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OptionPill(
                                    text = stringResource(R.string.lang_zh),
                                    selected = draftLang.equals("zh-CN", ignoreCase = true) || draftLang.equals("zh", ignoreCase = true),
                                    onClick = { draftLang = "zh-CN" }
                                )
                                OptionPill(
                                    text = stringResource(R.string.lang_en),
                                    selected = draftLang.equals("en", ignoreCase = true),
                                    onClick = { draftLang = "en" }
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.width(72.dp)) {
                                Text(text = stringResource(R.string.settings_theme_label), style = labelStyle)
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OptionPill(
                                    text = stringResource(R.string.theme_light),
                                    selected = draftThemeMode == "light",
                                    onClick = { draftThemeMode = "light" }
                                )
                                OptionPill(
                                    text = stringResource(R.string.theme_dark),
                                    selected = draftThemeMode == "dark",
                                    onClick = { draftThemeMode = "dark" }
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.width(72.dp)) {
                                Text(text = stringResource(R.string.settings_feedback_label), style = labelStyle)
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                fun openUrl(url: String) {
                                    runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
                                }
                                fun openEmail(address: String) {
                                    runCatching {
                                        context.startActivity(
                                            Intent(Intent.ACTION_SENDTO).apply { data = Uri.parse("mailto:$address") }
                                        )
                                    }
                                }

                                @Composable
                                fun FeedbackIcon(content: @Composable () -> Unit, onClick: () -> Unit) {
                                    Box(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f))
                                            .clickable { onClick() },
                                        contentAlignment = Alignment.Center
                                    ) { content() }
                                }

                                FeedbackIcon(
                                    content = { Text("X", fontWeight = FontWeight.ExtraBold) },
                                    onClick = { openUrl("https://x.com/Dawn20251201/status/2020041752183459879?s=20") }
                                )
                                FeedbackIcon(
                                    content = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_github),
                                            contentDescription = "GitHub",
                                            tint = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    onClick = { openUrl("https://github.com/HuangTM23?tab=repositories") }
                                )
                                FeedbackIcon(
                                    content = {
                                        Icon(
                                            imageVector = Icons.Rounded.Email,
                                            contentDescription = "Email",
                                            tint = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    },
                                    onClick = { openEmail("huangtianmingw@outlook.com") }
                                )
                            }
                        }
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val next = configName.trim()
                            persistConfig(next, configX)
                            agentName = next
                            xHandle = configX.trim().removePrefix("@")
                            configDialog = false

                            // Apply system settings only on Save to avoid "instant restart" feel.
                            val applyFont = draftFontScale.coerceIn(0.9f, 1.2f)
                            val applyTheme = draftThemeMode.trim().ifBlank { "system" }
                            val applyLang = draftLang.trim().ifBlank { settingsPrefs.getString("ui_lang", "en") ?: "en" }
                            Handler(Looper.getMainLooper()).post {
                                if (applyFont != currentFontScale) onUpdateFontScale(applyFont)
                                if (applyTheme != currentThemeMode) setThemeMode(applyTheme)
                                val currentLang = settingsPrefs.getString("ui_lang", null)?.trim().orEmpty()
                                val changedLang = !applyLang.equals(currentLang, ignoreCase = true) &&
                                    !(applyLang.equals("zh-CN", ignoreCase = true) && currentLang.equals("zh", ignoreCase = true)) &&
                                    !(applyLang.equals("zh", ignoreCase = true) && currentLang.equals("zh-CN", ignoreCase = true))
                                if (changedLang) setLanguage(applyLang)
                            }
                        }
                    ) { Text(stringResource(R.string.save), fontWeight = FontWeight.ExtraBold) }
                },
                dismissButton = {
                    TextButton(onClick = { configDialog = false }) { Text(stringResource(R.string.cancel), fontWeight = FontWeight.ExtraBold) }
                }
            )
        }

        SectionCard(containerColor = if (LocalIsDark.current) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.82f)) {
            val tabIndex = if (tab == "posts") 0 else 1
            val postsCount = (profile as? com.moltbook.app.data.AgentProfileResponse)?.recentPosts?.size ?: 0
            val commentsCount = (profile as? com.moltbook.app.data.AgentProfileResponse)?.recentComments?.size ?: 0
            val tabTextStyle = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent)
                    .border(
                        1.dp,
                        if (LocalIsDark.current) Color.White.copy(alpha = 0.12f)
                        else MaterialTheme.colorScheme.outline.copy(alpha = 0.65f),
                        RoundedCornerShape(12.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)
                        .align(if (tabIndex == 0) Alignment.CenterStart else Alignment.CenterEnd)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (LocalIsDark.current) Color.White.copy(alpha = 0.14f)
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.06f)
                        )
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { tab = "posts" },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "📝 ${stringResource(R.string.tab_posts)} (${formatCount(postsCount.toLong())})",
                            style = tabTextStyle,
                            color = if (tabIndex == 0) (if (LocalIsDark.current) Color.White else MaterialTheme.colorScheme.onSurface)
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { tab = "comments" },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "💬 ${stringResource(R.string.tab_comments)} (${formatCount(commentsCount.toLong())})",
                            style = tabTextStyle,
                            color = if (tabIndex == 1) (if (LocalIsDark.current) Color.White else MaterialTheme.colorScheme.onSurface)
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        val hasConfiguredAgent = agentName.trim().isNotBlank()
        if (!hasConfiguredAgent) {
            // No account configured: do not load, do not show errors. Keep layout stable.
            SectionCard(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.agent_silent_observation), fontWeight = FontWeight.ExtraBold)
                }
            }
        } else {
            SectionCard(modifier = Modifier.weight(1f)) {
                when (val state = profileState) {
                    UiState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(strokeWidth = 2.dp)
                        }
                    }
                    is UiState.Error -> {
                        Text(state.message, color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                    }
                    is UiState.Success -> {
                        val profile2 = state.data
                        if (profile2 == null) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(stringResource(R.string.agent_silent_observation), fontWeight = FontWeight.ExtraBold)
                            }
                            return@SectionCard
                        }

                        val commentSubject = stringResource(R.string.share_comment_subject)
                        if (tab == "posts") {
                            val posts = profile2.recentPosts
                            if (posts.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(stringResource(R.string.agent_silent_observation), fontWeight = FontWeight.ExtraBold)
                                }
                                return@SectionCard
                            }

                            var loadingTop by remember(agentName.trim()) { mutableStateOf(false) }
                            LaunchedEffect(tab, posts.map { it.id }) {
                                if (tab != "posts") return@LaunchedEffect
                                if (posts.isEmpty()) return@LaunchedEffect
                                if (loadingTop) return@LaunchedEffect
                                loadingTop = true
                                val map = runCatching { loadTopCommentsForPosts(repository, posts) }.getOrDefault(emptyMap())
                                topComments.clear()
                                topComments.putAll(map)
                                loadingTop = false
                            }

                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                items(posts, key = { it.id }) { p ->
                                    PostCard(
                                        post = p,
                                        topComment = topComments[p.id],
                                        translation = translations[p.id],
                                        isFavorited = followedPosts.containsKey(p.id),
                                        isAlt = false,
                                        onToggleFavorite = { onToggleFollowPost(p) },
                                        onOpenDetails = { onOpenPost(p) },
                                        onOpenUser = { name, id -> onOpenUser(name, id) },
                                        onOpenSubmolt = { onOpenSubmolt(it) },
                                        onLoadTopComment = {},
                                        onTranslate = { request ->
                                            translations[p.id] = TranslationState(isLoading = true)
                                            requestTranslation(request, languageIdentifier) { translations[p.id] = it }
                                        }
                                    )
                                }
                            }
                        } else {
                            val commentItems = remember(profile2.agent.id, profile2.agent.name, profile2.recentComments.size) {
                                profile2.recentComments.map { rc ->
                                    val postRef = rc.post
                                    val post = Post(
                                        id = postRef.id,
                                        title = postRef.title ?: "(untitled)",
                                        content = null,
                                        url = null,
                                        upvotes = 0,
                                        downvotes = 0,
                                        commentCount = postRef.commentCount ?: 0,
                                        createdAt = postRef.createdAt,
                                        submolt = postRef.submolt,
                                        author = Author(id = profile2.agent.id, name = profile2.agent.name)
                                    )
                                    val comment = Comment(
                                        id = rc.id,
                                        content = rc.content,
                                        parentId = null,
                                        upvotes = rc.upvotes,
                                        downvotes = rc.downvotes,
                                        createdAt = rc.createdAt,
                                        author = CommentAuthor(id = profile2.agent.id, name = profile2.agent.name),
                                        replies = emptyList()
                                    )
                                    AgentCommentItem(post = post, comment = comment)
                                }
                            }
                            val commentTranslations = remember(agentName.trim()) { mutableStateMapOf<String, TranslationState>() }
                            val commentFavorites = remember(agentName.trim()) { mutableStateMapOf<String, Boolean>() }

                            if (commentItems.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(stringResource(R.string.agent_silent_observation), fontWeight = FontWeight.ExtraBold)
                                }
                            } else {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    items(commentItems, key = { it.comment.id }) { item ->
                                        val comment = item.comment
                                        val isFav = commentFavorites[comment.id] == true
                                        SectionCard {
                                            Text(
                                                text = item.post.title,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Text(
                                                text = formatTimeAgo(comment.createdAt),
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = comment.content ?: "",
                                                style = MaterialTheme.typography.bodySmall
                                            )

                                            if (isFav) {
                                                Text(
                                                    text = stringResource(R.string.original_prefix) +
                                                        "${item.post.title} ${normalizeContent(item.post.content).take(80)}",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }

                                            commentTranslations[comment.id]?.let { ts ->
                                                when {
                                                    ts.isLoading -> Text(stringResource(R.string.translating), style = MaterialTheme.typography.bodySmall)
                                                    ts.error != null -> Text(ts.error, color = MaterialTheme.colorScheme.error)
                                                    ts.translatedContent != null -> Text(
                                                        ts.translatedContent,
                                                        style = MaterialTheme.typography.bodySmall,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                            }

                                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                                IconButton(
                                                    onClick = { commentFavorites[comment.id] = !isFav },
                                                    modifier = Modifier.size(24.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = if (isFav) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                                                        contentDescription = "Favorite",
                                                        modifier = Modifier.size(14.dp),
                                                        tint = if (isFav) Color(0xFFE11D48) else MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                                IconButton(
                                                    onClick = {
                                                        commentTranslations[comment.id] = TranslationState(isLoading = true)
                                                        requestTranslation(
                                                            TranslationRequest(
                                                                title = commentSubject,
                                                                content = comment.content
                                                            ),
                                                            languageIdentifier
                                                        ) { commentTranslations[comment.id] = it }
                                                    },
                                                    modifier = Modifier.size(24.dp)
                                                ) {
                                                    Icon(Icons.Rounded.Translate, contentDescription = "Translate", modifier = Modifier.size(14.dp))
                                                }
                                                IconButton(
                                                    onClick = { shareComment(context, item.post, comment.content ?: "") },
                                                    modifier = Modifier.size(24.dp)
                                                ) {
                                                    Icon(Icons.Rounded.Share, contentDescription = "Share", modifier = Modifier.size(14.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WebPageScreen(
    url: String,
    title: String? = null,
    onBack: () -> Unit
) {
    var webViewRef by remember { mutableStateOf<WebView?>(null) }
    BackHandler {
        val wv = webViewRef
        if (wv != null && wv.canGoBack()) wv.goBack() else onBack()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        DetailTopBar(title = title ?: stringResource(R.string.title_web), onBack = onBack)
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                WebView(ctx).apply {
                    webViewRef = this
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.cacheMode = WebSettings.LOAD_DEFAULT
                    webViewClient = object : WebViewClient() {}
                    loadUrl(url)
                }
            },
            update = { wv ->
                webViewRef = wv
            }
        )
    }
}

@Composable
private fun AgentsDirectoryScreen(
    repository: MoltbookRepository,
    onBack: () -> Unit,
    onOpenAgent: (com.moltbook.app.data.Agent) -> Unit,
    onOpenX: (String) -> Unit
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    val cached = remember { repository.getCachedRecentAgents() }
    var state by remember {
        mutableStateOf<UiState<com.moltbook.app.data.AgentsRecentResponse>>(
            cached?.let { UiState.Success(it) } ?: UiState.Loading
        )
    }

    var searchState by remember { mutableStateOf<UiState<List<com.moltbook.app.data.Agent>>>(UiState.Success(emptyList())) }
    val agentPresence = remember { mutableStateMapOf<String, Pair<Boolean?, String?>>() } // name -> (isActive, lastActive)
    val presenceInFlight = remember { mutableStateMapOf<String, Boolean>() }
    val scope = rememberCoroutineScope()

    val ensurePresence: (String) -> Unit = ensurePresence@{ name ->
        if (agentPresence.containsKey(name)) return@ensurePresence
        if (presenceInFlight[name] == true) return@ensurePresence
        presenceInFlight[name] = true
        scope.launch {
            try {
                val profile = repository.getAgentProfile(name)
                agentPresence[name] = (profile.agent.isActive to profile.agent.lastActive)
            } catch (_: Exception) {
                // Mark as known to prevent spamming; will still render with placeholders.
                agentPresence[name] = (null to null)
            } finally {
                presenceInFlight[name] = false
            }
        }
    }

    LaunchedEffect(Unit) {
        try {
            val fresh = repository.getRecentAgents(limit = 50, sort = "recent", forceRefresh = true)
            state = UiState.Success(fresh)
        } catch (e: Exception) {
            if (state !is UiState.Success) {
                state = UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
            }
        }
    }

    LaunchedEffect(state) {
        val recent = (state as? UiState.Success)?.data ?: return@LaunchedEffect
        // Warm up presence for the first screenful, but don't block UI.
        recent.agents.take(18).forEach { ensurePresence(it.name) }
    }

    LaunchedEffect(query) {
        if (query.isBlank()) {
            searchState = UiState.Success(emptyList())
            return@LaunchedEffect
        }
        delay(300)
        searchState = UiState.Loading
        searchState = try {
            val resp = repository.search(query, "agents")
            val agents = resp.results.mapNotNull { r ->
                val name = (r.handle ?: r.name ?: "").trim()
                if (name.isBlank()) null
                else com.moltbook.app.data.Agent(id = r.id ?: name, name = name, description = r.content)
            }
            UiState.Success(agents.distinctBy { it.name })
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: context.getString(R.string.search_failed))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val recent = (state as? UiState.Success)?.data
        val total = recent?.totalCount ?: 0

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 2.dp)
                .padding(horizontal = 10.dp, vertical = 2.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val headerGradient = remember(total) {
                val palettes = listOf(
                    listOf(Color(0xFF0B1220), Color(0xFF1D4ED8)),
                    listOf(Color(0xFF0B1220), Color(0xFF7C3AED)),
                    listOf(Color(0xFF0B1220), Color(0xFFEA580C)),
                    listOf(Color(0xFF0B1220), Color(0xFF0F766E))
                )
                palettes[kotlin.math.abs(total.hashCode()) % palettes.size]
            }
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0B1220)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.linearGradient(colors = headerGradient))
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.agents_title),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Browse all AI agents on Moltbook",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.82f),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "Agents:",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                            Text(
                                text = if (total > 0) total.toString() else "—",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                    }

                }
            }

            // Search row (rounded-rect style)
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                singleLine = true,
                placeholder = { Text(stringResource(R.string.find_agent), fontWeight = FontWeight.Bold) },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f),
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        }

        val recentFiltered = recent?.agents.orEmpty().let { list ->
            if (query.isBlank()) list else list.filter { it.name.contains(query, ignoreCase = true) }
        }

        val gridAgents: List<com.moltbook.app.data.Agent> = when (val s = searchState) {
            is UiState.Success -> if (query.isNotBlank() && s.data.isNotEmpty()) s.data else recentFiltered
            else -> recentFiltered
        }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(gridAgents, key = { it.id }) { agent ->
                AgentGridCard(
                    agent = agent,
                    presence = agentPresence[agent.name],
                    onNeedPresence = ensurePresence,
                    onOpenAgent = onOpenAgent,
                    onOpenX = onOpenX
                )
            }
        }
    }
}

@Composable
private fun AgentGridCard(
    agent: com.moltbook.app.data.Agent,
    presence: Pair<Boolean?, String?>?,
    onNeedPresence: (String) -> Unit,
    onOpenAgent: (com.moltbook.app.data.Agent) -> Unit,
    onOpenX: (String) -> Unit
) {
    val isDark = LocalIsDark.current
    LaunchedEffect(agent.name, presence) {
        if (presence == null) onNeedPresence(agent.name)
    }
    val (isActive, lastActive) = presence ?: (null to null)
    val avatarColor = remember(agent.name) {
        val hues = listOf(12f, 28f, 165f, 210f, 255f, 285f)
        val hue = hues[kotlin.math.abs(agent.name.hashCode()) % hues.size]
        Color.hsv(hue, 0.55f, 0.92f)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 74.dp)
            .clickable { onOpenAgent(agent) }
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = if (isDark) 0.10f else 0.45f), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(avatarColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            agent.name.take(1).uppercase(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                    if (isActive == true) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .align(Alignment.BottomEnd)
                                .clip(CircleShape)
                                .background(Color(0xFF22C55E))
                                .border(1.dp, MaterialTheme.colorScheme.surface, CircleShape)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        agent.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "🤖 ${formatCount(agent.followerCount ?: 0)}  /  💪 ${formatCount(agent.karma ?: 0)}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    val handle = agent.owner?.xHandle
                    if (!handle.isNullOrBlank()) {
                        Text(
                            text = "@$handle",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { onOpenX(handle) },
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SubmoltsDirectoryScreen(
    repository: MoltbookRepository,
    onBack: () -> Unit,
    onOpenSubmolt: (String) -> Unit
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    val cached = remember { repository.getCachedSubmoltsDirectory() }
    var state by remember {
        mutableStateOf<UiState<com.moltbook.app.data.SubmoltsDirectoryResponse>>(
            cached?.let { UiState.Success(it) } ?: UiState.Loading
        )
    }
    var agentsTotal by remember { mutableStateOf(repository.getCachedRecentAgents()?.totalCount) }

    LaunchedEffect(Unit) {
        try {
            val fresh = repository.getSubmoltsDirectory(limit = 100, offset = 0, forceRefresh = true)
            state = UiState.Success(fresh)
        } catch (e: Exception) {
            if (state !is UiState.Success) {
                state = UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
            }
        }
    }
    LaunchedEffect(Unit) {
        if (agentsTotal != null) return@LaunchedEffect
        runCatching { repository.getRecentAgents(limit = 1, sort = "recent", forceRefresh = false).totalCount }
            .onSuccess { agentsTotal = it }
    }

    val data = (state as? UiState.Success)?.data
    val total = data?.count ?: 0
    val totalPosts = data?.totalPosts ?: 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 2.dp)
                .padding(horizontal = 10.dp, vertical = 2.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val headerGradient = remember(total) {
                val palettes = listOf(
                    listOf(Color(0xFF0B1220), Color(0xFF0F766E)),
                    listOf(Color(0xFF0B1220), Color(0xFF2563EB)),
                    listOf(Color(0xFF0B1220), Color(0xFF7C3AED)),
                    listOf(Color(0xFF0B1220), Color(0xFFEA580C))
                )
                palettes[kotlin.math.abs(total.hashCode()) % palettes.size]
            }
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0B1220)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.linearGradient(colors = headerGradient))
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.submolts_title),
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Discover where AI agents gather to share and discuss",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White.copy(alpha = 0.82f),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = if (total > 0) "%,d".format(total) else "—",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Text(
                                    text = stringResource(R.string.submolts_count_label),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White.copy(alpha = 0.82f)
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                Text(
                                    text = if (totalPosts > 0) "%,d".format(totalPosts) else "—",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Text(
                                    text = stringResource(R.string.submolts_posts_label),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White.copy(alpha = 0.82f)
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                val agentsText = agentsTotal?.let { "%,d".format(it) } ?: "…"
                                Text(
                                    text = agentsText,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Text(
                                    text = stringResource(R.string.submolts_agents_label),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White.copy(alpha = 0.82f)
                                )
                            }
                        }
                    }

                    // no live pill on this page
                }
            }

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                singleLine = true,
                placeholder = { Text(stringResource(R.string.find_submolt), fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f),
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )
        }

        val submolts = data?.submolts.orEmpty()
        val filtered = if (query.isBlank()) submolts else submolts.filter {
            it.name.contains(query, ignoreCase = true) || (it.displayName ?: "").contains(query, ignoreCase = true)
        }
        val featured = filtered.filter { !it.featuredAt.isNullOrBlank() }.sortedByDescending { parseInstantOrZero(it.featuredAt) }
        val all = filtered.filter { it.featuredAt.isNullOrBlank() }

        when (val s = state) {
            UiState.Loading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    item { SectionCard { Text(stringResource(R.string.loading)) } }
                }
            }
            is UiState.Error -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    item { SectionCard { Text(s.message, color = MaterialTheme.colorScheme.error) } }
                }
            }
            is UiState.Success -> {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item(span = { GridItemSpan(2) }) {
                        SectionCard(
                            modifier = Modifier.padding(horizontal = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text("⭐", fontSize = 16.sp)
                                Text(
                                    text = stringResource(R.string.submolts_featured),
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                    if (featured.isEmpty()) {
                        item(span = { GridItemSpan(2) }) { SectionCard { Text("—") } }
                    } else {
                        items(featured.take(12), key = { "f_${it.id}" }) { submolt ->
                            SubmoltGridCard(submolt = submolt, onOpenSubmolt = onOpenSubmolt)
                        }
                    }
                    item(span = { GridItemSpan(2) }) {
                        SectionCard(
                            modifier = Modifier.padding(horizontal = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text("🦞", fontSize = 16.sp)
                                Text(
                                    text = stringResource(R.string.all_submolts),
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }
                    }
                    items(all, key = { it.id }) { submolt ->
                        SubmoltGridCard(submolt = submolt, onOpenSubmolt = onOpenSubmolt)
                    }
                }
            }
        }
    }
}

@Composable
private fun SubmoltGridCard(
    submolt: Submolt,
    onOpenSubmolt: (String) -> Unit
) {
    val isDark = LocalIsDark.current
    val avatarColor = remember(submolt.name) {
        val hues = listOf(12f, 28f, 165f, 210f, 255f, 285f)
        val hue = hues[kotlin.math.abs(submolt.name.hashCode()) % hues.size]
        Color.hsv(hue, 0.55f, 0.92f)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 74.dp)
            .clickable { onOpenSubmolt(submolt.name) }
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = if (isDark) 0.10f else 0.45f), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(avatarColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        submolt.name.take(1).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        "m/${submolt.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${formatCount(submolt.subscriberCount ?: 0)} ${stringResource(R.string.members)}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            if (!submolt.description.isNullOrBlank()) {
                Text(
                    text = submolt.description!!,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun StatsRow(stats: Stats) {
    SectionCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatItem(stringResource(R.string.stats_agents), stats.agents, Color(0xFFE01B24))
            StatItem(stringResource(R.string.stats_submolts), stats.submolts, Color(0xFF00D4AA))
            StatItem(stringResource(R.string.stats_posts), stats.posts, Color(0xFF4A9EFF))
            StatItem(stringResource(R.string.stats_comments), stats.comments, Color(0xFFFFD700))
        }
    }
}

@Composable
private fun StatItem(label: String, value: Long, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = formatCount(value), color = color, fontWeight = FontWeight.Bold)
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun PostSortChips(sort: String, onSortChange: (String) -> Unit) {
    var topMenuExpanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip("new", sort, onSelect = onSortChange, label = stringResource(R.string.sort_new), width = 84.dp)
        Box {
            FilterChip("top", sort, onSelect = { topMenuExpanded = true }, label = stringResource(R.string.sort_top), width = 84.dp)
            androidx.compose.material3.DropdownMenu(
                expanded = topMenuExpanded,
                onDismissRequest = { topMenuExpanded = false }
            ) {
                androidx.compose.material3.DropdownMenuItem(
                    text = { Text("Top · 24h") },
                    onClick = {
                        onSortChange("top")
                        topMenuExpanded = false
                    }
                )
                androidx.compose.material3.DropdownMenuItem(
                    text = { Text("Top · Week") },
                    onClick = {
                        onSortChange("top")
                        topMenuExpanded = false
                    }
                )
                androidx.compose.material3.DropdownMenuItem(
                    text = { Text("Top · Month") },
                    onClick = {
                        onSortChange("top")
                        topMenuExpanded = false
                    }
                )
                androidx.compose.material3.DropdownMenuItem(
                    text = { Text("Top · All") },
                    onClick = {
                        onSortChange("top")
                        topMenuExpanded = false
                    }
                )
            }
        }
        FilterChip("comments", sort, onSelect = onSortChange, label = stringResource(R.string.sort_discussed), width = 84.dp)
        FilterChip("random", sort, onSelect = onSortChange, label = stringResource(R.string.sort_random), width = 84.dp)
    }
}

@Composable
private fun FilterChip(
    value: String,
    selectedValue: String,
    onSelect: (String) -> Unit,
    label: String = value,
    width: Dp? = null
) {
    val selected = value == selectedValue
    Card(
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(containerColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .height(30.dp)
            .then(if (width != null) Modifier.width(width) else Modifier.widthIn(min = 72.dp)),
        onClick = { onSelect(value) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .height(18.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
private fun PostCard(
    post: Post,
    topComment: Comment?,
    translation: TranslationState?,
    isFavorited: Boolean,
    isAlt: Boolean,
    onToggleFavorite: () -> Unit,
    onOpenDetails: () -> Unit,
    onOpenUser: (String, String?) -> Unit,
    onOpenSubmolt: (String) -> Unit,
    onLoadTopComment: (String) -> Unit,
    onTranslate: (TranslationRequest) -> Unit
) {
    val context = LocalContext.current
    // NOTE: Top comments are batched at the screen level to avoid incremental UI jumps.
    val isDark = LocalIsDark.current

    val baseColor = if (isDark) MaterialTheme.colorScheme.surface else Color(0xFFF4F6FA)
    val altColor = if (isDark) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f) else Color(0xFFD6DEE8)
    val score = (post.upvotes - (post.downvotes ?: 0)).coerceAtLeast(0L)

    fun abbreviateTail(text: String, maxChars: Int): String {
        if (text.length <= maxChars) return text
        if (maxChars <= 1) return "…"
        return "…" + text.takeLast(maxChars - 1)
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = if (isAlt) altColor else baseColor),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = if (isDark) 0.15f else 0.75f)
        ),
        onClick = onOpenDetails
    ) {
        Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val submoltName = post.submolt?.name
                    if (!submoltName.isNullOrBlank()) {
                        Text(
                            text = "m/$submoltName",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { onOpenSubmolt(submoltName) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }

                    val authorName = post.author?.name?.takeIf { !it.isNullOrBlank() } ?: "unknown"
                    val canOpenUser = authorName != "unknown"
                    Text(
                        text = "u/${abbreviateTail(authorName, 18)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        softWrap = false,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .clickable(enabled = canOpenUser) { onOpenUser(authorName, post.author?.id) }
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.35f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = formatTimeAgo(post.createdAt),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                IconButton(onClick = onToggleFavorite, modifier = Modifier.size(30.dp)) {
                    Icon(
                        imageVector = if (isFavorited) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(18.dp),
                        tint = if (isFavorited) Color(0xFFE11D48) else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    onClick = {
                        onTranslate(
                            TranslationRequest(
                                title = post.title,
                                content = post.content,
                                comment = topComment?.content
                            )
                        )
                    },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(Icons.Rounded.Translate, contentDescription = "Translate", modifier = Modifier.size(18.dp))
                }
                IconButton(
                    onClick = { sharePost(context, post) },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(Icons.Rounded.Share, contentDescription = "Share", modifier = Modifier.size(18.dp))
                }
            }
            Text(
                post.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = normalizeContent(post.content),
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 16.sp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            @Composable
            fun Capsule(text: String, bg: Color) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(bg)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
            @Composable
            fun ScoreCapsule(scoreText: String, bg: Color) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(bg)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.TrendingUp,
                            contentDescription = null,
                            modifier = Modifier.size(12.dp),
                            tint = Color.White
                        )
                        Text(
                            text = scoreText,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }
            }
            val hasCommentCount = post.commentCount > 0
            if (score > 0 || hasCommentCount || topComment != null) {
                Divider()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (topComment != null) {
                        Capsule(stringResource(R.string.chip_comments), Color(0xFFEA580C).copy(alpha = 0.55f))
                    }
                    if (hasCommentCount) {
                        Capsule("💬 ${formatCount(post.commentCount)}", Color(0xFF2563EB).copy(alpha = 0.50f))
                    }
                    if (score > 0) {
                        ScoreCapsule(formatCount(score), Color(0xFF16A34A).copy(alpha = 0.55f))
                    }
                }
            }
            if (topComment != null) {
                Text(
                    text = topComment.content ?: "",
                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 16.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (translation != null) {
                when {
                    translation.isLoading -> Text(stringResource(R.string.translating), style = MaterialTheme.typography.bodySmall)
                    translation.error != null -> Text(translation.error, color = MaterialTheme.colorScheme.error)
                    translation.translatedTitle != null ||
                        translation.translatedContent != null ||
                        translation.translatedComment != null -> {
                        Divider()
                        translation.translatedTitle?.let {
                            Text(it, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                        }
                        translation.translatedContent?.let {
                            Text(it, style = MaterialTheme.typography.bodySmall)
                        }
                        translation.translatedComment?.let {
                            Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PostDetailScreen(
    post: Post,
    repository: MoltbookRepository,
    isFollowed: Boolean,
    onToggleFollow: () -> Unit,
    onBack: () -> Unit,
    onOpenUser: (String, String?) -> Unit,
    onOpenSubmolt: (String) -> Unit
) {
    val context = LocalContext.current
    val languageIdentifier = rememberLanguageIdentifier()
    val commentSubject = stringResource(R.string.share_comment_subject)
    var commentsState by remember { mutableStateOf<UiState<List<Comment>>>(UiState.Loading) }
    var postTranslation by remember { mutableStateOf<TranslationState?>(null) }
    val commentTranslations = remember { mutableStateMapOf<String, TranslationState>() }
    val commentFavorites = remember { mutableStateMapOf<String, Boolean>() }
    var isFavorited by remember(post.id) { mutableStateOf(isFollowed) }
    val expandedComments = remember { mutableStateMapOf<String, Boolean>() }

    LaunchedEffect(post.id) {
        commentsState = UiState.Loading
        commentsState = try {
            val response = repository.getComments(post.id)
            UiState.Success(response.comments)
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
        }
    }

    BackHandler(onBack = onBack)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DetailTopBar(title = stringResource(R.string.title_post), onBack = onBack)
        SectionCard(modifier = Modifier.weight(1f)) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val submoltName = post.submolt?.name
                        if (!submoltName.isNullOrBlank()) {
                            Text(
                                text = "m/$submoltName",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.clickable { onOpenSubmolt(submoltName) }
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        val authorName = post.author?.name
                        if (!authorName.isNullOrBlank()) {
                            Text(
                                text = "u/$authorName",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.clickable { onOpenUser(authorName, post.author?.id) }
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = formatTimeAgo(post.createdAt),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        val score = (post.upvotes - (post.downvotes ?: 0)).coerceAtLeast(0)
                        if (score > 0) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(999.dp))
                                    .background(Color(0xFF16A34A).copy(alpha = 0.30f))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Rounded.TrendingUp,
                                        contentDescription = null,
                                        modifier = Modifier.size(12.dp),
                                        tint = Color.White.copy(alpha = 0.92f)
                                    )
                                    Text(
                                        text = formatCount(score),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White.copy(alpha = 0.92f)
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Text(
                        post.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(stringResource(R.string.content_label), style = MaterialTheme.typography.labelMedium)
                        Spacer(modifier = Modifier.weight(1f))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(
                                onClick = { onToggleFollow(); isFavorited = !isFavorited },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(
                                    imageVector = if (isFavorited) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(20.dp),
                                    tint = if (isFavorited) Color(0xFFE11D48) else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            IconButton(
                                onClick = {
                                    requestTranslation(
                                        TranslationRequest(title = post.title, content = post.content),
                                        languageIdentifier
                                    ) { postTranslation = it }
                                },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.Rounded.Translate, contentDescription = "Translate", modifier = Modifier.size(20.dp))
                            }
                            IconButton(
                                onClick = { sharePost(context, post) },
                                modifier = Modifier.size(32.dp)
                            ) {
                                Icon(Icons.Rounded.Share, contentDescription = "Share", modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }
                item {
                    Text(
                        text = post.content ?: stringResource(R.string.empty_content),
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 16.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                item {
                    postTranslation?.let { state ->
                        when {
                            state.isLoading -> Text(stringResource(R.string.translating), style = MaterialTheme.typography.bodySmall)
                            state.error != null -> Text(state.error, color = MaterialTheme.colorScheme.error)
                            state.translatedTitle != null || state.translatedContent != null -> {
                                Divider()
                                state.translatedTitle?.let {
                                    Text(it, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                                }
                                state.translatedContent?.let {
                                    Text(it, style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
                item {
                    Divider()
                    Text(
                        text = stringResource(R.string.post_comments_header, formatCount(post.commentCount)),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                when (val state = commentsState) {
                    UiState.Loading -> item { Text(stringResource(R.string.loading_comments), style = MaterialTheme.typography.bodySmall) }
                    is UiState.Error -> item { Text(state.message, color = MaterialTheme.colorScheme.error) }
                    is UiState.Success -> {
                        items(state.data) { comment ->
                            val isCommentFav = commentFavorites[comment.id] == true
                            CommentThread(
                                comment = comment,
                                depth = 0,
                                expandedComments = expandedComments,
                                onToggleExpand = { id ->
                                    expandedComments[id] = !(expandedComments[id] == true)
                                },
                                onTranslate = { id, content ->
                                    commentTranslations[id] = TranslationState(isLoading = true)
                                    requestTranslation(
                                        TranslationRequest(
                                            title = commentSubject,
                                            content = content
                                        ),
                                        languageIdentifier
                                    ) { commentTranslations[id] = it }
                                },
                                onShare = { content ->
                                    shareComment(context, post, content)
                                },
                                onFavorite = {
                                    commentFavorites[comment.id] = !isCommentFav
                                },
                                translationMap = commentTranslations,
                                onOpenUser = onOpenUser,
                                showOriginal = isCommentFav,
                                originalTitle = post.title,
                                originalContent = post.content ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UserDetailScreen(
    name: String,
    repository: MoltbookRepository,
    homepageState: UiState<HomepageResponse>,
    postsState: UiState<List<Post>>,
    followedPosts: Map<String, FollowedPost>,
    onToggleFollowPost: (Post) -> Unit,
    onBack: () -> Unit,
    onOpenPost: (Post) -> Unit,
    onOpenSubmolt: (String) -> Unit,
    onOpenUser: (String, String?) -> Unit
) {
    val context = LocalContext.current
    BackHandler(onBack = onBack)
    val userTitle = stringResource(R.string.title_user)
    val translations = remember(name) { mutableStateMapOf<String, TranslationState>() }
    val languageIdentifier = rememberLanguageIdentifier()
    var isFavorited by remember(name) { mutableStateOf(false) }
    var userPostsState by remember(name) { mutableStateOf<UiState<List<Post>>>(UiState.Loading) }
    val topComments = remember(name) { mutableStateMapOf<String, Comment?>() }
    val postTranslations = remember(name) { mutableStateMapOf<String, TranslationState>() }

    LaunchedEffect(name) {
        userPostsState = try {
            UiState.Success(repository.getPostsByAuthor(name, limit = 20).posts)
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
        }
    }

    val userPosts = (userPostsState as? UiState.Success)?.data.orEmpty()
    val posts = buildList {
        if (homepageState is UiState.Success) addAll(homepageState.data.posts)
        if (postsState is UiState.Success) addAll(postsState.data)
        addAll(userPosts)
    }.distinctBy { it.id }.filter { it.author?.name == name }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DetailTopBar(title = stringResource(R.string.title_user), onBack = onBack)
        SectionCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(name.take(1).uppercase(), style = MaterialTheme.typography.titleMedium)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("u/$name", fontWeight = FontWeight.SemiBold)
                    Text(
                        text = stringResource(R.string.user_home_title),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    IconButton(
                        onClick = {
                            translations["profile"] = TranslationState(isLoading = true)
                            requestTranslation(
                                TranslationRequest(title = userTitle, content = name),
                                languageIdentifier
                            ) { translations["profile"] = it }
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Rounded.Translate, contentDescription = "Translate", modifier = Modifier.size(18.dp))
                    }
                    IconButton(
                        onClick = { shareText(context, userTitle, name) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Rounded.Share, contentDescription = "Share", modifier = Modifier.size(18.dp))
                    }
                    IconButton(
                        onClick = { isFavorited = !isFavorited },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isFavorited) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(18.dp),
                            tint = if (isFavorited) Color(0xFFE11D48) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            translations["profile"]?.translatedContent?.let {
                Spacer(modifier = Modifier.height(6.dp))
                Text(it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        SectionTitle(stringResource(R.string.recent_posts))
        when (val state = userPostsState) {
            UiState.Loading -> SectionCard(modifier = Modifier.weight(1f)) { Text(stringResource(R.string.loading)) }
            is UiState.Error -> SectionCard(modifier = Modifier.weight(1f)) { Text(state.message, color = MaterialTheme.colorScheme.error) }
            is UiState.Success -> {
                val postsReady = posts.isNotEmpty() && posts.all { p ->
                    p.commentCount == 0L || topComments.containsKey(p.id)
                }
                var loadingTop by remember(name) { mutableStateOf(false) }

                LaunchedEffect(name, posts.map { it.id }) {
                    if (posts.isEmpty()) return@LaunchedEffect
                    if (postsReady || loadingTop) return@LaunchedEffect
                    loadingTop = true
                    val map = runCatching { loadTopCommentsForPosts(repository, posts) }.getOrDefault(emptyMap())
                    topComments.putAll(map)
                    loadingTop = false
                }

                if (posts.isEmpty()) {
                    SectionCard(modifier = Modifier.weight(1f)) { Text(stringResource(R.string.empty_content)) }
                } else if (!postsReady || loadingTop) {
                    SectionCard(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(strokeWidth = 2.dp)
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(posts, key = { it.id }) { post ->
                            PostCard(
                                post = post,
                                topComment = topComments[post.id],
                                translation = postTranslations[post.id],
                                isFavorited = followedPosts.containsKey(post.id),
                                isAlt = false,
                                onToggleFavorite = { onToggleFollowPost(post) },
                                onOpenDetails = { onOpenPost(post) },
                                onOpenUser = { name, id -> onOpenUser(name, id) },
                                onOpenSubmolt = { onOpenSubmolt(it) },
                                onLoadTopComment = {},
                                onTranslate = { request ->
                                    postTranslations[post.id] = TranslationState(isLoading = true)
                                    requestTranslation(request, languageIdentifier) { postTranslations[post.id] = it }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SubmoltDetailScreen(
    name: String,
    repository: MoltbookRepository,
    homepageState: UiState<HomepageResponse>,
    postsState: UiState<List<Post>>,
    isFollowed: Boolean,
    onToggleFollow: (SubmoltDetail) -> Unit,
    followedPosts: Map<String, FollowedPost>,
    onToggleFollowPost: (Post) -> Unit,
    onBack: () -> Unit,
    onOpenPost: (Post) -> Unit,
    onOpenUser: (String, String?) -> Unit,
    onOpenSubmolt: (String) -> Unit,
    snapshot: SubmoltScreenSnapshot?,
    onUpdateSnapshot: (SubmoltScreenSnapshot) -> Unit
) {
    val context = LocalContext.current
    val languageIdentifier = rememberLanguageIdentifier()
    val bioTitle = stringResource(R.string.profile_bio)
    val submoltShareTitle = stringResource(R.string.submolts_title)
    val submoltSeed = remember(name) {
        val s = (homepageState as? UiState.Success)?.data?.submolts?.firstOrNull { it.name == name }
        if (s == null) null else SubmoltDetail(
            id = s.id,
            name = s.name,
            displayName = s.displayName,
            description = s.description,
            subscriberCount = s.subscriberCount,
            createdAt = s.createdAt
        )
    }
    var submoltDetail by remember(name) {
        mutableStateOf<UiState<SubmoltDetail>>(
            snapshot?.detail?.let { UiState.Success(it) }
                ?: submoltSeed?.let { UiState.Success(it) }
                ?: UiState.Loading
        )
    }
    var descriptionTranslation by remember(name) { mutableStateOf<TranslationState?>(null) }
    val topComments = remember(name) { mutableStateMapOf<String, Comment?>() }
    val translations = remember(name) { mutableStateMapOf<String, TranslationState>() }
    val scope = rememberCoroutineScope()
    var isFollowedLocal by remember(name) { mutableStateOf(isFollowed) }
    val hasSnapshot = snapshot != null
    var postsStateLocal by remember(name) {
        mutableStateOf<UiState<List<Post>>>(
            snapshot?.posts?.takeIf { it.isNotEmpty() }?.let { UiState.Success(it) } ?: UiState.Loading
        )
    }
    val submolt = (homepageState as? UiState.Success)?.data?.submolts?.firstOrNull { it.name == name }

    LaunchedEffect(name) {
        if (hasSnapshot) {
            topComments.clear()
            topComments.putAll(snapshot!!.topComments)
        }
    }

    LaunchedEffect(name) {
        if (hasSnapshot) return@LaunchedEffect

        val detail = runCatching { repository.getSubmoltDetail(name).submolt }
            .getOrElse { err ->
                // Keep existing seed if available; don't flash to loading.
                val hadSeed = (submoltDetail as? UiState.Success)?.data != null
                if (!hadSeed) submoltDetail = UiState.Error(err.localizedMessage ?: context.getString(R.string.load_failed))
                null
            }
        if (detail != null) submoltDetail = UiState.Success(detail)

        postsStateLocal = UiState.Loading
        postsStateLocal = try {
            val posts = repository.getPostsBySubmolt(name, limit = 20).posts
            val topMap = runCatching { loadTopCommentsForPosts(repository, posts) }.getOrDefault(emptyMap())
            topComments.clear()
            topComments.putAll(topMap)
            onUpdateSnapshot(SubmoltScreenSnapshot(detail = detail ?: (submoltDetail as? UiState.Success)?.data, posts = posts, topComments = topMap))
            UiState.Success(posts)
        } catch (e: Exception) {
            UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
        }
    }

    LaunchedEffect(name, isFollowed) { isFollowedLocal = isFollowed }

    BackHandler(onBack = onBack)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            // Align the header card's top edge with AppTopBar's top edge (status bar + 2dp).
            .statusBarsPadding()
            .padding(top = 2.dp)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1F2937)),
            modifier = Modifier.fillMaxWidth()
        ) {
            val memberCount = submolt?.subscriberCount
                ?: (submoltDetail as? UiState.Success)?.data?.subscriberCount
                ?: 0
            val descriptionText = when (val state = submoltDetail) {
                UiState.Loading -> stringResource(R.string.loading_profile)
                is UiState.Error -> stringResource(R.string.empty_description)
                is UiState.Success -> state.data.description ?: stringResource(R.string.empty_description)
            }
            val gradient = remember(name) {
                val palettes = listOf(
                    listOf(Color(0xFF1E3A8A), Color(0xFF0F172A)),
                    listOf(Color(0xFF6D28D9), Color(0xFF0F172A)),
                    listOf(Color(0xFFB45309), Color(0xFF1F2937)),
                    listOf(Color(0xFF0F766E), Color(0xFF0F172A)),
                    listOf(Color(0xFF7F1D1D), Color(0xFF1F2937))
                )
                palettes[kotlin.math.abs(name.hashCode()) % palettes.size]
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = gradient
                        )
                    )
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFF9FAFB)
                )
                Text(
                    text = descriptionText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFE5E7EB),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${formatCount(memberCount)} ${stringResource(R.string.members)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFD1D5DB)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    IconButton(
                        onClick = {
                            val detail = (submoltDetail as? UiState.Success)?.data
                            if (detail != null) {
                                onToggleFollow(detail)
                                isFollowedLocal = !isFollowedLocal
                            }
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (isFollowedLocal) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(18.dp),
                            tint = if (isFollowedLocal) Color(0xFFF97316) else Color(0xFFE5E7EB)
                        )
                    }
                    IconButton(
                        onClick = {
                            requestTranslation(
                                TranslationRequest(title = bioTitle, content = descriptionText),
                                languageIdentifier
                            ) { descriptionTranslation = it }
                        },
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(28.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Translate,
                            contentDescription = "Translate",
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFFE5E7EB)
                        )
                    }
                    IconButton(
                        onClick = { shareText(context, submoltShareTitle, "https://www.moltbook.com/m/$name") },
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(32.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Share,
                            contentDescription = "Share",
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFFE5E7EB)
                        )
                    }
                }
            }
            when (val state = submoltDetail) {
                is UiState.Success -> {
                    // handled above
                }
                else -> {}
            }
            descriptionTranslation?.translatedContent?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFE5E7EB),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF0F172A))
                        .padding(top = 4.dp, bottom = 8.dp)
                )
            }
        }
        SectionTitle(stringResource(R.string.submolt_content_title))
        when (val state = postsStateLocal) {
            UiState.Loading -> SectionCard(modifier = Modifier.weight(1f)) { Text(stringResource(R.string.loading)) }
            is UiState.Error -> SectionCard(modifier = Modifier.weight(1f)) { Text(state.message, color = MaterialTheme.colorScheme.error) }
            is UiState.Success -> {
                val posts = state.data
                var loadingTop by remember(name) { mutableStateOf(false) }

                LaunchedEffect(name, posts.map { it.id }) {
                    if (hasSnapshot) return@LaunchedEffect
                    if (posts.isEmpty()) return@LaunchedEffect
                    if (loadingTop) return@LaunchedEffect
                    val missing = posts.any { p -> p.commentCount > 0L && !topComments.containsKey(p.id) }
                    if (!missing) return@LaunchedEffect
                    loadingTop = true
                    val map = runCatching { loadTopCommentsForPosts(repository, posts) }.getOrDefault(emptyMap())
                    topComments.putAll(map)
                    onUpdateSnapshot(SubmoltScreenSnapshot(detail = (submoltDetail as? UiState.Success)?.data, posts = posts, topComments = topComments.toMap()))
                    loadingTop = false
                }

                if (posts.isEmpty()) {
                    SectionCard(modifier = Modifier.weight(1f)) { Text(stringResource(R.string.empty_posts)) }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(posts, key = { it.id }) { post ->
                            PostCard(
                                post = post,
                                topComment = topComments[post.id],
                                translation = translations[post.id],
                                isFavorited = followedPosts.containsKey(post.id),
                                isAlt = false,
                                onToggleFavorite = { onToggleFollowPost(post) },
                                onOpenDetails = { onOpenPost(post) },
                                onOpenUser = { name, id -> onOpenUser(name, id) },
                                onOpenSubmolt = { onOpenSubmolt(it) },
                                onLoadTopComment = {},
                                onTranslate = { request ->
                                    translations[post.id] = TranslationState(isLoading = true)
                                    requestTranslation(request, languageIdentifier) { translations[post.id] = it }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PairingDetailScreen(
    pairing: TopHuman,
    onBack: () -> Unit,
    onOpenX: (String) -> Unit
) {
    val context = LocalContext.current
    BackHandler(onBack = onBack)
    val pairingTitle = stringResource(R.string.title_pairing)
    var isFavorited by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DetailTopBar(title = stringResource(R.string.title_pairing), onBack = onBack)
        SectionCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = pairing.xAvatar,
                    contentDescription = null,
                    modifier = Modifier.size(54.dp).clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(pairing.botName ?: "Pairing", fontWeight = FontWeight.SemiBold)
                    Text(
                        text = pairing.xHandle?.let { "@$it" } ?: "",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable { pairing.xHandle?.let { onOpenX(it) } }
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    IconButton(onClick = { isFavorited = !isFavorited }, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = if (isFavorited) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(18.dp),
                            tint = if (isFavorited) Color(0xFFE11D48) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(
                        onClick = { shareText(context, pairingTitle, pairing.botName ?: "") },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(Icons.Rounded.Share, contentDescription = "Share", modifier = Modifier.size(18.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.followers_count, formatCount(pairing.xFollowerCount ?: 0)),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun AgentDetailScreen(
    agent: com.moltbook.app.data.Agent,
    repository: MoltbookRepository,
    homepageState: UiState<HomepageResponse>,
    postsState: UiState<List<Post>>,
    preloadedPosts: Map<String, List<Post>>,
    preloadedComments: Map<String, List<AgentCommentItem>>,
    agentStats: com.moltbook.app.data.AgentStats?,
    debugInfo: String?,
    isFollowed: Boolean,
    onToggleFollow: () -> Unit,
    followedPosts: Map<String, FollowedPost>,
    onToggleFollowPost: (Post) -> Unit,
    onBack: () -> Unit,
    onOpenPost: (Post) -> Unit,
    onOpenX: (String) -> Unit,
    onOpenUser: (String, String?) -> Unit,
    onOpenSubmolt: (String) -> Unit
) {
    val context = LocalContext.current
    BackHandler(onBack = onBack)
    val cachedProfile = remember(agent.name) { repository.getCachedAgentProfile(agent.name) }
    var agentPostsState by remember(agent.name, agent.id) {
        mutableStateOf<UiState<List<Post>>>(
            preloadedPosts[agent.name]?.let { UiState.Success(it) }
                ?: cachedProfile?.recentPosts?.let { UiState.Success(it) }
                ?: UiState.Loading
        )
    }
    val topComments = remember(agent.name, agent.id) { mutableStateMapOf<String, Comment?>() }
    val translations = remember(agent.name, agent.id) { mutableStateMapOf<String, TranslationState>() }
    val languageIdentifier = rememberLanguageIdentifier()
    val scope = rememberCoroutineScope()
    var agentCommentsState by remember(agent.name, agent.id) {
        mutableStateOf<UiState<List<AgentCommentItem>>>(
            preloadedComments[agent.name]?.let { UiState.Success(it) }
                ?: cachedProfile?.let { profile ->
                    UiState.Success(
                        profile.recentComments.map { rc ->
                            val postRef = rc.post
                            val post = Post(
                                id = postRef.id,
                                title = postRef.title ?: "(untitled)",
                                content = null,
                                url = null,
                                upvotes = 0,
                                downvotes = 0,
                                commentCount = postRef.commentCount ?: 0,
                                createdAt = postRef.createdAt,
                                submolt = postRef.submolt,
                                author = Author(id = profile.agent.id, name = profile.agent.name)
                            )
                            val comment = Comment(
                                id = rc.id,
                                content = rc.content,
                                parentId = null,
                                upvotes = rc.upvotes,
                                downvotes = rc.downvotes,
                                createdAt = rc.createdAt,
                                author = CommentAuthor(id = profile.agent.id, name = profile.agent.name),
                                replies = emptyList()
                            )
                            AgentCommentItem(post = post, comment = comment)
                        }
                    )
                }
                ?: UiState.Loading
        )
    }
    val commentTranslations = remember(agent.name, agent.id) { mutableStateMapOf<String, TranslationState>() }
    val commentFavorites = remember(agent.name, agent.id) { mutableStateMapOf<String, Boolean>() }
    var tab by remember(agent.name, agent.id) { mutableStateOf("posts") }
    val joined = formatDate(agent.createdAt) ?: "2026/1/30"
    var isFollowedLocal by remember(agent.name, agent.id) { mutableStateOf(isFollowed) }
    var ownerPulse by remember(agent.name, agent.id) { mutableStateOf(false) }
    val ownerScale by animateFloatAsState(
        targetValue = if (ownerPulse) 1.12f else 1.0f,
        animationSpec = tween(durationMillis = 180),
        label = "ownerScale"
    )

    LaunchedEffect(agent.name, agent.id) {
        // Canonical data path: /api/v1/agents/profile?name={name}
        if (preloadedPosts[agent.name] != null && preloadedComments[agent.name] != null) return@LaunchedEffect
        try {
            val profile = repository.getAgentProfile(agent.name)
            agentPostsState = UiState.Success(profile.recentPosts)
            agentCommentsState = UiState.Success(
                profile.recentComments.map { rc ->
                    val postRef = rc.post
                    val post = Post(
                        id = postRef.id,
                        title = postRef.title ?: "(untitled)",
                        content = null,
                        url = null,
                        upvotes = 0,
                        downvotes = 0,
                        commentCount = postRef.commentCount ?: 0,
                        createdAt = postRef.createdAt,
                        submolt = postRef.submolt,
                        author = Author(id = profile.agent.id, name = profile.agent.name)
                    )
                    val comment = Comment(
                        id = rc.id,
                        content = rc.content,
                        parentId = null,
                        upvotes = rc.upvotes,
                        downvotes = rc.downvotes,
                        createdAt = rc.createdAt,
                        author = CommentAuthor(id = profile.agent.id, name = profile.agent.name),
                        replies = emptyList()
                    )
                    AgentCommentItem(post = post, comment = comment)
                }
            )
        } catch (e: Exception) {
            agentPostsState = UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
            agentCommentsState = UiState.Error(e.localizedMessage ?: context.getString(R.string.load_failed))
        }
    }

    LaunchedEffect(preloadedPosts[agent.name]) {
        preloadedPosts[agent.name]?.let {
            agentPostsState = UiState.Success(it)
        }
    }

    LaunchedEffect(agent.name, isFollowed) {
        isFollowedLocal = isFollowed
    }

    val posts = (agentPostsState as? UiState.Success)?.data.orEmpty()
    val commentItems = (agentCommentsState as? UiState.Success)?.data.orEmpty()
    val karma = agent.karma ?: posts.sumOf { (it.upvotes - (it.downvotes ?: 0)).coerceAtLeast(0) }
    
    val displayPostCount =
        if (agentStats != null && agentStats.postCount > 0) agentStats.postCount else posts.size.toLong()
    val displayCommentCount =
        if (agentStats != null && agentStats.commentCount > 0) agentStats.commentCount else commentItems.size.toLong()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            // Align the header card's top edge with AppTopBar's top edge (status bar + 2dp).
            .statusBarsPadding()
            .padding(top = 2.dp)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Agent Header Card
        val agentGradient = remember(agent.name) {
            val palettes = listOf(
                listOf(Color(0xFF1D4ED8), Color(0xFF0F172A)),
                listOf(Color(0xFF7C3AED), Color(0xFF0F172A)),
                listOf(Color(0xFFF97316), Color(0xFF1F2937)),
                listOf(Color(0xFF0F766E), Color(0xFF0F172A)),
                listOf(Color(0xFFB91C1C), Color(0xFF1F2937))
            )
            palettes[kotlin.math.abs(agent.name.hashCode()) % palettes.size]
        }
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.verticalGradient(colors = agentGradient))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Row 1: Agent avatar+name centered as a unit; reserve right space to avoid overlap.
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val reservedRight = 74.dp // link icon + gap + owner avatar (prevents long names overlapping right)
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(CircleShape)
                                        .background(Color.White.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Align with website: default letter avatar.
                                    Text(
                                        agent.name.take(1).uppercase(),
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .size(9.dp)
                                        .align(Alignment.BottomEnd)
                                        .clip(CircleShape)
                                        .background(if (agent.isActive == true) Color(0xFF22C55E) else Color(0xFF94A3B8))
                                        .border(1.dp, Color(0xFF0F172A), CircleShape)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                agent.name,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                maxLines = 2,
                                overflow = TextOverflow.Clip,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Right owner avatar + chain icon
                    val owner = agent.owner
                    Row(
                        modifier = Modifier.width(reservedRight),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Link,
                            contentDescription = null,
                            tint = Color.White.copy(alpha = 0.65f),
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(18.dp)
                        )
                        Box(
                            modifier = Modifier
                                .graphicsLayer(scaleX = ownerScale, scaleY = ownerScale)
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.15f))
                                .border(
                                    width = if (ownerPulse) 2.dp else 1.dp,
                                    color = if (ownerPulse) Color(0xFFF97316) else Color.White.copy(alpha = 0.25f),
                                    shape = CircleShape
                                )
                                .clickable(enabled = owner?.xHandle != null) {
                                    ownerPulse = true
                                    scope.launch {
                                        delay(220)
                                        ownerPulse = false
                                    }
                                    owner?.xHandle?.let { onOpenX(it) }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            // Owner avatar comes from API and points to pbs.twimg.com on the web.
                            if (!owner?.xAvatar.isNullOrBlank()) {
                                AsyncImage(
                                    model = owner?.xAvatar,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                val initial = (owner?.xName ?: owner?.xHandle ?: "X").take(1).uppercase()
                                Text(
                                    initial,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                // Row 2: Description
                Text(
                    text = agent.description ?: "AI Agent showing off its capabilities.",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFE5E7EB),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )

                // Row 3: Stats (icons only, one line)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    @Composable
                    fun Capsule(text: String, bg: Color) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(999.dp))
                                .background(bg)
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = text,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    Capsule("💪 ${formatCount(agent.karma ?: karma)}", Color(0xFFEA580C).copy(alpha = 0.35f))
                    Capsule("🎂 $joined", Color(0xFF2563EB).copy(alpha = 0.35f))
                    Capsule("🤖 ${formatCount(agent.followerCount ?: 0)}", Color(0xFF16A34A).copy(alpha = 0.35f))
                    Capsule("🏹 ${formatCount(agent.followingCount ?: 0)}", Color(0xFFA855F7).copy(alpha = 0.30f))
                }

                // Row 4: Owner handle + Follow + Share
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val handle = agent.owner?.xHandle
                    val partnerLabel = stringResource(R.string.human_partner)
                    Text(
                        text = if (handle.isNullOrBlank()) "$partnerLabel: —" else "$partnerLabel: @$handle",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFE5E7EB),
                        modifier = Modifier
                            .clickable(enabled = !handle.isNullOrBlank()) { handle?.let { onOpenX(it) } }
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    IconButton(
                        onClick = {
                            onToggleFollow()
                            isFollowedLocal = !isFollowedLocal
                        },
                        modifier = Modifier.size(22.dp)
                    ) {
                        Icon(
                            imageVector = if (isFollowedLocal) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Follow",
                            tint = if (isFollowedLocal) Color(0xFFF97316) else Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(
                        onClick = { shareText(context, "Agent", "https://www.moltbook.com/u/${agent.name}") },
                        modifier = Modifier.size(22.dp)
                    ) {
                        Icon(Icons.Rounded.Share, contentDescription = "Share", tint = Color.White)
                    }
                }
            }
        }
        SectionCard(containerColor = if (LocalIsDark.current) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.82f)) {
            val tabIndex = if (tab == "posts") 0 else 1
            val tabTextStyle = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Transparent)
                    .border(
                        1.dp,
                        if (LocalIsDark.current) Color.White.copy(alpha = 0.12f)
                        else MaterialTheme.colorScheme.outline.copy(alpha = 0.65f),
                        RoundedCornerShape(12.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)
                        .align(if (tabIndex == 0) Alignment.CenterStart else Alignment.CenterEnd)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (LocalIsDark.current) Color.White.copy(alpha = 0.14f)
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.06f)
                        )
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { tab = "posts" },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "📝 ${stringResource(R.string.tab_posts)} (${formatCount(displayPostCount)})",
                            style = tabTextStyle,
                            color = if (tabIndex == 0) (if (LocalIsDark.current) Color.White else MaterialTheme.colorScheme.onSurface)
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { tab = "comments" },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "💬 ${stringResource(R.string.tab_comments)} (${formatCount(displayCommentCount)})",
                            style = tabTextStyle,
                            color = if (tabIndex == 1) (if (LocalIsDark.current) Color.White else MaterialTheme.colorScheme.onSurface)
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
        if (tab == "posts") {
            when (val state = agentPostsState) {
                UiState.Loading -> SectionCard(modifier = Modifier.weight(1f)) { Text(stringResource(R.string.loading)) }
                is UiState.Error -> SectionCard(modifier = Modifier.weight(1f)) { Text(state.message, color = MaterialTheme.colorScheme.error) }
                is UiState.Success -> {
                    val posts = state.data
                    var loadingTop by remember(agent.name, agent.id) { mutableStateOf(false) }

                    LaunchedEffect(tab, posts.map { it.id }) {
                        if (tab != "posts") return@LaunchedEffect
                        if (posts.isEmpty()) return@LaunchedEffect
                        if (loadingTop) return@LaunchedEffect
                        loadingTop = true
                        val map = runCatching { loadTopCommentsForPosts(repository, posts) }.getOrDefault(emptyMap())
                        topComments.putAll(map)
                        loadingTop = false
                    }

                    if (posts.isEmpty()) {
                        SectionCard(modifier = Modifier.weight(1f)) { Text(stringResource(R.string.empty_content)) }
                    } else {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            items(posts, key = { it.id }) { post ->
                                PostCard(
                                    post = post,
                                    topComment = topComments[post.id],
                                    translation = translations[post.id],
                                    isFavorited = followedPosts.containsKey(post.id),
                                    isAlt = false,
                                    onToggleFavorite = { onToggleFollowPost(post) },
                                    onOpenDetails = { onOpenPost(post) },
                                    onOpenUser = { name, id -> onOpenUser(name, id) },
                                    onOpenSubmolt = { onOpenSubmolt(it) },
                                    onLoadTopComment = {},
                                    onTranslate = { request ->
                                        translations[post.id] = TranslationState(isLoading = true)
                                        requestTranslation(request, languageIdentifier) { translations[post.id] = it }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        } else {
            when (val state = agentCommentsState) {
                UiState.Loading -> SectionCard(modifier = Modifier.weight(1f)) { Text(stringResource(R.string.loading_comments)) }
                is UiState.Error -> SectionCard(modifier = Modifier.weight(1f)) { Text(state.message, color = MaterialTheme.colorScheme.error) }
                is UiState.Success -> {
                    if (state.data.isEmpty()) {
                        SectionCard(modifier = Modifier.weight(1f)) { Text(stringResource(R.string.empty_comments)) }
                    } else {
                        val commentSubject = stringResource(R.string.share_comment_subject)
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            items(state.data, key = { it.comment.id }) { item ->
                                val comment = item.comment
                                val isFav = commentFavorites[comment.id] == true
                                SectionCard {
                                    Text(
                                        text = item.post.title,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = formatTimeAgo(comment.createdAt),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = comment.content ?: "",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    if (isFav) {
                                        Text(
                                            text = stringResource(R.string.original_prefix) +
                                                "${item.post.title} ${normalizeContent(item.post.content).take(80)}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                    commentTranslations[comment.id]?.let { ts ->
                                        when {
                                            ts.isLoading -> Text(stringResource(R.string.translating), style = MaterialTheme.typography.bodySmall)
                                            ts.error != null -> Text(ts.error, color = MaterialTheme.colorScheme.error)
                                            ts.translatedContent != null -> Text(
                                                ts.translatedContent,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        IconButton(
                                            onClick = { commentFavorites[comment.id] = !isFav },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(
                                                imageVector = if (isFav) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                                                contentDescription = "Favorite",
                                                modifier = Modifier.size(14.dp),
                                                tint = if (isFav) Color(0xFFE11D48) else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        IconButton(
                                            onClick = {
                                                commentTranslations[comment.id] = TranslationState(isLoading = true)
                                                requestTranslation(
                                                    TranslationRequest(
                                                        title = commentSubject,
                                                        content = comment.content
                                                    ),
                                                    languageIdentifier
                                                ) { commentTranslations[comment.id] = it }
                                            },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(Icons.Rounded.Translate, contentDescription = "Translate", modifier = Modifier.size(14.dp))
                                        }
                                        IconButton(
                                            onClick = { shareComment(context, item.post, comment.content ?: "") },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(Icons.Rounded.Share, contentDescription = "Share", modifier = Modifier.size(14.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack, modifier = Modifier.size(36.dp)) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun PairingsBoard(
    pairings: List<TopHuman>,
    onOpenPairing: (TopHuman) -> Unit,
    onOpenX: (String) -> Unit
) {
    SectionCard {
        LazyColumn(
            modifier = Modifier.heightIn(max = (5 * 56).dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(pairings) { pairing ->
                val avatarColor = remember(pairing.xHandle ?: pairing.xName ?: "user") {
                    val hues = listOf(180f, 210f, 240f, 270f)
                    val hue = hues[(pairing.xHandle ?: pairing.xName ?: "").hashCode().mod(hues.size)]
                    Color.hsv(hue, 0.5f, 0.9f)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onOpenPairing(pairing) }
                        .padding(vertical = 6.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Rank
                    Text(
                        text = pairing.rank?.toString() ?: "-",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Owner Avatar
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(avatarColor),
                        contentAlignment = Alignment.Center
                    ) {
                        if (pairing.xAvatar.isNullOrBlank()) {
                            val initial = (pairing.xName ?: pairing.xHandle ?: "?").take(1).uppercase()
                            Text(
                                text = initial,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            AsyncImage(
                                model = pairing.xAvatar,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            pairing.botName ?: "Unknown Bot",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = pairing.xHandle?.let { "@$it" } ?: "",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { pairing.xHandle?.let { onOpenX(it) } }
                        )
                    }
                    
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            formatCount(pairing.xFollowerCount ?: 0),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text("followers", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}

@Composable
private fun SubmoltBoard(submolts: List<Submolt>, onOpenSubmolt: (String) -> Unit) {
    SectionCard {
        LazyColumn(
            modifier = Modifier.heightIn(max = (5 * 56).dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(submolts) { submolt ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenSubmolt(submolt.name) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("🦞")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "m/${submolt.name}",
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${formatCount(submolt.subscriberCount ?: 0)} ${stringResource(R.string.members)}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RecentAgentsStrip(
    agents: List<com.moltbook.app.data.Agent>,
    onOpenX: (String) -> Unit,
    onOpenAgent: (com.moltbook.app.data.Agent) -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    SectionCard {
        RankingsSectionTitle(
            text = stringResource(R.string.explore_agents_online),
            iconContent = {
                Box {
                    Icon(
                        Icons.Rounded.SmartToy,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = 4.dp, y = (-2).dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.Green.copy(alpha = alpha))
                    )
                }
            }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(agents) { agent ->
                val avatarColor = remember(agent.name) {
                    val hues = listOf(0f, 30f, 150f, 200f, 280f, 330f)
                    val hue = hues[agent.name.hashCode().mod(hues.size)]
                    Color.hsv(hue, 0.6f, 0.85f)
                }

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .width(135.dp) // Width reduced to 135dp
                        .height(65.dp)
                        .clickable { onOpenAgent(agent) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Avatar with online dot
                        Box {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(avatarColor),
                                contentAlignment = Alignment.Center
                            ) {
                                if (agent.avatarUrl.isNullOrBlank()) {
                                    Text(
                                        text = agent.name.take(1).uppercase(),
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                } else {
                                    AsyncImage(
                                        model = agent.avatarUrl,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .size(9.dp)
                                    .align(Alignment.BottomEnd)
                                    .clip(CircleShape)
                                    .background(Color.Green)
                                    .border(1.dp, MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(10.dp))
                        
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = agent.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                            
                            agent.owner?.xHandle?.let { handle ->
                                Text(
                                    text = "@$handle",
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 9.sp),
                                    color = MaterialTheme.colorScheme.primary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.clickable { onOpenX(handle) }
                                )
                            }
                            
                            // Time on the 3rd line
                            Text(
                                text = formatTimeAgo(agent.createdAt),
                                style = androidx.compose.ui.text.TextStyle(fontSize = 8.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FollowAvatarRow(
    agents: List<com.moltbook.app.data.Agent>,
    onOpenAgent: (com.moltbook.app.data.Agent) -> Unit
) {
    SectionCard {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(agents) { agent ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box {
                        AgentAvatar(agent, size = 42.dp)
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .align(Alignment.BottomEnd)
                                .clip(CircleShape)
                                .background(Color(0xFF22C55E))
                        )
                    }
                    Text(
                        text = agent.name,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.clickable { onOpenAgent(agent) }
                    )
                }
            }
        }
    }
}

@Composable
private fun FollowPairingRow(
    pairings: List<TopHuman>,
    onOpenX: (String) -> Unit
) {
    SectionCard {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(pairings) { pairing ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box {
                        AsyncImage(
                            model = pairing.xAvatar,
                            contentDescription = null,
                            modifier = Modifier.size(42.dp).clip(CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .align(Alignment.BottomEnd)
                                .clip(CircleShape)
                                .background(if (pairing.xVerified == true) Color(0xFF22C55E) else Color(0xFF6B7280))
                        )
                    }
                    Text(
                        text = pairing.botName ?: "Bot",
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.clickable {
                            pairing.xHandle?.let { onOpenX(it) }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun AgentAvatar(agent: com.moltbook.app.data.Agent, size: Dp) {
    val initial = agent.name.firstOrNull()?.uppercase() ?: "A"
    if (agent.owner?.xAvatar.isNullOrBlank()) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Text(initial, style = MaterialTheme.typography.labelMedium)
        }
    } else {
        AsyncImage(
            model = agent.owner?.xAvatar,
            contentDescription = null,
            modifier = Modifier.size(size).clip(CircleShape)
        )
    }
}

@Composable
private fun CommentThread(
    comment: Comment,
    depth: Int,
    expandedComments: MutableMap<String, Boolean>,
    onToggleExpand: (String) -> Unit,
    onTranslate: (String, String) -> Unit,
    onShare: (String) -> Unit,
    onFavorite: () -> Unit,
    translationMap: Map<String, TranslationState>,
    onOpenUser: (String, String?) -> Unit,
    showOriginal: Boolean,
    originalTitle: String,
    originalContent: String
) {
    val isExpanded = expandedComments[comment.id] == true
    val translationState = translationMap[comment.id]
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = (depth * 10).dp)
    ) {
        if (depth > 0) {
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.outline)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            val authorName = comment.author?.name
            Text(
                text = authorName?.let { "u/$it" } ?: stringResource(R.string.anonymous_user),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = if (authorName != null) {
                    Modifier.clickable { onOpenUser(authorName, comment.author?.id) }
                } else Modifier
            )
            Text(
                text = comment.content ?: "",
                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 16.sp)
            )
            if (showOriginal) {
                Text(
                    text = stringResource(R.string.original_prefix) + "$originalTitle ${originalContent.take(80)}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            translationState?.let { state ->
                when {
                    state.isLoading -> Text(stringResource(R.string.translating), style = MaterialTheme.typography.bodySmall)
                    state.error != null -> Text(state.error, color = MaterialTheme.colorScheme.error)
                    state.translatedContent != null -> Text(
                        state.translatedContent,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = onFavorite, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Rounded.FavoriteBorder, contentDescription = "Favorite", modifier = Modifier.size(14.dp))
                }
                IconButton(
                    onClick = { onTranslate(comment.id, comment.content ?: "") },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Rounded.Translate, contentDescription = "Translate", modifier = Modifier.size(14.dp))
                }
                IconButton(
                    onClick = { onShare(comment.content ?: "") },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Rounded.Share, contentDescription = "Share", modifier = Modifier.size(14.dp))
                }
            }
            if (comment.replies.isNotEmpty()) {
                Text(
                    text = if (isExpanded) {
                        stringResource(R.string.collapse_replies)
                    } else {
                        stringResource(R.string.expand_replies, comment.replies.size)
                    },
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .clickable { onToggleExpand(comment.id) }
                )
                if (isExpanded) {
                    Spacer(modifier = Modifier.height(4.dp))
                    comment.replies.forEach { reply ->
                        CommentThread(
                            comment = reply,
                            depth = depth + 1,
                            expandedComments = expandedComments,
                            onToggleExpand = onToggleExpand,
                            onTranslate = onTranslate,
                            onShare = onShare,
                            onFavorite = onFavorite,
                            translationMap = translationMap,
                            onOpenUser = onOpenUser,
                            showOriginal = showOriginal,
                            originalTitle = originalTitle,
                            originalContent = originalContent
                        )
                    }
                }
            }
        }
    }
}

private fun buildCommentText(state: UiState<List<Comment>>): String {
    return if (state is UiState.Success) {
        state.data.joinToString("\n\n") { comment ->
            listOfNotNull(comment.author?.name, comment.content).joinToString(": ")
        }
    } else {
        ""
    }
}

private fun shareText(context: android.content.Context, title: String, content: String?) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, listOfNotNull(title, content).joinToString("\n\n"))
    }
    context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_chooser_title)))
}

private fun sharePost(context: android.content.Context, post: Post) {
    val url = post.url ?: "https://www.moltbook.com/post/${post.id}"
    val content = listOfNotNull(post.content).joinToString("\n\n")
    val full = listOfNotNull(post.title, content, "${context.getString(R.string.link_prefix)} $url").joinToString("\n\n")
    shareText(context, context.getString(R.string.share_post_subject), full)
}

private fun shareComment(context: android.content.Context, post: Post, comment: String) {
    val original = buildString {
        append(context.getString(R.string.original_prefix))
        append(post.title)
        if (!post.content.isNullOrBlank()) {
            append("\n")
            append(post.content)
        }
    }
    shareText(context, context.getString(R.string.share_comment_subject), "$comment\n\n$original")
}

private fun normalizeContent(content: String?): String {
    if (content.isNullOrBlank()) return ""
    return content
        .lines()
        .map { it.trimEnd() }
        .filter { it.isNotBlank() }
        .joinToString("\n")
}


@Composable
private fun SectionTitle(text: String) {
    Text(
        text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun SectionCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit
) {
    val isDark = LocalIsDark.current
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isDark) 0.dp else 2.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = if (isDark) 0.10f else 0.45f)
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            content()
        }
    }
}

@Composable
private fun CompactSearchBox(
    onClick: () -> Unit,
    width: Dp,
    height: Dp,
    iconSize: Dp,
    textSize: androidx.compose.ui.text.TextStyle,
    showText: Boolean = true
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .height(height)
            .width(width),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(iconSize)
            )
            if (showText) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "agent  submolt",
                    style = textSize,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                )
            }
        }
    }
}

private data class SearchPayload(
    val agents: List<com.moltbook.app.data.Agent> = emptyList(),
    val submolts: List<Submolt> = emptyList(),
    val posts: List<Post> = emptyList()
)

private data class AgentCommentItem(
    val post: Post,
    val comment: Comment
)

@Composable
private fun ScrapeWebView(
    name: String,
    repository: com.moltbook.app.data.MoltbookRepository,
    onPosts: (List<Post>) -> Unit,
    onStats: (com.moltbook.app.data.AgentStats) -> Unit,
    onDebugLog: (String) -> Unit,
    onDone: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val completed = remember { java.util.concurrent.atomic.AtomicBoolean(false) }
    val handler = remember { Handler(Looper.getMainLooper()) }
    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                settings.mediaPlaybackRequiresUserGesture = false
                settings.userAgentString = settings.userAgentString + " MoltbookApp"
                val bridge = object {
                    @android.webkit.JavascriptInterface
                    fun onPostsPayload(payload: String) {
                        if (completed.get()) return
                        scope.launch(kotlinx.coroutines.Dispatchers.IO) {
                            val rawPosts = parsePostsFromJsonPayload(payload)
                            val validPosts = rawPosts.filter { 
                                it.author?.name?.trim()?.equals(name.trim(), ignoreCase = true) == true 
                            }
                            if (validPosts.isNotEmpty() && completed.compareAndSet(false, true)) {
                                withContext(kotlinx.coroutines.Dispatchers.Main) {
                                    onPosts(validPosts)
                                    onDone()
                                }
                            }
                        }
                    }

                    @android.webkit.JavascriptInterface
                    fun onStatsPayload(posts: Long, comments: Long) {
                        scope.launch(kotlinx.coroutines.Dispatchers.Main) {
                            onStats(com.moltbook.app.data.AgentStats(posts, comments))
                        }
                    }

                    @android.webkit.JavascriptInterface
                    fun logDebug(msg: String) {
                        scope.launch(kotlinx.coroutines.Dispatchers.Main) {
                            onDebugLog(msg)
                        }
                    }
                }
                addJavascriptInterface(bridge, "MoltbookBridge")
                val injectJs = """
                    (function() {
                      if (window.__moltbook_hooked) return;
                      window.__moltbook_hooked = true;
                      function notify(payload) {
                        try { MoltbookBridge.onPostsPayload(payload); } catch (e) {}
                      }
                      function findStats() {
                         try {
                           const bodyText = document.body.innerText;
                           const pMatch = bodyText.match(/(\d+[,.]?\d*)\s*posts/i);
                           const cMatch = bodyText.match(/(\d+[,.]?\d*)\s*comments/i);
                           if (pMatch || cMatch) {
                               const p = pMatch ? parseInt(pMatch[1].replace(/[,.]/g, '')) : 0;
                               const c = cMatch ? parseInt(cMatch[1].replace(/[,.]/g, '')) : 0;
                               MoltbookBridge.onStatsPayload(p, c);
                               MoltbookBridge.logDebug("Stats found: P=" + p + " C=" + c);
                           }
                         } catch(e) {}
                      }
                      findStats();
                      setInterval(findStats, 2000);
                      const origFetch = window.fetch;
                      window.fetch = function() {
                        return origFetch.apply(this, arguments).then(function(resp) {
                          try {
                            const clone = resp.clone();
                            clone.text().then(function(t) {
                                if (t.includes('"posts"') || t.includes('"author"')) {
                                    notify(t);
                                }
                            });
                          } catch (e) {}
                          return resp;
                        });
                      };
                    })();
                """.trimIndent()
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                        view?.evaluateJavascript(injectJs) { _ -> }
                    }

                    override fun shouldInterceptRequest(view: WebView?, request: android.webkit.WebResourceRequest?): android.webkit.WebResourceResponse? {
                        val reqUrl = request?.url?.toString().orEmpty()
                        if (reqUrl.contains("/api/v1/posts") && (reqUrl.contains("author_id=") || reqUrl.contains("author="))) {
                            scope.launch(kotlinx.coroutines.Dispatchers.IO) {
                                val postsList: List<Post> = when {
                                    reqUrl.contains("author_id=") -> {
                                        val id = reqUrl.substringAfter("author_id=").substringBefore("&")
                                        runCatching { repository.getPostsByAuthorId(id, limit = 20).posts }.getOrNull().orEmpty()
                                    }
                                    reqUrl.contains("author=") -> {
                                        val authorParam = reqUrl.substringAfter("author=").substringBefore("&")
                                        val decoded = java.net.URLDecoder.decode(authorParam, "UTF-8")
                                        if (decoded.equals(name, ignoreCase = true)) {
                                            runCatching { repository.getPostsByAuthor(decoded, limit = 20) }
                                                .getOrNull()
                                                ?.posts
                                                ?.filter { it.author?.name?.trim()?.equals(name, ignoreCase = true) == true }
                                                .orEmpty()
                                        } else emptyList<Post>()
                                    }
                                    else -> emptyList<Post>()
                                }
                                if (postsList.isNotEmpty() && completed.compareAndSet(false, true)) {
                                    withContext(kotlinx.coroutines.Dispatchers.Main) {
                                        onPosts(postsList)
                                        onDone()
                                    }
                                }
                            }
                        }
                        return super.shouldInterceptRequest(view, request)
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        view?.evaluateJavascript(injectJs) { _ -> }
                        handler.postDelayed({
                            if (completed.compareAndSet(false, true)) {
                                onDone()
                            }
                            view?.destroy()
                        }, 6000)
                    }
                }
                loadUrl("https://www.moltbook.com/u/${name.lowercase()}")
            }
        },
        modifier = Modifier
            .size(1.dp)
            .alpha(0f)
    )
}

private fun isUuid(value: String?): Boolean {
    if (value.isNullOrBlank()) return false
    return Regex("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
        .matches(value)
}

private suspend fun scrapeAgentPostsViaWebView(
    context: android.content.Context,
    name: String,
    repository: MoltbookRepository
): List<Post> = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
    val cleanName = name.removePrefix("u/").trim()
    val url = "https://www.moltbook.com/u/${cleanName.lowercase()}"
    val deferred = kotlinx.coroutines.CompletableDeferred<List<Post>>()
    val webView = WebView(context)
    val destroyed = java.util.concurrent.atomic.AtomicBoolean(false)
    webView.settings.javaScriptEnabled = true
    webView.settings.domStorageEnabled = true
    webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
    webView.settings.mediaPlaybackRequiresUserGesture = false
    webView.settings.userAgentString = webView.settings.userAgentString + " MoltbookApp"
    val bridge = object {
        @android.webkit.JavascriptInterface
        fun onPostsPayload(payload: String) {
            if (!deferred.isCompleted) {
                kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                    val rawPosts = runCatching {
                        parsePostsFromJsonPayload(payload)
                    }.getOrNull().orEmpty()
                    
                    val validPosts = rawPosts.filter { 
                        it.author?.name?.trim()?.equals(cleanName, ignoreCase = true) == true 
                    }
                    
                    if (validPosts.isNotEmpty()) {
                        deferred.complete(validPosts)
                    }
                }
            }
        }
    }
    webView.addJavascriptInterface(bridge, "MoltbookBridge")
    fun injectHooks(view: WebView?) {
        if (destroyed.get()) return
        view?.evaluateJavascript(
            """
            (function() {
              if (window.__moltbook_hooked) return;
              window.__moltbook_hooked = true;
              function notify(payload) {
                try { MoltbookBridge.onPostsPayload(payload); } catch (e) {}
              }
              const origFetch = window.fetch;
              window.fetch = function() {
                return origFetch.apply(this, arguments).then(function(resp) {
                  try {
                    resp.clone().text().then(function(t){ 
                        if (t.includes('"posts"') || t.includes('"author"')) {
                            notify(t); 
                        }
                    });
                  } catch (e) {}
                  return resp;
                });
              };
            })();
            """.trimIndent()
        ) { _ -> }
    }

    val handler = Handler(Looper.getMainLooper())
    webView.webViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
            injectHooks(view)
        }
        override fun shouldInterceptRequest(view: WebView?, request: android.webkit.WebResourceRequest?): android.webkit.WebResourceResponse? {
            val reqUrl = request?.url?.toString().orEmpty()
            if (reqUrl.contains("/api/v1/posts") && (reqUrl.contains("author_id=") || reqUrl.contains("author="))) {
                if (!deferred.isCompleted) {
                    kotlinx.coroutines.GlobalScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                        val posts: List<Post> = when {
                            reqUrl.contains("author_id=") -> {
                                val id = reqUrl.substringAfter("author_id=").substringBefore("&")
                                runCatching { repository.getPostsByAuthorId(id, limit = 20).posts }.getOrNull().orEmpty()
                            }
                            reqUrl.contains("author=") -> {
                                val author = reqUrl.substringAfter("author=").substringBefore("&")
                                val decoded = java.net.URLDecoder.decode(author, "UTF-8")
                                if (decoded.equals(cleanName, ignoreCase = true)) {
                                    runCatching { repository.getPostsByAuthor(decoded, limit = 20) }
                                        .getOrNull()
                                        ?.posts
                                        ?.filter { it.author?.name?.trim()?.equals(cleanName, ignoreCase = true) == true }
                                        .orEmpty()
                                } else emptyList<Post>()
                            }
                            else -> emptyList<Post>()
                        }
                        if (posts.isNotEmpty()) {
                            deferred.complete(posts)
                        }
                    }
                }
            }
            return super.shouldInterceptRequest(view, request)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            handler.postDelayed({
                if (!deferred.isCompleted) {
                    deferred.complete(emptyList())
                }
                if (!destroyed.get()) {
                    destroyed.set(true)
                    view?.destroy()
                }
            }, 5000)
        }
    }
    webView.loadUrl(url)
    val result = kotlinx.coroutines.withTimeoutOrNull(7000) { deferred.await() } ?: emptyList()
    handler.removeCallbacksAndMessages(null)
    if (!destroyed.get()) {
        destroyed.set(true)
        webView.destroy()
    }
    result
}


private fun parsePostsFromJsonPayload(payload: String): List<Post> {
    val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
    val trimmed = payload.trim()
    if (trimmed.isEmpty()) return emptyList()
    return runCatching {
        val resp = json.decodeFromString(com.moltbook.app.data.PostsResponse.serializer(), trimmed)
        resp.posts
    }.getOrElse {
        // Try to find a posts array anywhere in a large JSON payload
        runCatching {
            val root = json.parseToJsonElement(trimmed)
            val posts = mutableListOf<Post>()
            fun walk(el: kotlinx.serialization.json.JsonElement) {
                when (el) {
                    is kotlinx.serialization.json.JsonObject -> {
                        el["posts"]?.let { value ->
                            if (value is kotlinx.serialization.json.JsonArray) {
                                value.forEach { item ->
                                    runCatching {
                                        val p = json.decodeFromJsonElement(Post.serializer(), item)
                                        posts.add(p)
                                    }
                                }
                            }
                        }
                        el.values.forEach { walk(it) }
                    }
                    is kotlinx.serialization.json.JsonArray -> el.forEach { walk(it) }
                    else -> {}
                }
            }
            walk(root)
            posts
        }.getOrElse { emptyList() }
    }
}

private suspend fun discoverAuthorIdByName(
    repository: MoltbookRepository,
    name: String
): String? {
    val sorts = listOf("new", "comments", "top")
    val limit = 50
    val maxPages = 4
    for (sort in sorts) {
        for (page in 0 until maxPages) {
            val offset = page * limit
            val resp = runCatching { repository.getPosts(sort = sort, limit = limit, offset = offset) }.getOrNull()
                ?: continue
            val match = resp.posts.firstOrNull { it.author?.name == name }
            if (match?.author?.id != null) {
                return match.author.id
            }
            if (!resp.hasMore) break
        }
    }
    return null
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun SearchPanel(
    query: String,
    history: List<String>,
    homepageState: UiState<HomepageResponse>,
    searchState: UiState<SearchPayload>,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClearHistory: () -> Unit,
    onDismiss: () -> Unit,
    onOpenSubmolt: (String) -> Unit,
    onOpenPost: (Post) -> Unit,
    onOpenAgent: (com.moltbook.app.data.Agent) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current
    val isDark = androidx.compose.foundation.isSystemInDarkTheme()
    val panelColor = if (isDark) Color(0xFF1C1F24) else Color.White
    val fieldBg = if (isDark) Color(0xFF2A2E35) else Color(0xFFF8F9FA)
    val textColor = if (isDark) Color(0xFFE6E6E6) else Color.Black
    val hintColor = if (isDark) Color(0xFF9AA0A6) else Color.Gray
    val borderColor = if (isDark) Color(0xFF3A3F46) else Color(0xFFE0E0E0)
    val chipColor = if (isDark) Color(0xFF2B2F36) else Color(0xFFF1F3F4)

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onDismiss
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = panelColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .padding(vertical = 40.dp) // Provide some breathing room from screen edges
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {}
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title for context
                Text(
                    stringResource(R.string.search_title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier.padding(start = 4.dp)
                )

                OutlinedTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    placeholder = { Text(stringResource(R.string.search_placeholder_posts), color = hintColor) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    leadingIcon = {
                        Icon(Icons.Outlined.Search, contentDescription = null, tint = Color(0xFF4285F4))
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { onQueryChange("") }) {
                                Icon(Icons.Rounded.Close, contentDescription = "Clear", tint = hintColor)
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4285F4),
                        unfocusedBorderColor = borderColor,
                        focusedContainerColor = fieldBg,
                        unfocusedContainerColor = fieldBg,
                        cursorColor = Color(0xFF4285F4),
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor
                    )
                )

                if (query.isBlank()) {
                    if (history.isNotEmpty()) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(stringResource(R.string.search_recent), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = if (isDark) Color(0xFFC2C6CC) else Color.DarkGray)
                                Text(
                                    stringResource(R.string.search_clear),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color(0xFF4285F4),
                                    modifier = Modifier.clickable { onClearHistory() }
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                history.forEach { item ->
                                    Card(
                                        shape = RoundedCornerShape(16.dp),
                                        colors = CardDefaults.cardColors(containerColor = chipColor),
                                        modifier = Modifier
                                            .padding(bottom = 8.dp)
                                            .clickable { onQueryChange(item); onSearch() }
                                    ) {
                                        Text(
                                            item,
                                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = textColor
                                        )
                                    }
                                }
                            }
                        }
                    }
                } else {
                    when (searchState) {
                        UiState.Loading -> Box(Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(modifier = Modifier.size(32.dp), strokeWidth = 2.dp, color = Color(0xFF4285F4))
                        }
                        is UiState.Error -> Text(searchState.message, color = MaterialTheme.colorScheme.error)
                        is UiState.Success -> {
                            val payload = searchState.data
                            val hasResults = payload.agents.isNotEmpty() || payload.submolts.isNotEmpty() || payload.posts.isNotEmpty()

                            if (!hasResults) {
                                Text(stringResource(R.string.search_no_results), modifier = Modifier.padding(vertical = 20.dp), color = hintColor)
                            } else {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(max = 400.dp),
                                    verticalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    if (payload.posts.isNotEmpty()) {
                                        item { SearchSectionTitle(stringResource(R.string.search_section_posts)) }
                                        items(payload.posts) { post ->
                                            GoogleSearchRow(
                                                post.title,
                                                stringResource(R.string.search_from_submolt, post.submolt?.name ?: ""),
                                                "📝"
                                            ) { onOpenPost(post); onDismiss() }
                                        }
                                    }
                                    if (payload.agents.isNotEmpty()) {
                                        item { SearchSectionTitle(stringResource(R.string.search_section_agents)) }
                                        items(payload.agents) { agent ->
                                            GoogleSearchRow(agent.name, "@${agent.owner?.xHandle ?: ""}", "🤖") { onOpenAgent(agent); onDismiss() }
                                        }
                                    }
                                    if (payload.submolts.isNotEmpty()) {
                                        item { SearchSectionTitle(stringResource(R.string.search_section_submolts)) }
                                        items(payload.submolts) { submolt ->
                                            GoogleSearchRow("m/${submolt.name}", "${formatCount(submolt.subscriberCount ?: 0)} ${stringResource(R.string.members)}", "🦞") { onOpenSubmolt(submolt.name); onDismiss() }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
private fun SearchSectionTitle(text: String) {
    val isDark = androidx.compose.foundation.isSystemInDarkTheme()
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.ExtraBold,
        color = if (isDark) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f) else Color.Gray,
        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp, start = 4.dp)
    )
}

@Composable
private fun GoogleSearchRow(title: String, subtitle: String, icon: String, onClick: () -> Unit) {
    val isDark = androidx.compose.foundation.isSystemInDarkTheme()
    val titleColor = if (isDark) MaterialTheme.colorScheme.onSurface else Color.Black
    val subtitleColor = if (isDark) MaterialTheme.colorScheme.onSurfaceVariant else Color.Gray
    val iconBg = if (isDark) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f) else Color(0xFFF8F9FA)
    val chevronTint = if (isDark) MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f) else Color.LightGray
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(iconBg, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = titleColor
            )
            if (subtitle.isNotEmpty()) {
                Text(subtitle, style = MaterialTheme.typography.labelSmall, color = subtitleColor)
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = chevronTint
        )
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
private fun FlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable androidx.compose.foundation.layout.FlowRowScope.() -> Unit
) {
    androidx.compose.foundation.layout.FlowRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        content = content
    )
}

private fun formatCount(value: Long): String {
    return when {
        value >= 1_000_000 -> String.format("%.1fM", value / 1_000_000f)
        value >= 1_000 -> String.format("%.1fK", value / 1_000f)
        else -> value.toString()
    }
}

private fun currentAppLanguage(): String {
    val tags = AppCompatDelegate.getApplicationLocales().toLanguageTags().trim()
    val first = tags.split(',').firstOrNull().orEmpty().trim()
    if (first.isNotBlank()) return java.util.Locale.forLanguageTag(first).language
    return Locale.getDefault().language
}

private fun isAppZh(): Boolean = currentAppLanguage().equals("zh", ignoreCase = true)

private fun tr(zh: String, en: String): String = if (isAppZh()) zh else en

private fun normalizeAgentNameInput(raw: String): String {
    var s = raw.trim()
    if (s.isBlank()) return ""
    s = s.removePrefix("@").trim()

    val lower = s.lowercase(Locale.ROOT)
    if (lower.startsWith("http://") || lower.startsWith("https://")) {
        s = runCatching { Uri.parse(s).path.orEmpty() }.getOrDefault(s)
    }
    s = s.trim().trim('/')

    val parts = s.split('/').filter { it.isNotBlank() }
    val uIndex = parts.indexOfFirst { it.equals("u", ignoreCase = true) || it.equals("u+", ignoreCase = true) }
    if (uIndex >= 0 && uIndex + 1 < parts.size) {
        return parts[uIndex + 1].trim()
    }
    return parts.lastOrNull().orEmpty().trim()
}

private fun formatTimeAgo(isoTime: String?): String {
    val instant = parseInstantFlexible(isoTime) ?: return ""
    return try {
        val isZh = isAppZh()
        val now = Instant.now()
        val duration = Duration.between(instant, now)
        val seconds = duration.seconds.coerceAtLeast(0)
        val minutes = duration.toMinutes().coerceAtLeast(0)
        val hours = duration.toHours().coerceAtLeast(0)
        val days = duration.toDays().coerceAtLeast(0)
        when {
            minutes < 1 -> if (isZh) "${seconds}秒" else "${seconds}s"
            minutes < 60 -> if (isZh) "${minutes}分钟" else "${minutes}m"
            hours < 24 -> if (isZh) "${hours}小时" else "${hours}H"
            days < 7 -> if (isZh) "${days}天" else "${days}D"
            else -> DateTimeFormatter.ofPattern("MM-dd")
                .withZone(ZoneId.systemDefault())
                .format(instant)
        }
    } catch (_: Exception) {
        ""
    }
}

private fun formatDate(isoTime: String?): String? {
    val instant = parseInstantFlexible(isoTime) ?: return null
    return try {
        DateTimeFormatter.ofPattern("yyyy/M/d")
            .withZone(ZoneId.systemDefault())
            .format(instant)
    } catch (_: Exception) {
        null
    }
}

private fun parseInstantOrZero(isoTime: String?): Long {
    return parseInstantFlexible(isoTime)?.toEpochMilli() ?: 0L
}

private fun parseInstantFlexible(isoTime: String?): Instant? {
    if (isoTime.isNullOrBlank()) return null
    return try {
        Instant.parse(isoTime)
    } catch (_: Exception) {
        try {
            java.time.OffsetDateTime.parse(isoTime).toInstant()
        } catch (_: Exception) {
            null
        }
    }
}

private suspend fun loadTopCommentBatch(repository: MoltbookRepository, postId: String): Comment? {
    return try {
        val response = withContext(Dispatchers.IO) { repository.getComments(postId, limit = 10) }
        response.comments.firstOrNull()
    } catch (_: Exception) {
        null
    }
}

private suspend fun loadTopCommentsForPosts(
    repository: MoltbookRepository,
    posts: List<Post>
): Map<String, Comment?> = withContext(Dispatchers.IO) {
    posts.filter { it.commentCount > 0 }
        .map { post ->
            async {
                post.id to loadTopCommentBatch(repository, post.id)
            }
        }
        .awaitAll()
        .toMap()
}

private suspend fun performSearch(
    repository: MoltbookRepository,
    query: String,
    homepageState: UiState<HomepageResponse>
): UiState<SearchPayload> {
    return try {
        coroutineScope {
            // Website-default: prioritize posts search. Enhance with local agent/submolt matching.
            val postsDeferred = async(Dispatchers.IO) { repository.search(query, "posts") }
            val agentsDeferred = async(Dispatchers.IO) {
                repository.getCachedRecentAgents() ?: repository.getRecentAgents(limit = 50, sort = "recent", forceRefresh = false)
            }
            val submoltsDeferred = async(Dispatchers.IO) {
                repository.getCachedSubmoltsDirectory() ?: repository.getSubmoltsDirectory(limit = 100, offset = 0, forceRefresh = false)
            }

            val postsResp = postsDeferred.await()
            val posts = postsResp.results
                .filter { it.type == "post" }
                .mapNotNull { r ->
                    val id = r.postId ?: r.id
                    val title = r.title?.trim().orEmpty()
                    if (id.isNullOrBlank() || title.isBlank()) return@mapNotNull null
                    Post(
                        id = id,
                        title = title.lines().firstOrNull { it.isNotBlank() } ?: title,
                        content = r.content,
                        url = null,
                        upvotes = r.upvotes ?: 0,
                        downvotes = r.downvotes,
                        commentCount = 0,
                        createdAt = r.createdAt,
                        submolt = r.submolt,
                        author = r.author
                    )
                }

            val keyword = query.trim().lowercase()
            val agentsSource = agentsDeferred.await().agents
            val agents = agentsSource
                .filter { a ->
                    a.name.lowercase().contains(keyword) ||
                        (a.owner?.xHandle ?: "").lowercase().contains(keyword) ||
                        (a.owner?.xName ?: "").lowercase().contains(keyword)
                }
                .take(20)

            val submoltsSource = submoltsDeferred.await().submolts
            val submolts = submoltsSource
                .filter { s ->
                    s.name.lowercase().contains(keyword) ||
                        (s.displayName ?: "").lowercase().contains(keyword) ||
                        (s.description ?: "").lowercase().contains(keyword)
                }
                .take(20)

            // Strengthen local cache for future searches.
            repository.addKnownAgents(
                agents.map { it.name } +
                    posts.mapNotNull { it.author?.name }
            )
            repository.addKnownSubmolts(
                submolts.map { it.name } +
                    posts.mapNotNull { it.submolt?.name }
            )

            if (posts.isEmpty() && agents.isEmpty() && submolts.isEmpty()) {
                fallbackSearch(query, homepageState)
            } else {
                UiState.Success(SearchPayload(agents = agents, submolts = submolts, posts = posts))
            }
        }
    } catch (_: Exception) {
        fallbackSearch(query, homepageState)
    }
}

private fun fallbackSearch(query: String, homepageState: UiState<HomepageResponse>): UiState<SearchPayload> {
    val data = (homepageState as? UiState.Success)?.data ?: return UiState.Error(tr("搜索不可用", "Search unavailable"))
    val keyword = query.lowercase()
    val agents = data.agents.filter { agent ->
        agent.name.lowercase().contains(keyword) ||
            (agent.owner?.xHandle ?: "").lowercase().contains(keyword)
    }
    val submolts = data.submolts.filter { it.name.lowercase().contains(keyword) }
    val posts = data.posts.filter { post ->
        post.title.lowercase().contains(keyword) || (post.content ?: "").lowercase().contains(keyword)
    }
    return UiState.Success(SearchPayload(agents = agents, submolts = submolts, posts = posts))
}

private data class TranslationState(
    val isLoading: Boolean = false,
    val translatedTitle: String? = null,
    val translatedContent: String? = null,
    val translatedComment: String? = null,
    val error: String? = null
)

private data class TranslationRequest(
    val title: String,
    val content: String? = null,
    val comment: String? = null
)


@Composable
private fun rememberLanguageIdentifier(): LanguageIdentifier {
    val identifier = remember {
        LanguageIdentification.getClient()
    }
    DisposableEffect(Unit) {
        onDispose { identifier.close() }
    }
    return identifier
}

private fun requestTranslation(
    request: TranslationRequest,
    languageIdentifier: LanguageIdentifier,
    onState: (TranslationState) -> Unit
) {
    fun tr(zh: String, en: String): String {
        return if (isAppZh()) zh else en
    }
    if (request.title.isBlank() && request.content.isNullOrBlank() && request.comment.isNullOrBlank()) {
        onState(TranslationState(error = tr("暂无可翻译内容", "Nothing to translate")))
        return
    }

    val combined = listOfNotNull(request.title, request.content, request.comment).joinToString("\n")
    languageIdentifier.identifyLanguage(combined)
        .addOnSuccessListener { lang ->
            val systemLang = java.util.Locale.getDefault().language
            val target = TranslateLanguage.fromLanguageTag(systemLang) ?: TranslateLanguage.CHINESE
            val source = TranslateLanguage.fromLanguageTag(lang)
            if (source == null || source == "und") {
                translateWithModel(
                    source = TranslateLanguage.ENGLISH,
                    target = target,
                    request = request,
                    onState = onState
                )
                return@addOnSuccessListener
            }
            if (source == target) {
                onState(TranslationState(error = tr("当前语言无需翻译", "No translation needed")))
                return@addOnSuccessListener
            }
            translateWithModel(source = source, target = target, request = request, onState = onState)
        }
        .addOnFailureListener {
            onState(TranslationState(error = tr("语言检测失败", "Language detection failed")))
        }
}

private fun translateWithModel(
    source: String,
    target: String,
    request: TranslationRequest,
    onState: (TranslationState) -> Unit
) {
    val options = TranslatorOptions.Builder()
        .setSourceLanguage(source)
        .setTargetLanguage(target)
        .build()
    val translator = Translation.getClient(options)
    val conditions = DownloadConditions.Builder().build()
    translator.downloadModelIfNeeded(conditions)
        .addOnSuccessListener {
            translateSequential(
                translator = translator,
                title = request.title,
                content = request.content,
                comment = request.comment,
                onState = {
                    translator.close()
                    onState(it)
                }
            )
        }
        .addOnFailureListener { error ->
            translator.close()
            val prefix = if (isAppZh()) "模型下载失败：" else "Model download failed: "
            onState(TranslationState(error = prefix + (error.localizedMessage ?: "unknown")))
        }
}

private fun translateSequential(
    translator: Translator,
    title: String,
    content: String?,
    comment: String?,
    onState: (TranslationState) -> Unit
) {
    translator.translate(title)
        .addOnSuccessListener { tTitle ->
            if (content.isNullOrBlank()) {
                translateComment(translator, tTitle, null, comment, onState)
            } else {
                translator.translate(content)
                    .addOnSuccessListener { tContent ->
                        translateComment(translator, tTitle, tContent, comment, onState)
                    }
                    .addOnFailureListener { error ->
                        val prefix = if (isAppZh()) "翻译失败：" else "Translation failed: "
                        onState(TranslationState(error = prefix + (error.localizedMessage ?: "unknown")))
                    }
            }
        }
        .addOnFailureListener { error ->
            val prefix = if (isAppZh()) "翻译失败：" else "Translation failed: "
            onState(TranslationState(error = prefix + (error.localizedMessage ?: "unknown")))
        }
}

private fun translateComment(
    translator: Translator,
    tTitle: String,
    tContent: String?,
    comment: String?,
    onState: (TranslationState) -> Unit
) {
    if (comment.isNullOrBlank()) {
        onState(
            TranslationState(
                translatedTitle = tTitle,
                translatedContent = tContent
            )
        )
        return
    }

    translator.translate(comment)
        .addOnSuccessListener { tComment ->
            onState(
                TranslationState(
                    translatedTitle = tTitle,
                    translatedContent = tContent,
                    translatedComment = tComment
                )
            )
        }
        .addOnFailureListener { error ->
            val prefix = if (isAppZh()) "翻译失败：" else "Translation failed: "
            onState(TranslationState(error = prefix + (error.localizedMessage ?: "unknown")))
        }
}

@Composable
private fun IntroScreen(
    repository: MoltbookRepository,
    fallbackHomePosts: List<Post>,
    fallbackHomeTopComments: Map<String, Comment?>,
    onDone: (IntroPayload) -> Unit,
    onOnboarding: (IntroPayload?) -> Unit
) {
    val context = LocalContext.current
    val hasOnboarded = remember {
        context.getSharedPreferences("moltbook_onboarding", Context.MODE_PRIVATE)
            .getBoolean("has_onboarded", false)
    }

    var payload by remember { mutableStateOf<IntroPayload?>(null) }
    var done by remember { mutableStateOf(false) }

    // Preload in background. Do not transition early; the 3s gate below controls that.
    LaunchedEffect(Unit) {
        runCatching {
            val home = withContext(Dispatchers.IO) { repository.getHomepage(forceRefresh = true) }

            val sorts = listOf("comments", "new", "top", "random")
            val postsBySort = mutableMapOf<String, List<Post>>()
            val commentsBySort = mutableMapOf<String, Map<String, Comment?>>()
            val tsBySort = mutableMapOf<String, Long>()

            coroutineScope {
                sorts.map { s ->
                    async(Dispatchers.IO) {
                        val limit = if (s == "comments") 20 else 10
                        val posts = when (s) {
                            "random" -> home.posts.take(limit)
                            else -> repository.getPosts(sort = s, limit = limit).posts
                        }
                        val topMap = loadTopCommentsForPosts(repository, posts)
                        Triple(s, posts, topMap)
                    }
                }.awaitAll().forEach { (s, posts, topMap) ->
                    postsBySort[s] = posts
                    commentsBySort[s] = topMap
                    tsBySort[s] = System.currentTimeMillis()
                }
            }

            payload = IntroPayload(
                home = home,
                defaultPosts = postsBySort["comments"].orEmpty(),
                defaultComments = commentsBySort["comments"].orEmpty(),
                preload = IntroPreload(
                    posts = postsBySort,
                    comments = commentsBySort,
                    timestamps = tsBySort
                )
            )
        }
    }

    // Transition gate:
    // Non-first-launch: enter home after 2s.
    // First-launch: go to the onboarding screen after 3s.
    LaunchedEffect(Unit) {
        delay(if (hasOnboarded) 2000 else 3000)
        if (done) return@LaunchedEffect
        done = true
        if (hasOnboarded) {
            val p = payload
            if (p != null) {
                onDone(p)
            } else {
                onDone(
                    IntroPayload(
                        home = repository.getCachedHomepage() ?: HomepageResponse(success = true),
                        defaultPosts = fallbackHomePosts,
                        defaultComments = fallbackHomeTopComments,
                        preload = IntroPreload(posts = emptyMap(), comments = emptyMap(), timestamps = emptyMap())
                    )
                )
            }
        } else {
            onOnboarding(payload)
        }
    }

    val isDark = LocalIsDark.current
    val bg = MaterialTheme.colorScheme.background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .statusBarsPadding()
            .padding(horizontal = 18.dp, vertical = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        // Keep splash instant and stable: local resources only, no animations.
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.moltbook_mascot),
                        contentDescription = "Moltbook",
                        modifier = Modifier.size(46.dp)
                    )
                    Text(
                        text = "moltbook",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = stringResource(R.string.splash_tagline),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = if (isDark) 0.90f else 0.78f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun OnboardingScreen(
    initialX: String,
    initialAgent: String,
    onSubmit: (xHandle: String, agentName: String) -> Unit,
    onLater: () -> Unit
) {
    var xHandle by rememberSaveable { mutableStateOf(initialX) }
    var agentName by rememberSaveable { mutableStateOf(initialAgent) }

    val isDark = LocalIsDark.current
    val bg = MaterialTheme.colorScheme.background

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .statusBarsPadding()
            .padding(horizontal = 18.dp, vertical = 14.dp)
    ) {
        // Compact, Google-setup-like central card. Avoid split-screen complexity and heavy layout.
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isDark) Color.White.copy(alpha = 0.06f) else MaterialTheme.colorScheme.surface
                ),
                border = if (isDark) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.55f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.moltbook_mascot),
                            contentDescription = "Moltbook",
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = "moltbook",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    @Composable
                    fun CompactField(
                        bg: Color,
                        icon: androidx.compose.ui.graphics.vector.ImageVector,
                        leadingText: String?,
                        value: String,
                        onValueChange: (String) -> Unit,
                        placeholder: String
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = bg),
                            border = if (isDark) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.35f))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (isDark) Color.White.copy(alpha = 0.14f)
                                            else MaterialTheme.colorScheme.primary.copy(alpha = 0.10f)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        icon,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                OutlinedTextField(
                                    value = value,
                                    onValueChange = onValueChange,
                                    singleLine = true,
                                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                    placeholder = { Text(placeholder, fontWeight = FontWeight.Bold) },
                                    leadingIcon = leadingText?.let {
                                        {
                                            Text(
                                                it,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.86f)
                                            )
                                        }
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                        unfocusedBorderColor = Color.Transparent,
                                        focusedBorderColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        focusedContainerColor = Color.Transparent,
                                        cursorColor = MaterialTheme.colorScheme.primary
                                    ),
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(14.dp)
                                )
                            }
                        }
                    }

                    CompactField(
                        bg = if (isDark) Color(0xFF1D4ED8).copy(alpha = 0.28f) else Color(0xFFDBEAFE),
                        icon = Icons.Rounded.Person,
                        leadingText = "@",
                        value = xHandle,
                        onValueChange = { xHandle = it },
                        placeholder = stringResource(R.string.onboarding_x_optional)
                    )
                    CompactField(
                        bg = if (isDark) Color(0xFFEA580C).copy(alpha = 0.26f) else Color(0xFFFFEDD5),
                        icon = Icons.Rounded.SmartToy,
                        leadingText = null,
                        value = agentName,
                        onValueChange = { agentName = it },
                        placeholder = stringResource(R.string.onboarding_agent_required)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onLater) { Text(stringResource(R.string.later), fontWeight = FontWeight.ExtraBold) }
                        Button(
                            onClick = { onSubmit(xHandle, agentName.trim()) },
                            enabled = agentName.trim().isNotBlank(),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isDark) Color.White.copy(alpha = 0.14f) else MaterialTheme.colorScheme.surface,
                                contentColor = if (isDark) Color.White else MaterialTheme.colorScheme.onSurface,
                                disabledContainerColor = if (isDark) Color.White.copy(alpha = 0.06f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f),
                                disabledContentColor = if (isDark) Color.White.copy(alpha = 0.35f) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
                            )
                        ) {
                            Text(stringResource(R.string.done), fontWeight = FontWeight.ExtraBold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MoltBookTheme(
    palette: BrandPalette,
    fontScale: Float = 1.0f,
    themeMode: String = "system",
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        "dark" -> true
        "light" -> false
        else -> androidx.compose.foundation.isSystemInDarkTheme()
    }
    val colorScheme = if (isDark) {
        darkColorScheme(
            primary = palette.accent,
            secondary = palette.accentAlt,
            background = palette.background,
            surface = palette.surface,
            surfaceVariant = palette.surfaceAlt,
            onPrimary = palette.onSurface,
            onSecondary = palette.onSurface,
            onBackground = palette.onSurface,
            onSurface = palette.onSurface,
            onSurfaceVariant = palette.onSurfaceMuted
        )
    } else {
        // Google-like light palette: clean neutrals, readable text, subtle variants.
        lightColorScheme(
            primary = Color(0xFF1A73E8),
            secondary = Color(0xFF34A853),
            // Slightly deeper than pure white to reduce glare.
            background = Color(0xFFEDEFF3),
            surface = Color(0xFFF6F7F9),
            surfaceVariant = Color(0xFFE2E8F0),
            onPrimary = Color(0xFFFFFFFF),
            onSecondary = Color(0xFFFFFFFF),
            onBackground = Color(0xFF0F172A),
            onSurface = Color(0xFF0F172A),
            onSurfaceVariant = Color(0xFF334155),
            outline = Color(0xFFCBD5E1)
        )
    }

    fun scaleUnit(u: TextUnit, scale: Float): TextUnit = if (u.isUnspecified) u else u * scale
    fun scaleStyle(style: TextStyle, scale: Float): TextStyle = style.copy(
        fontSize = scaleUnit(style.fontSize, scale),
        lineHeight = scaleUnit(style.lineHeight, scale)
    )
    fun scaleTypography(base: Typography, scale: Float): Typography {
        if (scale == 1.0f) return base
        return base.copy(
            displayLarge = scaleStyle(base.displayLarge, scale),
            displayMedium = scaleStyle(base.displayMedium, scale),
            displaySmall = scaleStyle(base.displaySmall, scale),
            headlineLarge = scaleStyle(base.headlineLarge, scale),
            headlineMedium = scaleStyle(base.headlineMedium, scale),
            headlineSmall = scaleStyle(base.headlineSmall, scale),
            titleLarge = scaleStyle(base.titleLarge, scale),
            titleMedium = scaleStyle(base.titleMedium, scale),
            titleSmall = scaleStyle(base.titleSmall, scale),
            bodyLarge = scaleStyle(base.bodyLarge, scale),
            bodyMedium = scaleStyle(base.bodyMedium, scale),
            bodySmall = scaleStyle(base.bodySmall, scale),
            labelLarge = scaleStyle(base.labelLarge, scale),
            labelMedium = scaleStyle(base.labelMedium, scale),
            labelSmall = scaleStyle(base.labelSmall, scale)
        )
    }

    val lang = currentAppLanguage().lowercase(Locale.ROOT)
    val typography = remember(lang, fontScale) {
        val isZh = lang == "zh"
        val base = Typography()
        val bolded = if (isZh) {
            base
        } else {
            // Bold non-content English UI: labels/titles/headlines.
            base.copy(
                labelSmall = base.labelSmall.copy(fontWeight = FontWeight.Bold),
                labelMedium = base.labelMedium.copy(fontWeight = FontWeight.Bold),
                labelLarge = base.labelLarge.copy(fontWeight = FontWeight.Bold),
                titleSmall = base.titleSmall.copy(fontWeight = FontWeight.Bold),
                titleMedium = base.titleMedium.copy(fontWeight = FontWeight.Bold),
                titleLarge = base.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                headlineSmall = base.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                headlineMedium = base.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                headlineLarge = base.headlineLarge.copy(fontWeight = FontWeight.ExtraBold),
                displaySmall = base.displaySmall.copy(fontWeight = FontWeight.ExtraBold),
                displayMedium = base.displayMedium.copy(fontWeight = FontWeight.ExtraBold),
                displayLarge = base.displayLarge.copy(fontWeight = FontWeight.ExtraBold)
            )
        }
        scaleTypography(bolded, fontScale.coerceIn(0.9f, 1.2f))
    }

    CompositionLocalProvider(LocalIsDark provides isDark) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}
