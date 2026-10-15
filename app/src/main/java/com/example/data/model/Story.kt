package com.example.data.model

import androidx.annotation.DrawableRes
import com.example.R

enum class StoryCategory(
    val tamilName: String,
    val englishName: String,
    val iconEmoji: String,
    val description: String
) {
    PAATI_KATHAIGAL(
        "பாட்டி சொல்லும் கதைகள்",
        "Grandma's Stories",
        "👵",
        "அன்பும் பாசமும் நிறைந்த பாட்டி சொன்ன வாழ்வியல் கதைகள்"
    ),
    THAATHA_KATHAIGAL(
        "தாத்தா சொல்லும் கதைகள்",
        "Grandpa's Stories",
        "👴",
        "அனுபவமும் அறிவும் நிறைந்த தாத்தா சொன்ன பழங்காலக் கதைகள்"
    ),
    NEETHI_KATHAIGAL(
        "நீதிக்கதைகள்",
        "Moral Stories",
        "⚖️",
        "நல்ல ஒழுக்கத்தையும் நற்பண்புகளையும் கற்றுத்தரும் நீதிகள்"
    ),
    VILANGU_KATHAIGAL(
        "விலங்குக் கதைகள்",
        "Animal Tales",
        "🐘",
        "காட்டு விலங்குகள், பறவைகளின் சுவாரசியமான கதைகள்"
    ),
    TENALI_RAMAN(
        "தெனாலிராமன் கதைகள்",
        "Tenali Raman Stories",
        "🎭",
        "தெனாலிராமனின் புத்திசாலித்தனம் மற்றும் நகைச்சுவைக் கதைகள்"
    ),
    AKBAR_BIRBAL(
        "அக்பர் பீர்பால் கதைகள்",
        "Akbar Birbal Stories",
        "👑",
        "அரசவையில் பீர்பாலின் சமயோசித புத்தி கூர்மை கதைகள்"
    ),
    NAATTUPURA_KATHAIGAL(
        "தமிழ் நாட்டுப்புறக் கதைகள்",
        "Tamil Folk Tales",
        "🌾",
        "தமிழக கிராமங்களின் தொன்மை வாய்ந்த நாட்டுப்புறக் கதைகள்"
    ),
    GRAMATHU_KATHAIGAL(
        "கிராமத்துக் கதைகள்",
        "Village Tales",
        "🏡",
        "வயல்வெளிகள், பனைமரங்கள் நிறைந்த எளிய கிராமிய வாழ்க்கை"
    ),
    KULANTHAIGAL(
        "குழந்தைகளுக்கான கதைகள்",
        "Children's Stories",
        "🎈",
        "மழலைகளுக்கான எளிய, இனிமையான கற்பனைக் கதைகள்"
    ),
    PURANA_KATHAIGAL(
        "புராணக் கதைகள்",
        "Mythological Stories",
        "🛕",
        "இந்திய கலாச்சாரம் மற்றும் புராண வரலாற்றுக் கதைகள்"
    );

    companion object {
        fun fromTamil(name: String): StoryCategory {
            return entries.firstOrNull { it.tamilName.equals(name, ignoreCase = true) }
                ?: PAATI_KATHAIGAL
        }
    }
}

data class Story(
    val id: String,
    val titleTamil: String,
    val titleEnglish: String,
    val category: StoryCategory,
    val contentTamil: String,
    val contentEnglish: String,
    val moralTamil: String,
    val moralEnglish: String,
    val storyteller: String,
    val readingTimeMinutes: Int,
    @DrawableRes val illustrationRes: Int? = null,
    val isFavorite: Boolean = false,
    val isFeatured: Boolean = false,
    val isPopular: Boolean = false,
    val isPublished: Boolean = true,
    val dateAdded: String = "2026-10-14"
)
