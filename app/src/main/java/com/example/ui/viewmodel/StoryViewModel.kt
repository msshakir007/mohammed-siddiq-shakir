package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.AudioState
import com.example.audio.StoryAudioPlayer
import com.example.data.local.StoryDatabase
import com.example.data.model.Story
import com.example.data.model.StoryCategory
import com.example.data.repository.StoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class SortOption(val tamilLabel: String, val englishLabel: String) {
    POPULAR("பிரபலமானவை", "Most Popular"),
    NEWEST("புதியவை", "Newest"),
    SHORTEST_READ("குறுகிய கதை", "Shortest Read"),
    ALPHABETICAL("அகரவரிசை", "Alphabetical")
}

enum class AppLanguage(val label: String) {
    TAMIL("தமிழ்"),
    ENGLISH("English"),
    BILINGUAL("இருமொழி (Bilingual)")
}

class StoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StoryRepository
    val audioPlayer: StoryAudioPlayer = StoryAudioPlayer(application)

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow<StoryCategory?>(null)
    val selectedCategory: StateFlow<StoryCategory?> = _selectedCategory.asStateFlow()

    private val _sortBy = MutableStateFlow(SortOption.POPULAR)
    val sortBy: StateFlow<SortOption> = _sortBy.asStateFlow()

    private val _appLanguage = MutableStateFlow(AppLanguage.TAMIL)
    val appLanguage: StateFlow<AppLanguage> = _appLanguage.asStateFlow()

    private val _fontSizeScale = MutableStateFlow(1.0f) // 0.85f, 1.0f, 1.2f, 1.4f
    val fontSizeScale: StateFlow<Float> = _fontSizeScale.asStateFlow()

    private val _selectedStory = MutableStateFlow<Story?>(null)
    val selectedStory: StateFlow<Story?> = _selectedStory.asStateFlow()

    private val _uiMessage = MutableStateFlow<String?>(null)
    val uiMessage: StateFlow<String?> = _uiMessage.asStateFlow()

    init {
        val db = StoryDatabase.getDatabase(application)
        repository = StoryRepository(db.storyDao())
        viewModelScope.launch {
            repository.ensureInitialized()
        }
    }

    val allStories: StateFlow<List<Story>> = repository.allStories
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteStories: StateFlow<List<Story>> = repository.favoriteStories
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val audioState: StateFlow<AudioState> = audioPlayer.audioState

    val filteredStories: StateFlow<List<Story>> = combine(
        allStories,
        _searchQuery,
        _selectedCategory,
        _sortBy
    ) { stories, query, category, sort ->
        var list = stories.filter { it.isPublished }

        // Category filter
        if (category != null) {
            list = list.filter { it.category == category }
        }

        // Search query filter (matches title, content, keywords, moral)
        if (query.isNotBlank()) {
            val q = query.trim().lowercase()
            list = list.filter { story ->
                story.titleTamil.lowercase().contains(q) ||
                        story.titleEnglish.lowercase().contains(q) ||
                        story.contentTamil.lowercase().contains(q) ||
                        story.contentEnglish.lowercase().contains(q) ||
                        story.moralTamil.lowercase().contains(q) ||
                        story.moralEnglish.lowercase().contains(q) ||
                        story.category.tamilName.lowercase().contains(q) ||
                        story.category.englishName.lowercase().contains(q)
            }
        }

        // Sorting
        when (sort) {
            SortOption.POPULAR -> list.sortedWith(
                compareByDescending<Story> { it.isFeatured }
                    .thenByDescending { it.isPopular }
            )
            SortOption.NEWEST -> list.sortedByDescending { it.dateAdded }
            SortOption.SHORTEST_READ -> list.sortedBy { it.readingTimeMinutes }
            SortOption.ALPHABETICAL -> list.sortedBy { it.titleTamil }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategory(category: StoryCategory?) {
        _selectedCategory.value = category
    }

    fun setSortOption(option: SortOption) {
        _sortBy.value = option
    }

    fun setAppLanguage(lang: AppLanguage) {
        _appLanguage.value = lang
    }

    fun increaseFontSize() {
        if (_fontSizeScale.value < 1.4f) {
            _fontSizeScale.value = (_fontSizeScale.value + 0.15f).coerceAtMost(1.4f)
        }
    }

    fun decreaseFontSize() {
        if (_fontSizeScale.value > 0.85f) {
            _fontSizeScale.value = (_fontSizeScale.value - 0.15f).coerceAtLeast(0.85f)
        }
    }

    fun resetFontSize() {
        _fontSizeScale.value = 1.0f
    }

    fun selectStory(story: Story?) {
        _selectedStory.value = story
    }

    fun toggleFavorite(story: Story) {
        viewModelScope.launch {
            repository.toggleFavorite(story.id, story.isFavorite)
            val updated = story.copy(isFavorite = !story.isFavorite)
            if (_selectedStory.value?.id == story.id) {
                _selectedStory.value = updated
            }
        }
    }

    fun playStoryAudio(story: Story, languageCode: String = "ta") {
        val textToRead = if (languageCode == "en" && story.contentEnglish.isNotBlank()) {
            story.contentEnglish
        } else {
            story.contentTamil
        }
        audioPlayer.playStory(story.id, textToRead, languageCode)
    }

    fun pauseAudio() {
        audioPlayer.pause()
    }

    fun resumeAudio() {
        audioPlayer.resume()
    }

    fun stopAudio() {
        audioPlayer.stop()
    }

    fun setSpeechRate(rate: Float) {
        audioPlayer.setSpeechRate(rate)
    }

    fun addStory(story: Story) {
        viewModelScope.launch {
            repository.insertStory(story)
            _uiMessage.value = "கதை வெற்றிகரமாகச் சேர்க்கப்பட்டது!"
        }
    }

    fun bulkAddStories(stories: List<Story>) {
        viewModelScope.launch {
            repository.insertStories(stories)
            _uiMessage.value = "${stories.size} கதைகள் வெற்றிகரமாகச் சேர்க்கப்பட்டன! (Bulk Import Successful)"
        }
    }

    fun updateStory(story: Story) {
        viewModelScope.launch {
            repository.updateStory(story)
            if (_selectedStory.value?.id == story.id) {
                _selectedStory.value = story
            }
            _uiMessage.value = "கதை வெற்றிகரமாகப் புதுப்பிக்கப்பட்டது!"
        }
    }

    fun deleteStory(storyId: String) {
        viewModelScope.launch {
            repository.deleteStory(storyId)
            if (_selectedStory.value?.id == storyId) {
                _selectedStory.value = null
            }
            if (audioState.value.currentStoryId == storyId) {
                audioPlayer.stop()
            }
            _uiMessage.value = "கதை நீக்கப்பட்டது."
        }
    }

    fun resetToDefaultStories() {
        viewModelScope.launch {
            repository.resetToDefaultStories()
            _uiMessage.value = "அனைத்துக் கதைகளும் ஆரம்ப நிலைக்கு மாற்றப்பட்டன!"
        }
    }

    fun clearUiMessage() {
        _uiMessage.value = null
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.shutdown()
    }
}
