package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Story
import com.example.ui.components.TopStoryBar
import com.example.ui.screens.AdminScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.StoryDetailScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.VillageGreenSecondary
import com.example.ui.theme.WarmYellowCard
import com.example.ui.viewmodel.StoryViewModel

enum class NavigationTab(val tamilLabel: String, val englishLabel: String) {
    HOME("முகப்பு", "Home"),
    CATEGORIES("வகைகள்", "Categories"),
    FAVORITES("பிடித்தவை", "Saved"),
    ADMIN("நிர்வாகம்", "Admin")
}

class MainActivity : ComponentActivity() {

    private val viewModel: StoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                StoryApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun StoryApp(viewModel: StoryViewModel) {
    var currentTab by remember { mutableStateOf(NavigationTab.HOME) }

    val allStories by viewModel.allStories.collectAsState()
    val filteredStories by viewModel.filteredStories.collectAsState()
    val favoriteStories by viewModel.favoriteStories.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val sortBy by viewModel.sortBy.collectAsState()
    val appLanguage by viewModel.appLanguage.collectAsState()
    val fontSizeScale by viewModel.fontSizeScale.collectAsState()
    val selectedStory by viewModel.selectedStory.collectAsState()
    val audioState by viewModel.audioState.collectAsState()
    val uiMessage by viewModel.uiMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiMessage) {
        uiMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearUiMessage()
        }
    }

    // Detail Screen Navigation Back Handler
    if (selectedStory != null) {
        StoryDetailScreen(
            story = selectedStory!!,
            allStories = allStories,
            audioState = audioState,
            fontSizeScale = fontSizeScale,
            onIncreaseFontSize = { viewModel.increaseFontSize() },
            onDecreaseFontSize = { viewModel.decreaseFontSize() },
            onResetFontSize = { viewModel.resetFontSize() },
            onBack = { viewModel.selectStory(null) },
            onToggleFavorite = { viewModel.toggleFavorite(it) },
            onPlayAudio = { story, lang -> viewModel.playStoryAudio(story, lang) },
            onPauseAudio = { viewModel.pauseAudio() },
            onResumeAudio = { viewModel.resumeAudio() },
            onStopAudio = { viewModel.stopAudio() },
            onSetSpeechRate = { viewModel.setSpeechRate(it) },
            onSelectStory = { viewModel.selectStory(it) }
        )
        return
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopStoryBar(
                currentLanguage = appLanguage,
                onLanguageSelected = { viewModel.setAppLanguage(it) }
            )
        },
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Mini Player Bar if Audio is active
                AnimatedVisibility(
                    visible = audioState.currentStoryId != null,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
                ) {
                    val activePlayingStory = allStories.firstOrNull { it.id == audioState.currentStoryId }
                    if (activePlayingStory != null) {
                        Surface(
                            color = WarmYellowCard,
                            tonalElevation = 6.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.selectStory(activePlayingStory) }
                                .testTag("mini_audio_player")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Surface(
                                        color = if (audioState.isPlaying) VillageGreenSecondary else TerracottaPrimary,
                                        shape = CircleShape,
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (audioState.isPlaying) Icons.Default.VolumeUp else Icons.Default.PlayArrow,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .fillMaxSize()
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = activePlayingStory.titleTamil,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = if (audioState.isPlaying) "குரல் கேட்கிறது..." else "இடைநிறுத்தப்பட்டுள்ளது",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(
                                        onClick = {
                                            if (audioState.isPlaying) {
                                                viewModel.pauseAudio()
                                            } else {
                                                viewModel.resumeAudio()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (audioState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                            contentDescription = if (audioState.isPlaying) "Pause" else "Play",
                                            tint = TerracottaPrimary
                                        )
                                    }

                                    IconButton(onClick = { viewModel.stopAudio() }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Stop",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Main Navigation Bar
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 3.dp
                ) {
                    NavigationBarItem(
                        selected = currentTab == NavigationTab.HOME,
                        onClick = { currentTab = NavigationTab.HOME },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text(NavigationTab.HOME.tamilLabel) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = TerracottaPrimary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_home")
                    )

                    NavigationBarItem(
                        selected = currentTab == NavigationTab.CATEGORIES,
                        onClick = { currentTab = NavigationTab.CATEGORIES },
                        icon = { Icon(Icons.Default.GridView, contentDescription = "Categories") },
                        label = { Text(NavigationTab.CATEGORIES.tamilLabel) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = TerracottaPrimary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_categories")
                    )

                    NavigationBarItem(
                        selected = currentTab == NavigationTab.FAVORITES,
                        onClick = { currentTab = NavigationTab.FAVORITES },
                        icon = { Icon(Icons.Default.Bookmark, contentDescription = "Saved") },
                        label = { Text(NavigationTab.FAVORITES.tamilLabel) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = TerracottaPrimary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_favorites")
                    )

                    NavigationBarItem(
                        selected = currentTab == NavigationTab.ADMIN,
                        onClick = { currentTab = NavigationTab.ADMIN },
                        icon = { Icon(Icons.Default.AdminPanelSettings, contentDescription = "Admin") },
                        label = { Text(NavigationTab.ADMIN.tamilLabel) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = TerracottaPrimary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = Modifier.testTag("nav_admin")
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                NavigationTab.HOME -> {
                    HomeScreen(
                        stories = filteredStories,
                        searchQuery = searchQuery,
                        onSearchQueryChanged = { viewModel.setSearchQuery(it) },
                        selectedCategory = selectedCategory,
                        onCategorySelected = { viewModel.selectCategory(it) },
                        sortBy = sortBy,
                        onSortOptionSelected = { viewModel.setSortOption(it) },
                        onStoryClick = { viewModel.selectStory(it) },
                        onToggleFavorite = { viewModel.toggleFavorite(it) },
                        onPlayAudio = { viewModel.playStoryAudio(it) },
                        currentPlayingStoryId = audioState.currentStoryId
                    )
                }

                NavigationTab.CATEGORIES -> {
                    CategoriesScreen(
                        stories = allStories,
                        onStoryClick = { viewModel.selectStory(it) },
                        onToggleFavorite = { viewModel.toggleFavorite(it) },
                        onPlayAudio = { viewModel.playStoryAudio(it) },
                        currentPlayingStoryId = audioState.currentStoryId
                    )
                }

                NavigationTab.FAVORITES -> {
                    FavoritesScreen(
                        favorites = favoriteStories,
                        onStoryClick = { viewModel.selectStory(it) },
                        onToggleFavorite = { viewModel.toggleFavorite(it) },
                        onPlayAudio = { viewModel.playStoryAudio(it) },
                        onExploreStories = { currentTab = NavigationTab.HOME },
                        currentPlayingStoryId = audioState.currentStoryId
                    )
                }

                NavigationTab.ADMIN -> {
                    AdminScreen(
                        stories = allStories,
                        onAddStory = { viewModel.addStory(it) },
                        onBulkAddStories = { viewModel.bulkAddStories(it) },
                        onUpdateStory = { viewModel.updateStory(it) },
                        onDeleteStory = { viewModel.deleteStory(it) },
                        onResetDefaults = { viewModel.resetToDefaultStories() }
                    )
                }
            }
        }
    }
}
