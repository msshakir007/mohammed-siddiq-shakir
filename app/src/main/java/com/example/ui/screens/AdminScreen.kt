package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.Story
import com.example.data.model.StoryCategory
import androidx.compose.material.icons.filled.FileUpload
import org.json.JSONArray
import org.json.JSONObject
import com.example.ui.theme.TerracottaPrimary
import com.example.ui.theme.VillageGreenSecondary
import com.example.ui.theme.WarmYellowCard
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    stories: List<Story>,
    onAddStory: (Story) -> Unit,
    onBulkAddStories: (List<Story>) -> Unit,
    onUpdateStory: (Story) -> Unit,
    onDeleteStory: (String) -> Unit,
    onResetDefaults: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isAuthenticated by remember { mutableStateOf(false) }
    var pinInput by remember { mutableStateOf("") }
    var pinError by remember { mutableStateOf(false) }

    var showStoryDialog by remember { mutableStateOf(false) }
    var showBulkImportDialog by remember { mutableStateOf(false) }
    var editingStory by remember { mutableStateOf<Story?>(null) }
    var storyToDelete by remember { mutableStateOf<Story?>(null) }
    var showResetConfirm by remember { mutableStateOf(false) }

    // PIN Authentication Gate
    if (!isAuthenticated) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("admin_login_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = WarmYellowCard),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        color = TerracottaPrimary,
                        shape = CircleShape,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(14.dp)
                                .fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "நிர்வாகி அங்கீகாரம் (Admin Access)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "கதைகளை நிர்வகிக்க ரகசிய குறியீட்டை (PIN) உள்ளிடவும். (மாதிரி PIN: 1234)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = pinInput,
                        onValueChange = {
                            pinInput = it
                            pinError = false
                        },
                        label = { Text("நிர்வாக PIN") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        isError = pinError,
                        supportingText = {
                            if (pinError) {
                                Text("தவறான PIN. தயவுசெய்து 1234 என உள்ளிடவும்.")
                            }
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("admin_pin_input")
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            if (pinInput == "1234") {
                                isAuthenticated = true
                                pinError = false
                            } else {
                                pinError = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("admin_login_btn")
                    ) {
                        Text("உள்நுழை (Verify & Enter)")
                    }
                }
            }
        }
        return
    }

    // Authenticated Admin Dashboard
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp, 16.dp, 16.dp, 100.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AdminPanelSettings,
                            contentDescription = null,
                            tint = TerracottaPrimary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "நிர்வாகப் பலகை (Admin Dashboard)",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                text = "கதைகளைச் சேர்க்க, திருத்த மற்றும் நிர்வகிக்கலாம்",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { showBulkImportDialog = true },
                            colors = ButtonDefaults.buttonColors(containerColor = VillageGreenSecondary),
                            modifier = Modifier.testTag("bulk_import_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.FileUpload,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("தொகுதி இறக்குமதி (Bulk)", style = MaterialTheme.typography.labelSmall)
                        }

                        OutlinedButton(
                            onClick = { showResetConfirm = true },
                            modifier = Modifier.testTag("reset_defaults_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.RestartAlt,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("மீட்டமை", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }

            // Stat Cards
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "மொத்தக் கதைகள்",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "${stories.size}",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = TerracottaPrimary
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "வெளியிடப்பட்டவை",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "${stories.count { it.isPublished }}",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = VillageGreenSecondary
                            )
                        }
                    }
                }
            }

            // Story Management List
            items(stories, key = { it.id }) { story ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("admin_story_row_${story.id}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = story.titleTamil,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                if (!story.isPublished) {
                                    Surface(
                                        color = MaterialTheme.colorScheme.errorContainer,
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        Text(
                                            text = "வரைவு (Draft)",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onErrorContainer,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }

                            Text(
                                text = "${story.category.tamilName} • ${story.readingTimeMinutes} நிமிடம்",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Publish toggle
                            IconButton(onClick = {
                                onUpdateStory(story.copy(isPublished = !story.isPublished))
                            }) {
                                Icon(
                                    imageVector = if (story.isPublished) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Toggle Published",
                                    tint = if (story.isPublished) VillageGreenSecondary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            // Edit button
                            IconButton(
                                onClick = {
                                    editingStory = story
                                    showStoryDialog = true
                                },
                                modifier = Modifier.testTag("edit_story_${story.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit",
                                    tint = TerracottaPrimary
                                )
                            }

                            // Delete button
                            IconButton(
                                onClick = { storyToDelete = story },
                                modifier = Modifier.testTag("delete_story_${story.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }

        // Add Story Floating Action Button
        FloatingActionButton(
            onClick = {
                editingStory = null
                showStoryDialog = true
            },
            containerColor = TerracottaPrimary,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 90.dp)
                .testTag("admin_add_story_fab")
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Story")
        }
    }

    // Add / Edit Story Dialog
    if (showStoryDialog) {
        StoryFormDialog(
            initialStory = editingStory,
            onDismiss = { showStoryDialog = false },
            onSave = { story ->
                if (editingStory != null) {
                    onUpdateStory(story)
                } else {
                    onAddStory(story)
                }
                showStoryDialog = false
            }
        )
    }

    // Delete Confirmation Dialog
    if (storyToDelete != null) {
        AlertDialog(
            onDismissRequest = { storyToDelete = null },
            title = { Text("கதையை நீக்க வேண்டுமா?") },
            text = { Text("\"${storyToDelete?.titleTamil}\" கதையை நிரந்தரமாக நீக்க விரும்புகிறீர்களா?") },
            confirmButton = {
                Button(
                    onClick = {
                        storyToDelete?.let { onDeleteStory(it.id) }
                        storyToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("நீக்கு (Delete)")
                }
            },
            dismissButton = {
                TextButton(onClick = { storyToDelete = null }) {
                    Text("ரத்து (Cancel)")
                }
            }
        )
    }

    // Bulk Import Dialog
    if (showBulkImportDialog) {
        BulkImportDialog(
            onDismiss = { showBulkImportDialog = false },
            onImport = { storiesList ->
                onBulkAddStories(storiesList)
                showBulkImportDialog = false
            }
        )
    }

    // Reset Defaults Confirmation Dialog
    if (showResetConfirm) {
        AlertDialog(
            onDismissRequest = { showResetConfirm = false },
            title = { Text("ஆரம்ப நிலைக்கு மீட்டமைக்க வேண்டுமா?") },
            text = { Text("அனைத்துக் கதைகளும் நீக்கப்பட்டு அசல் மாதிரி கதைகள் மீண்டும் நிறுவப்படும்.") },
            confirmButton = {
                Button(
                    onClick = {
                        onResetDefaults()
                        showResetConfirm = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary)
                ) {
                    Text("மீட்டமை (Reset)")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirm = false }) {
                    Text("ரத்து")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryFormDialog(
    initialStory: Story?,
    onDismiss: () -> Unit,
    onSave: (Story) -> Unit
) {
    var titleTamil by remember { mutableStateOf(initialStory?.titleTamil ?: "") }
    var titleEnglish by remember { mutableStateOf(initialStory?.titleEnglish ?: "") }
    var selectedCategory by remember { mutableStateOf(initialStory?.category ?: StoryCategory.PAATI_KATHAIGAL) }
    var contentTamil by remember { mutableStateOf(initialStory?.contentTamil ?: "") }
    var contentEnglish by remember { mutableStateOf(initialStory?.contentEnglish ?: "") }
    var moralTamil by remember { mutableStateOf(initialStory?.moralTamil ?: "") }
    var moralEnglish by remember { mutableStateOf(initialStory?.moralEnglish ?: "") }
    var storyteller by remember { mutableStateOf(initialStory?.storyteller ?: "பாட்டி (Grandmother)") }
    var readingTime by remember { mutableStateOf(initialStory?.readingTimeMinutes?.toString() ?: "3") }
    var isPublished by remember { mutableStateOf(initialStory?.isPublished ?: true) }

    var categoryDropdownExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (initialStory != null) "கதையைத் திருத்துக (Edit Story)" else "புதிய கதை சேர்க்க (Add Story)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = titleTamil,
                    onValueChange = { titleTamil = it },
                    label = { Text("கதைத் தலைப்பு (தமிழ்) *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("form_title_tamil")
                )

                OutlinedTextField(
                    value = titleEnglish,
                    onValueChange = { titleEnglish = it },
                    label = { Text("English Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("form_title_english")
                )

                // Category Dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryDropdownExpanded,
                    onExpandedChange = { categoryDropdownExpanded = it }
                ) {
                    OutlinedTextField(
                        value = "${selectedCategory.iconEmoji} ${selectedCategory.tamilName}",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("கதை வகை (Category)") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryDropdownExpanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = categoryDropdownExpanded,
                        onDismissRequest = { categoryDropdownExpanded = false }
                    ) {
                        StoryCategory.entries.forEach { category ->
                            DropdownMenuItem(
                                text = { Text("${category.iconEmoji} ${category.tamilName} (${category.englishName})") },
                                onClick = {
                                    selectedCategory = category
                                    categoryDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = storyteller,
                    onValueChange = { storyteller = it },
                    label = { Text("கதை சொல்பவர் (Storyteller)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = readingTime,
                    onValueChange = { readingTime = it },
                    label = { Text("வாசிப்பு நேரம் (நிமிடங்கள்)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = contentTamil,
                    onValueChange = { contentTamil = it },
                    label = { Text("கதை உரை (தமிழ்) *") },
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth().testTag("form_content_tamil")
                )

                OutlinedTextField(
                    value = contentEnglish,
                    onValueChange = { contentEnglish = it },
                    label = { Text("English Translation") },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = moralTamil,
                    onValueChange = { moralTamil = it },
                    label = { Text("நீதி / பாடம் (தமிழ்)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = moralEnglish,
                    onValueChange = { moralEnglish = it },
                    label = { Text("Moral (English)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("உடனே வெளியிடவா (Publish immediately)?")
                    Switch(
                        checked = isPublished,
                        onCheckedChange = { isPublished = it }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (titleTamil.isNotBlank() && contentTamil.isNotBlank()) {
                        val newStory = Story(
                            id = initialStory?.id ?: "story_${UUID.randomUUID()}",
                            titleTamil = titleTamil.trim(),
                            titleEnglish = titleEnglish.trim(),
                            category = selectedCategory,
                            contentTamil = contentTamil.trim(),
                            contentEnglish = contentEnglish.trim(),
                            moralTamil = moralTamil.trim(),
                            moralEnglish = moralEnglish.trim(),
                            storyteller = storyteller.trim(),
                            readingTimeMinutes = readingTime.toIntOrNull() ?: 3,
                            illustrationRes = initialStory?.illustrationRes ?: R.drawable.img_village_hero,
                            isFavorite = initialStory?.isFavorite ?: false,
                            isFeatured = initialStory?.isFeatured ?: false,
                            isPopular = initialStory?.isPopular ?: false,
                            isPublished = isPublished,
                            dateAdded = initialStory?.dateAdded ?: "2026-10-14"
                        )
                        onSave(newStory)
                    }
                },
                enabled = titleTamil.isNotBlank() && contentTamil.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                modifier = Modifier.testTag("form_save_btn")
            ) {
                Text("சேமி (Save)")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("ரத்து (Cancel)")
            }
        }
    )
}

@Composable
fun BulkImportDialog(
    onDismiss: () -> Unit,
    onImport: (List<Story>) -> Unit
) {
    var jsonText by remember { mutableStateOf("") }
    var parseError by remember { mutableStateOf<String?>(null) }
    var parsedCount by remember { mutableStateOf(0) }

    val sampleTemplate = """[
  {
    "titleTamil": "புதிய கிராமத்துக் கதை 1",
    "titleEnglish": "Village Tale 1",
    "category": "கிராமத்துக் கதைகள்",
    "contentTamil": "ஒரு ஊரில் அழகான ஒரு குளம் இருந்தது. அங்கே மீன்களும் கொக்குகளும் வாழ்ந்து வந்தன...",
    "contentEnglish": "Once in a quiet village there was a peaceful pond with birds and fish...",
    "moralTamil": "ஒற்றுமையே பலம்.",
    "moralEnglish": "Unity is strength.",
    "storyteller": "பாட்டி",
    "readingTimeMinutes": 3
  },
  {
    "titleTamil": "புதிய நீதிக்கதை 2",
    "titleEnglish": "Moral Story 2",
    "category": "நீதிக்கதைகள்",
    "contentTamil": "நேர்மையாக உழைத்த ஒரு சிறுவன் தன் குடும்பத்தைக் காப்பாற்றினான்...",
    "contentEnglish": "An honest boy worked hard to support his loving family...",
    "moralTamil": "உழைப்பே உயர்வு.",
    "moralEnglish": "Hard work always pays.",
    "storyteller": "தாத்தா",
    "readingTimeMinutes": 2
  }
]"""

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.FileUpload,
                    contentDescription = null,
                    tint = VillageGreenSecondary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "தொகுதி இறக்குமதி (Bulk Import JSON)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "ஒரே நேரத்தில் பல கதைகளை JSON வடிவில் உள்ளிடவும். கீழே உள்ள பொத்தானை அழுத்தி மாதிரி வடிவத்தைப் பார்க்கலாம்:",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedButton(
                    onClick = {
                        jsonText = sampleTemplate
                        parseError = null
                        parsedCount = 2
                    },
                    modifier = Modifier.fillMaxWidth().testTag("load_sample_json_btn")
                ) {
                    Text("மாதிரி JSON படிவம் ஏற்று (Load Sample Template)")
                }

                OutlinedTextField(
                    value = jsonText,
                    onValueChange = {
                        jsonText = it
                        parseError = null
                        try {
                            if (it.isNotBlank()) {
                                val array = JSONArray(it)
                                parsedCount = array.length()
                            } else {
                                parsedCount = 0
                            }
                        } catch (e: Exception) {
                            parsedCount = 0
                        }
                    },
                    label = { Text("கதைகள் JSON Array *") },
                    placeholder = { Text("[ { \"titleTamil\": \"...\", ... } ]") },
                    minLines = 8,
                    maxLines = 14,
                    modifier = Modifier.fillMaxWidth().testTag("bulk_json_input")
                )

                if (parsedCount > 0) {
                    Surface(
                        color = VillageGreenSecondary.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "✓ $parsedCount கதைகள் கண்டறியப்பட்டன (Ready to import)",
                            style = MaterialTheme.typography.labelSmall,
                            color = VillageGreenSecondary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }

                if (parseError != null) {
                    Surface(
                        color = MaterialTheme.colorScheme.errorContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = parseError ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (jsonText.isBlank()) {
                        parseError = "JSON உரையை உள்ளிடவும்."
                        return@Button
                    }
                    try {
                        val jsonArray = JSONArray(jsonText)
                        val storiesList = mutableListOf<Story>()
                        for (i in 0 until jsonArray.length()) {
                            val obj = jsonArray.getJSONObject(i)
                            val titleTamil = obj.optString("titleTamil", "").trim()
                            val titleEnglish = obj.optString("titleEnglish", "").trim()
                            val catString = obj.optString("category", obj.optString("categoryName", "பாட்டி சொல்லும் கதைகள்"))
                            val contentTamil = obj.optString("contentTamil", "").trim()
                            val contentEnglish = obj.optString("contentEnglish", "").trim()
                            val moralTamil = obj.optString("moralTamil", "").trim()
                            val moralEnglish = obj.optString("moralEnglish", "").trim()
                            val storyteller = obj.optString("storyteller", "பாட்டி").trim()
                            val readingTime = obj.optInt("readingTimeMinutes", 3)

                            if (titleTamil.isNotEmpty() && contentTamil.isNotEmpty()) {
                                storiesList.add(
                                    Story(
                                        id = obj.optString("id", "bulk_${UUID.randomUUID()}"),
                                        titleTamil = titleTamil,
                                        titleEnglish = titleEnglish,
                                        category = StoryCategory.fromTamil(catString),
                                        contentTamil = contentTamil,
                                        contentEnglish = contentEnglish,
                                        moralTamil = moralTamil,
                                        moralEnglish = moralEnglish,
                                        storyteller = storyteller,
                                        readingTimeMinutes = readingTime,
                                        illustrationRes = R.drawable.img_village_hero,
                                        isFavorite = false,
                                        isFeatured = obj.optBoolean("isFeatured", false),
                                        isPopular = obj.optBoolean("isPopular", false),
                                        isPublished = obj.optBoolean("isPublished", true),
                                        dateAdded = "2026-10-14"
                                    )
                                )
                            }
                        }

                        if (storiesList.isEmpty()) {
                            parseError = "சரியான கதைகள் எதுவும் JSON இல் கிடைக்கவில்லை. தலைப்பு மற்றும் உரை கட்டாயம்."
                        } else {
                            onImport(storiesList)
                        }
                    } catch (e: Exception) {
                        parseError = "தவறான JSON வடிவம்: ${e.localizedMessage}"
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = VillageGreenSecondary),
                modifier = Modifier.testTag("submit_bulk_import_btn")
            ) {
                Text("இறக்குமதி செய் (Import All)")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("ரத்து (Cancel)")
            }
        }
    )
}

