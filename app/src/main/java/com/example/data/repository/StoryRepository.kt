package com.example.data.repository

import com.example.data.local.StoryDao
import com.example.data.local.StoryEntity
import com.example.data.model.Story
import com.example.data.sample.SampleStories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoryRepository(private val storyDao: StoryDao) {

    val allStories: Flow<List<Story>> = storyDao.getAllStories().map { entities ->
        entities.map { it.toStory() }
    }

    val favoriteStories: Flow<List<Story>> = storyDao.getFavoriteStories().map { entities ->
        entities.map { it.toStory() }
    }

    val featuredStories: Flow<List<Story>> = storyDao.getFeaturedStories().map { entities ->
        entities.map { it.toStory() }
    }

    val popularStories: Flow<List<Story>> = storyDao.getPopularStories().map { entities ->
        entities.map { it.toStory() }
    }

    suspend fun ensureInitialized() {
        val count = storyDao.getCount()
        if (count == 0) {
            val entities = SampleStories.stories.map { StoryEntity.fromStory(it) }
            storyDao.insertAll(entities)
        }
    }

    suspend fun getStoryById(id: String): Story? {
        return storyDao.getStoryById(id)?.toStory()
    }

    suspend fun toggleFavorite(storyId: String, isCurrentlyFavorite: Boolean) {
        storyDao.updateFavorite(storyId, !isCurrentlyFavorite)
    }

    suspend fun insertStory(story: Story) {
        storyDao.insertStory(StoryEntity.fromStory(story))
    }

    suspend fun insertStories(stories: List<Story>) {
        val entities = stories.map { StoryEntity.fromStory(it) }
        storyDao.insertAll(entities)
    }

    suspend fun updateStory(story: Story) {
        storyDao.updateStory(StoryEntity.fromStory(story))
    }

    suspend fun deleteStory(storyId: String) {
        storyDao.deleteById(storyId)
    }

    suspend fun resetToDefaultStories() {
        storyDao.clearAll()
        val entities = SampleStories.stories.map { StoryEntity.fromStory(it) }
        storyDao.insertAll(entities)
    }
}
