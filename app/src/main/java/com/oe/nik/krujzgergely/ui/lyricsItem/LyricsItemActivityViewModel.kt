package com.oe.nik.krujzgergely.ui.lyricsItem

import android.app.Application
import androidx.lifecycle.*
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.repository.LyricsRepository
import com.oe.nik.krujzgergely.ui.lyrics.LyricsesActivityAdapter
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class LyricsItemActivityViewModel(application: Application) : AndroidViewModel(application)
{
    private var repository: LyricsRepository

    private var lyricsModel : LyricsModel = LyricsesActivityAdapter.currentLyrics

    private var _displayedPerformer : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedTitle :MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedLyrics_Text : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedyoutubelink : MutableLiveData<String> = MutableLiveData<String>()

    val displayedPerformer: LiveData<String>
        get() = _displayedPerformer

    val displayedTitle: LiveData<String>
        get() = _displayedTitle

    val displayedLyrics_Text: LiveData<String>
        get() = _displayedLyrics_Text

    val displayedyoutubelink: LiveData<String>
        get() = _displayedyoutubelink

    init
    {
        val lyricsDao= LyricsDatabase
            .getDatabase(application,viewModelScope,application.resources)
            .lyricsDao()
        this.repository = LyricsRepository(lyricsDao)
        onDisplayContents()
    }


    private fun onDisplayContents()
    {
        onDisplayPerformerContent()
        onDisplaySongTitleContent()
        onDisplayLyricsContent()
        onDisplayYoutubeLinkContent()
    }
    fun onDisplayPerformerContent() {_displayedPerformer.value = lyricsModel.performer }
    fun onDisplaySongTitleContent() {_displayedTitle.value = lyricsModel.title }
    fun onDisplayLyricsContent() {_displayedLyrics_Text.value = lyricsModel.lyrics_text }
    fun onDisplayYoutubeLinkContent() {_displayedyoutubelink.value = lyricsModel.youtubeLink }

    fun deleteLyricsFromLocalDb() { viewModelScope.launch { repository.deleteFromDb(lyricsModel) } }
}