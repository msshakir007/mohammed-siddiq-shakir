package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.Story
import com.example.data.model.StoryCategory
import com.example.ui.components.StoryCard
import com.example.ui.theme.BorderSand
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.VillageGreenSecondary
import com.example.ui.theme.WarmYellowCard
import com.example.ui.viewmodel.SortOption
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    stories: List<Story>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    selectedCategory: StoryCategory?,
    onCategorySelected: (StoryCategory?) -> Unit,
    sortBy: SortOption,
    onSortOptionSelected: (SortOption) -> Unit,
    onStoryClick: (Story) -> Unit,
    onToggleFavorite: (Story) -> Unit,
    onPlayAudio: (Story) -> Unit,
    currentPlayingStoryId: String?,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val featuredStories = stories.filter { it.isFeatured }
    val popularStories = stories.filter { it.isPopular }
    val recentStories = stories.sortedByDescending { it.dateAdded }.take(4)

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        // 1. Hero Banner with Welcome Message & Village Illustration
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .testTag("hero_banner_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = WarmYellowCard),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_village_hero),
                            contentDescription = "Village Storytelling Scene",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            WarmYellowCard.copy(alpha = 0.85f),
                                            WarmYellowCard
                                        )
                                    )
                                )
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "வணக்கம்! பாட்டி தாத்தா கதை உலகத்திற்கு\nஉங்களை அன்புடன் வரவேற்கிறோம்!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary,
                            textAlign = TextAlign.Center,
                            lineHeight = 26.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "பழங்காலக் கதைகளைப் படிப்போம், பாரம்பரியத்தை அடுத்த தலைமுறைக்குக் கொண்டு செல்வோம்!",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                scope.launch {
                                    listState.animateScrollToItem(index = 3)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = TerracottaPrimary,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(28.dp),
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .height(50.dp)
                                .testTag("read_stories_cta_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "கதைகளைப் படிக்கலாம்",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }

        // 2. Search Bar
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(28.dp))
                        .testTag("story_search_input"),
                    shape = RoundedCornerShape(28.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = TerracottaPrimary,
                        unfocusedBorderColor = BorderSand
                    ),
                    placeholder = {
                        Text(
                            text = "கதையைத் தேடுக… (தலைப்பு, வகை, நீதி)",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = TerracottaPrimary
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchQueryChanged("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    },
                    singleLine = true
                )
            }
        }

        // 3. Category Filter Chips (Horizontal)
        item {
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Text(
                    text = "கதை வகைகள் (Categories)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { onCategorySelected(null) },
                        label = { Text("அனைத்தும் (All)") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TerracottaPrimary,
                            selectedLabelColor = Color.White
                        ),
                        modifier = Modifier.testTag("filter_all")
                    )

                    StoryCategory.entries.forEach { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = {
                                onCategorySelected(if (selectedCategory == category) null else category)
                            },
                            label = { Text("${category.iconEmoji} ${category.tamilName}") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = VillageGreenSecondary,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.testTag("filter_${category.name}")
                        )
                    }
                }
            }
        }

        // 4. Sort Options Row
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "Sort",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "வரிசைப்படுத்து:",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    SortOption.entries.forEach { option ->
                        Surface(
                            color = if (sortBy == option) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .clickable { onSortOptionSelected(option) }
                                .padding(2.dp)
                                .testTag("sort_${option.name}")
                        ) {
                            Text(
                                text = option.tamilLabel,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (sortBy == option) FontWeight.Bold else FontWeight.Normal,
                                color = if (sortBy == option) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        // 5. Featured Stories Section (Carousel) - only when not actively searching
        if (searchQuery.isBlank() && selectedCategory == null && featuredStories.isNotEmpty()) {
            item {
                Column(modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = TerracottaPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "சிறப்புக் கதைகள் (Featured Stories)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(featuredStories, key = { "featured_${it.id}" }) { story ->
                            Box(modifier = Modifier.width(280.dp)) {
                                StoryCard(
                                    story = story,
                                    onClick = { onStoryClick(story) },
                                    onToggleFavorite = { onToggleFavorite(story) },
                                    onPlayAudio = { onPlayAudio(story) },
                                    isCurrentPlaying = currentPlayingStoryId == story.id
                                )
                            }
                        }
                    }
                }
            }
        }

        // 6. Popular Stories Section (only on default view)
        if (searchQuery.isBlank() && selectedCategory == null && popularStories.isNotEmpty()) {
            item {
                Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = VillageGreenSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "பிரபலமான கதைகள் (Popular Stories)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(popularStories, key = { "popular_${it.id}" }) { story ->
                            Box(modifier = Modifier.width(280.dp)) {
                                StoryCard(
                                    story = story,
                                    onClick = { onStoryClick(story) },
                                    onToggleFavorite = { onToggleFavorite(story) },
                                    onPlayAudio = { onPlayAudio(story) },
                                    isCurrentPlaying = currentPlayingStoryId == story.id
                                )
                            }
                        }
                    }
                }
            }
        }

        // 7. Stories List Header
        item {
            val title = when {
                searchQuery.isNotBlank() -> "தேடல் முடிவுகள் (${stories.size})"
                selectedCategory != null -> "${selectedCategory.tamilName} (${stories.size})"
                else -> "அனைத்துக் கதைகள் (${stories.size})"
            }

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
        }

        // 8. Story List Items
        if (stories.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "📖",
                        fontSize = 54.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "மன்னிக்கவும்! கதைகள் எதுவும் கிடைக்கவில்லை.",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "வேறு வார்த்தைகளை அல்லது வகைகளைத் தேர்ந்தெடுத்துப் பாருங்கள்.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onSearchQueryChanged("")
                            onCategorySelected(null)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary)
                    ) {
                        Text("அனைத்துக் கதைகளையும் காட்டு")
                    }
                }
            }
        } else {
            items(stories, key = { it.id }) { story ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    StoryCard(
                        story = story,
                        onClick = { onStoryClick(story) },
                        onToggleFavorite = { onToggleFavorite(story) },
                        onPlayAudio = { onPlayAudio(story) },
                        isCurrentPlaying = currentPlayingStoryId == story.id
                    )
                }
            }
        }

        // 9. Traditional Village Footer Section
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🌾 பாட்டி தாத்தா கதை உலகம் 🌾",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TerracottaPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "தமிழர்களின் தொன்மை வாய்ந்த வாழ்வியல் கதைகள், நாட்டுப்புறக் கதைகள், நீதிக்கதைகளை எளிய முறையில் வாசிக்கவும் கேட்கவும் உருவாக்கப்பட்ட பிரத்யேக தளம்.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "அன்புடன் தமிழ் குழந்தைகளுக்கு சமர்ப்பணம்",
                        style = MaterialTheme.typography.labelSmall,
                        color = VillageGreenSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
