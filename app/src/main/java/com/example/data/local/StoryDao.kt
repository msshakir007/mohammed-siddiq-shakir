package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {

    @Query("SELECT * FROM stories ORDER BY isFeatured DESC, dateAdded DESC")
    fun getAllStories(): Flow<List<StoryEntity>>

    @Query("SELECT * FROM stories WHERE id = :storyId LIMIT 1")
    suspend fun getStoryById(storyId: String): StoryEntity?

    @Query("SELECT * FROM stories WHERE isFavorite = 1 ORDER BY dateAdded DESC")
    fun getFavoriteStories(): Flow<List<StoryEntity>>

    @Query("SELECT * FROM stories WHERE categoryName = :categoryName ORDER BY dateAdded DESC")
    fun getStoriesByCategory(categoryName: String): Flow<List<StoryEntity>>

    @Query("SELECT * FROM stories WHERE isFeatured = 1")
    fun getFeaturedStories(): Flow<List<StoryEntity>>

    @Query("SELECT * FROM stories WHERE isPopular = 1")
    fun getPopularStories(): Flow<List<StoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: StoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stories: List<StoryEntity>)

    @Update
    suspend fun updateStory(story: StoryEntity)

    @Query("UPDATE stories SET isFavorite = :isFavorite WHERE id = :storyId")
    suspend fun updateFavorite(storyId: String, isFavorite: Boolean)

    @Delete
    suspend fun deleteStory(story: StoryEntity)

    @Query("DELETE FROM stories WHERE id = :storyId")
    suspend fun deleteById(storyId: String)

    @Query("SELECT COUNT(*) FROM stories")
    suspend fun getCount(): Int

    @Query("DELETE FROM stories")
    suspend fun clearAll()
}
