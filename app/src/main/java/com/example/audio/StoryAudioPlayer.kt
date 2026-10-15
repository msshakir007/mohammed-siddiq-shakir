package com.example.audio

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

data class AudioState(
    val isInitialized: Boolean = false,
    val isTamilSupported: Boolean = true,
    val isPlaying: Boolean = false,
    val isPaused: Boolean = false,
    val currentStoryId: String? = null,
    val currentChunkIndex: Int = 0,
    val totalChunks: Int = 0,
    val speechRate: Float = 1.0f,
    val pitch: Float = 1.0f,
    val errorMessage: String? = null
)

class StoryAudioPlayer(private val context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private val _audioState = MutableStateFlow(AudioState())
    val audioState: StateFlow<AudioState> = _audioState.asStateFlow()

    private var currentTextChunks: List<String> = emptyList()
    private var activeStoryId: String? = null
    private var pausedIndex: Int = 0

    init {
        tts = TextToSpeech(context.applicationContext, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val ttsEngine = tts
            if (ttsEngine != null) {
                val tamilLocale = Locale("ta", "IN")
                val langResult = ttsEngine.isLanguageAvailable(tamilLocale)
                val isSupported = langResult != TextToSpeech.LANG_MISSING_DATA &&
                        langResult != TextToSpeech.LANG_NOT_SUPPORTED

                if (isSupported) {
                    ttsEngine.language = tamilLocale
                    _audioState.value = _audioState.value.copy(
                        isInitialized = true,
                        isTamilSupported = true,
                        errorMessage = null
                    )
                } else {
                    // Try generic tamil locale "ta"
                    val genericTamil = Locale("ta")
                    val genericResult = ttsEngine.isLanguageAvailable(genericTamil)
                    val genericSupported = genericResult != TextToSpeech.LANG_MISSING_DATA &&
                            genericResult != TextToSpeech.LANG_NOT_SUPPORTED

                    if (genericSupported) {
                        ttsEngine.language = genericTamil
                        _audioState.value = _audioState.value.copy(
                            isInitialized = true,
                            isTamilSupported = true,
                            errorMessage = null
                        )
                    } else {
                        _audioState.value = _audioState.value.copy(
                            isInitialized = true,
                            isTamilSupported = false,
                            errorMessage = "உங்கள் சாதனத்தில் தமிழ் TTS குரல் இன்னும் பதிவிறக்கப்படவில்லை. Android Text-to-Speech அமைப்புகளில் தமிழ் குரல் கோப்பை நிறுவவும்."
                        )
                    }
                }

                ttsEngine.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        _audioState.value = _audioState.value.copy(
                            isPlaying = true,
                            isPaused = false
                        )
                    }

                    override fun onDone(utteranceId: String?) {
                        val index = utteranceId?.toIntOrNull() ?: 0
                        val next = index + 1
                        if (next < currentTextChunks.size && _audioState.value.isPlaying) {
                            pausedIndex = next
                            _audioState.value = _audioState.value.copy(
                                currentChunkIndex = next
                            )
                            speakChunk(next)
                        } else {
                            _audioState.value = _audioState.value.copy(
                                isPlaying = false,
                                isPaused = false,
                                currentChunkIndex = 0
                            )
                            pausedIndex = 0
                        }
                    }

                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        _audioState.value = _audioState.value.copy(
                            isPlaying = false,
                            isPaused = false,
                            errorMessage = "ஆடியோ வாசிப்பில் பிழை ஏற்பட்டது."
                        )
                    }
                })
            }
        } else {
            _audioState.value = _audioState.value.copy(
                isInitialized = true,
                isTamilSupported = false,
                errorMessage = "Text-to-Speech தொடங்குவதில் பிழை ஏற்பட்டது."
            )
        }
    }

    fun playStory(storyId: String, text: String, languageCode: String = "ta") {
        val ttsEngine = tts ?: return

        // Set locale depending on language
        if (languageCode == "en") {
            ttsEngine.language = Locale.ENGLISH
        } else {
            ttsEngine.language = Locale("ta", "IN")
        }

        // Split text into readable sentences/paragraphs
        val chunks = text.split("\n\n", ".\n", ". ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }

        if (chunks.isEmpty()) return

        currentTextChunks = chunks
        activeStoryId = storyId
        pausedIndex = 0

        _audioState.value = _audioState.value.copy(
            currentStoryId = storyId,
            currentChunkIndex = 0,
            totalChunks = chunks.size,
            isPlaying = true,
            isPaused = false,
            errorMessage = null
        )

        applySpeechParameters()
        speakChunk(0)
    }

    private fun speakChunk(index: Int) {
        val ttsEngine = tts ?: return
        if (index >= currentTextChunks.size) return
        val chunk = currentTextChunks[index]

        val params = Bundle()
        ttsEngine.speak(chunk, TextToSpeech.QUEUE_FLUSH, params, index.toString())
    }

    fun pause() {
        tts?.stop()
        _audioState.value = _audioState.value.copy(
            isPlaying = false,
            isPaused = true
        )
    }

    fun resume() {
        if (_audioState.value.isPaused && activeStoryId != null) {
            _audioState.value = _audioState.value.copy(
                isPlaying = true,
                isPaused = false
            )
            applySpeechParameters()
            speakChunk(pausedIndex)
        }
    }

    fun stop() {
        tts?.stop()
        pausedIndex = 0
        currentTextChunks = emptyList()
        activeStoryId = null
        _audioState.value = _audioState.value.copy(
            isPlaying = false,
            isPaused = false,
            currentStoryId = null,
            currentChunkIndex = 0,
            totalChunks = 0
        )
    }

    fun setSpeechRate(rate: Float) {
        _audioState.value = _audioState.value.copy(speechRate = rate)
        applySpeechParameters()
    }

    fun setPitch(pitch: Float) {
        _audioState.value = _audioState.value.copy(pitch = pitch)
        applySpeechParameters()
    }

    private fun applySpeechParameters() {
        tts?.setSpeechRate(_audioState.value.speechRate)
        tts?.setPitch(_audioState.value.pitch)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
