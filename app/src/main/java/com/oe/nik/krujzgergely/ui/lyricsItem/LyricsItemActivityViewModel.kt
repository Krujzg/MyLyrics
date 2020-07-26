package com.oe.nik.krujzgergely.ui.lyricsItem

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.models.enums.CrudType
import com.oe.nik.krujzgergely.repository.LyricsRepository
import com.oe.nik.krujzgergely.services.SpotifyService
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivityAdapter
import com.oe.nik.krujzgergely.util.sendNotification
import kotlinx.coroutines.launch


class LyricsItemActivityViewModel(application: Application) : AndroidViewModel(application)
{
    private val notificationManager = ContextCompat.getSystemService(application,
        NotificationManager::class.java) as NotificationManager

    private var repository: LyricsRepository

    private var lyricsModel : LyricsModel = LyricsActivityAdapter.currentLyrics

    private var _displayedPerformer : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedTitle :MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedLyricsText : MutableLiveData<String> = MutableLiveData<String>()
    private var _YoutubeLink : MutableLiveData<String> = MutableLiveData<String>()
    private var _SpotifyLink : MutableLiveData<String> = MutableLiveData<String>()
    private var _YoutubeMusicLink : MutableLiveData<String> = MutableLiveData<String>()

    val displayedPerformer: LiveData<String>
        get() = _displayedPerformer

    val displayedTitle: LiveData<String>
        get() = _displayedTitle

    val displayedLyricsText: LiveData<String>
        get() = _displayedLyricsText

    val YoutubeLink: LiveData<String>
        get() = _YoutubeLink

    val SpotifyLink: LiveData<String>
        get() = _SpotifyLink

    val YoutubeMusicLink: LiveData<String>
        get() = _YoutubeMusicLink

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
        getYoutubeLinkContent()
        getSpotifyLinkContent()
        getYoutubeMusicLinkContent()
    }

    fun playSpotifyLink() { SpotifyService.play(SpotifyLink.value!!) }

    fun pauseSpotifyLink() { SpotifyService.pause() }

    private fun onDisplayPerformerContent() {_displayedPerformer.value = lyricsModel.performer }
    private fun onDisplaySongTitleContent() {_displayedTitle.value = lyricsModel.title }
    private fun onDisplayLyricsContent() {_displayedLyricsText.value = lyricsModel.lyrics_text }
    private fun getYoutubeLinkContent() {_YoutubeLink.value = lyricsModel.youtubeLink }
    private fun getSpotifyLinkContent() {_SpotifyLink.value = lyricsModel.spotifyLink }
    private fun getYoutubeMusicLinkContent() {_YoutubeMusicLink.value = lyricsModel.youtubeMusicLink }

    private fun sendNotification(title :String,message : String)
    {
        notificationManager.sendNotification(title, message, CrudType.DELETE, getApplication())
    }

    fun deleteLyricsFromLocalDb()
    {
        val performer = displayedPerformer.value
        val title = displayedTitle.value
        viewModelScope.launch { repository.deleteFromDb(lyricsModel) }

        sendNotification("You have deleted this Lyrics:", "$performer - $title")
    }
}