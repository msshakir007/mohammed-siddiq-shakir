package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.Story
import com.example.data.model.StoryCategory

@Entity(tableName = "stories")
data class StoryEntity(
    @PrimaryKey
    val id: String,
    val titleTamil: String,
    val titleEnglish: String,
    val categoryName: String,
    val contentTamil: String,
    val contentEnglish: String,
    val moralTamil: String,
    val moralEnglish: String,
    val storyteller: String,
    val readingTimeMinutes: Int,
    val illustrationRes: Int? = null,
    val isFavorite: Boolean = false,
    val isFeatured: Boolean = false,
    val isPopular: Boolean = false,
    val isPublished: Boolean = true,
    val dateAdded: String = "2026-10-14"
) {
    fun toStory(): Story {
        return Story(
            id = id,
            titleTamil = titleTamil,
            titleEnglish = titleEnglish,
            category = StoryCategory.fromTamil(categoryName),
            contentTamil = contentTamil,
            contentEnglish = contentEnglish,
            moralTamil = moralTamil,
            moralEnglish = moralEnglish,
            storyteller = storyteller,
            readingTimeMinutes = readingTimeMinutes,
            illustrationRes = illustrationRes,
            isFavorite = isFavorite,
            isFeatured = isFeatured,
            isPopular = isPopular,
            isPublished = isPublished,
            dateAdded = dateAdded
        )
    }

    companion object {
        fun fromStory(story: Story): StoryEntity {
            return StoryEntity(
                id = story.id,
                titleTamil = story.titleTamil,
                titleEnglish = story.titleEnglish,
                categoryName = story.category.tamilName,
                contentTamil = story.contentTamil,
                contentEnglish = story.contentEnglish,
                moralTamil = story.moralTamil,
                moralEnglish = story.moralEnglish,
                storyteller = story.storyteller,
                readingTimeMinutes = story.readingTimeMinutes,
                illustrationRes = story.illustrationRes,
                isFavorite = story.isFavorite,
                isFeatured = story.isFeatured,
                isPopular = story.isPopular,
                isPublished = story.isPublished,
                dateAdded = story.dateAdded
            )
        }
    }
}
