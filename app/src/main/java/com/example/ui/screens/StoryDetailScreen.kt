package com.example.ui.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioState
import com.example.data.model.Story
import com.example.ui.components.StoryCard
import com.example.ui.theme.MarigoldGold
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.VillageGreenSecondary
import com.example.ui.theme.WarmYellowCard
import com.example.ui.theme.WarmYellowSurface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryDetailScreen(
    story: Story,
    allStories: List<Story>,
    audioState: AudioState,
    fontSizeScale: Float,
    onIncreaseFontSize: () -> Unit,
    onDecreaseFontSize: () -> Unit,
    onResetFontSize: () -> Unit,
    onBack: () -> Unit,
    onToggleFavorite: (Story) -> Unit,
    onPlayAudio: (Story, String) -> Unit,
    onPauseAudio: () -> Unit,
    onResumeAudio: () -> Unit,
    onStopAudio: () -> Unit,
    onSetSpeechRate: (Float) -> Unit,
    onSelectStory: (Story) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    BackHandler { onBack() }

    // 0 = Tamil, 1 = English, 2 = Both
    var viewModeIndex by remember { mutableIntStateOf(0) }

    val isCurrentStoryPlaying = audioState.currentStoryId == story.id && audioState.isPlaying
    val isCurrentStoryPaused = audioState.currentStoryId == story.id && audioState.isPaused

    val relatedStories = allStories
        .filter { it.id != story.id && (it.category == story.category || it.isPopular) }
        .take(5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = story.titleTamil,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("detail_back_btn")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TerracottaPrimary
                        )
                    }
                },
                actions = {
                    // Favorite button
                    IconButton(
                        onClick = { onToggleFavorite(story) },
                        modifier = Modifier.testTag("detail_fav_btn")
                    ) {
                        Icon(
                            imageVector = if (story.isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Favorite",
                            tint = if (story.isFavorite) MarigoldGold else MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Share button
                    IconButton(
                        onClick = {
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                type = "text/plain"
                                putExtra(
                                    Intent.EXTRA_SUBJECT,
                                    "பாட்டி தாத்தா கதை உலகம்: ${story.titleTamil}"
                                )
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    """📖 ${story.titleTamil} (${story.titleEnglish})
வகை: ${story.category.tamilName}

${story.contentTamil}

⚖️ நீதி: ${story.moralTamil}

- பாட்டி தாத்தா கதை உலகம் (Grandma & Grandpa Story World)"""
                                )
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "கதையைப் பகிர்க"))
                        },
                        modifier = Modifier.testTag("detail_share_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Story",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp, 8.dp, 16.dp, 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Cover Illustration & Category Pill
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp)),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    ) {
                        if (story.illustrationRes != null) {
                            Image(
                                painter = painterResource(id = story.illustrationRes),
                                contentDescription = story.titleTamil,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(WarmYellowCard),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = story.category.iconEmoji, fontSize = 72.sp)
                            }
                        }

                        // Gradient overlay
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.Black.copy(alpha = 0.35f),
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.65f)
                                        )
                                    )
                                )
                        )

                        // Top Category Pill
                        Surface(
                            color = TerracottaPrimary,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(14.dp)
                                .align(Alignment.TopStart)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            ) {
                                Text(text = story.category.iconEmoji, fontSize = 14.sp)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = story.category.tamilName,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // Bottom Info
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .align(Alignment.BottomStart)
                        ) {
                            Text(
                                text = story.titleTamil,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = story.titleEnglish,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.Schedule,
                                    contentDescription = null,
                                    tint = Color.White.copy(alpha = 0.8f),
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "${story.readingTimeMinutes} நிமிட வாசிப்பு • சொல்பவர்: ${story.storyteller}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White.copy(alpha = 0.85f)
                                )
                            }
                        }
                    }
                }
            }

            // 2. Audio Narrator (TTS Player Panel)
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .testTag("audio_player_panel"),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = WarmYellowCard),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    color = if (isCurrentStoryPlaying) VillageGreenSecondary else TerracottaPrimary,
                                    shape = CircleShape,
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Icon(
                                        imageVector = if (isCurrentStoryPlaying) Icons.Default.VolumeUp else Icons.Default.RecordVoiceOver,
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
                                        text = "கதை கேட்கலாம் (Listen to Story)",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = if (isCurrentStoryPlaying) "குரல் வாசிக்கப்படுகிறது..." else if (isCurrentStoryPaused) "இடைநிறுத்தப்பட்டுள்ளது" else "இயற்கையான தமிழ் உரை ஒலிபரப்பு",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = if (isCurrentStoryPlaying) VillageGreenSecondary else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }

                        // Playback Controls
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if (!isCurrentStoryPlaying && !isCurrentStoryPaused) {
                                Button(
                                    onClick = {
                                        val lang = if (viewModeIndex == 1) "en" else "ta"
                                        onPlayAudio(story, lang)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                                    shape = RoundedCornerShape(24.dp),
                                    modifier = Modifier.testTag("play_story_audio_btn")
                                ) {
                                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("ஒலிபரப்பு தொடங்கு (Play)")
                                }
                            } else {
                                if (isCurrentStoryPlaying) {
                                    Button(
                                        onClick = onPauseAudio,
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                                        shape = RoundedCornerShape(24.dp),
                                        modifier = Modifier.testTag("pause_audio_btn")
                                    ) {
                                        Icon(imageVector = Icons.Default.Pause, contentDescription = null)
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("இடைநிறுத்து (Pause)")
                                    }
                                } else {
                                    Button(
                                        onClick = onResumeAudio,
                                        colors = ButtonDefaults.buttonColors(containerColor = VillageGreenSecondary),
                                        shape = RoundedCornerShape(24.dp),
                                        modifier = Modifier.testTag("resume_audio_btn")
                                    ) {
                                        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text("தொடர்க (Resume)")
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                OutlinedButton(
                                    onClick = onStopAudio,
                                    shape = RoundedCornerShape(24.dp),
                                    modifier = Modifier.testTag("stop_audio_btn")
                                ) {
                                    Icon(imageVector = Icons.Default.Stop, contentDescription = null)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("நிறுத்து (Stop)")
                                }
                            }
                        }

                        // Speed Picker
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Speed,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "வேகம்:",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            listOf(0.8f, 1.0f, 1.2f).forEach { speed ->
                                Surface(
                                    color = if (audioState.speechRate == speed) TerracottaPrimary else Color.Transparent,
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .clickable { onSetSpeechRate(speed) }
                                        .padding(horizontal = 4.dp)
                                ) {
                                    Text(
                                        text = "${speed}x",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = if (audioState.speechRate == speed) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                        fontWeight = if (audioState.speechRate == speed) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }
                        }

                        // Friendly alert if Tamil TTS pack is missing
                        if (audioState.errorMessage != null || !audioState.isTamilSupported) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Surface(
                                color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.tertiary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = audioState.errorMessage
                                            ?: "சாதனத்தில் தமிழ் குரல் அமைப்பு தேவைப்படலாம் (Google Text-to-Speech -> Tamil).",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // 3. Reading Toolbar: Language Selector & Font Sizing
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Font Size Controls
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.FormatSize,
                                    contentDescription = "Font size",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "எழுத்தளவு:",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))

                                Surface(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable { onDecreaseFontSize() }
                                        .testTag("font_decrease_btn")
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text("A-", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                    }
                                }

                                Spacer(modifier = Modifier.width(6.dp))

                                Surface(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable { onResetFontSize() }
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text("${(fontSizeScale * 100).toInt()}%", fontSize = 10.sp)
                                    }
                                }

                                Spacer(modifier = Modifier.width(6.dp))

                                Surface(
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clickable { onIncreaseFontSize() }
                                        .testTag("font_increase_btn")
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text("A+", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    }
                                }
                            }

                            // View Mode Toggle (Tamil / English / Both)
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                val modes = listOf("தமிழ்", "English", "இரண்டும்")
                                modes.forEachIndexed { idx, label ->
                                    Surface(
                                        color = if (viewModeIndex == idx) TerracottaPrimary else MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp),
                                        modifier = Modifier
                                            .clickable { viewModeIndex = idx }
                                            .testTag("lang_toggle_$idx")
                                    ) {
                                        Text(
                                            text = label,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = if (viewModeIndex == idx) FontWeight.Bold else FontWeight.Normal,
                                            color = if (viewModeIndex == idx) Color.White else MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 4. Story Text Content
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = WarmYellowSurface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        val baseFontSize = 17.sp * fontSizeScale
                        val baseLineHeight = 28.sp * fontSizeScale

                        // Tamil Content
                        if (viewModeIndex == 0 || viewModeIndex == 2) {
                            if (viewModeIndex == 2) {
                                Surface(
                                    color = TerracottaPrimary.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    Text(
                                        text = "தமிழ் உரை (Tamil)",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = TerracottaPrimary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            Text(
                                text = story.contentTamil,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = baseFontSize,
                                    lineHeight = baseLineHeight
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        // Divider if both
                        if (viewModeIndex == 2) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // English Translation
                        if (viewModeIndex == 1 || viewModeIndex == 2) {
                            if (viewModeIndex == 2) {
                                Surface(
                                    color = VillageGreenSecondary.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    Text(
                                        text = "English Translation",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = VillageGreenSecondary,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                            }

                            if (story.contentEnglish.isNotBlank()) {
                                Text(
                                    text = story.contentEnglish,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontSize = baseFontSize,
                                        lineHeight = baseLineHeight
                                    ),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            } else {
                                Text(
                                    text = "ஆங்கில மொழிபெயர்ப்பு தற்போது கிடைக்கவில்லை.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            // 5. Golden Parchment Moral / Lesson Card
            if (story.moralTamil.isNotBlank()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .border(
                                width = 2.dp,
                                color = MarigoldGold,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = WarmYellowCard),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "⚖️", fontSize = 24.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "நீதி / பாடம் (Moral / Lesson)",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaPrimary
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = story.moralTamil,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface,
                                lineHeight = 24.sp
                            )

                            if (story.moralEnglish.isNotBlank()) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = story.moralEnglish,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }

            // 6. Related Stories Section
            if (relatedStories.isNotEmpty()) {
                item {
                    Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                        Text(
                            text = "தொடர்புடைய கதைகள் (Related Stories)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(14.dp)
                        ) {
                            items(relatedStories, key = { "related_${it.id}" }) { related ->
                                Box(modifier = Modifier.width(260.dp)) {
                                    StoryCard(
                                        story = related,
                                        onClick = { onSelectStory(related) },
                                        onToggleFavorite = { onToggleFavorite(related) },
                                        onPlayAudio = {
                                            onPlayAudio(related, if (viewModeIndex == 1) "en" else "ta")
                                        },
                                        isCurrentPlaying = audioState.currentStoryId == related.id && audioState.isPlaying
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
