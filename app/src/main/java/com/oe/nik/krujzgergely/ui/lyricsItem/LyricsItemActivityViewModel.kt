package com.oe.nik.krujzgergely.ui.lyricsItem

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.repository.LyricsRepository
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivityAdapter
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.oe.nik.krujzgergely.models.enums.CrudType
import com.oe.nik.krujzgergely.util.sendNotification

class LyricsItemActivityViewModel(application: Application) : AndroidViewModel(application)
{
    private val notificationManager = ContextCompat.getSystemService(application,
        NotificationManager::class.java) as NotificationManager

    private var repository: LyricsRepository

    private var lyricsModel : LyricsModel = LyricsActivityAdapter.currentLyrics

    private var _displayedPerformer : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedTitle :MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedLyricsText : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedYoutubeLink : MutableLiveData<String> = MutableLiveData<String>()

    val displayedPerformer: LiveData<String>
        get() = _displayedPerformer

    val displayedTitle: LiveData<String>
        get() = _displayedTitle

    val displayedLyricsText: LiveData<String>
        get() = _displayedLyricsText

    val displayedYoutubeLink: LiveData<String>
        get() = _displayedYoutubeLink

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

    private fun onDisplayPerformerContent() {_displayedPerformer.value = lyricsModel.performer }
    private fun onDisplaySongTitleContent() {_displayedTitle.value = lyricsModel.title }
    private fun onDisplayLyricsContent() {_displayedLyricsText.value = lyricsModel.lyrics_text }
    private fun onDisplayYoutubeLinkContent() {_displayedYoutubeLink.value = lyricsModel.youtubeLink }

    private fun sendNotification(title :String,message : String)
    {
        notificationManager.sendNotification(title, message, CrudType.DELETE, getApplication())
    }

    fun deleteLyricsFromLocalDb()
    {
        val performer = displayedPerformer.value
        val title = displayedTitle.value
        viewModelScope.launch { repository.deleteFromDb(lyricsModel) }

        sendNotification("You have deleted a Lyrics!",
            "Your lyrics was: \n${performer} - ${title}")
    }
}