package com.oe.nik.krujzgergely.ui.updatelyricsitem

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.models.CrudType
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.repository.LyricsRepository
import com.oe.nik.krujzgergely.ui.lyrics.LyricsesActivityAdapter
import com.oe.nik.krujzgergely.util.sendNotification
import kotlinx.coroutines.launch

class UpdateLyricsItemViewModel (application: Application) : AndroidViewModel(application), Observable
{
    val notificationManager = ContextCompat.getSystemService(application,
        NotificationManager::class.java) as NotificationManager

    private var repository: LyricsRepository

    private var lyricsModel : LyricsModel = LyricsesActivityAdapter.currentLyrics

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    @Bindable
    var displayedPerformer  = MutableLiveData<String>()

    @Bindable
    var displayedTitle = MutableLiveData<String>()

    @Bindable
    var displayedLyrics_Text = MutableLiveData<String>()

    @Bindable
    var displayedyoutubelink = MutableLiveData<String>()

    @Bindable
    var toggleButtonFavorite = MutableLiveData<Boolean>()


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
        onDisplayFavoriteContent()
    }

    private fun updateLyricsModelLocally()
    {
        lyricsModel.apply {
            performer = displayedPerformer.value!!
            title = displayedTitle.value!!
            lyrics_text = displayedLyrics_Text.value!!
            youtubeLink = displayedyoutubelink.value!!
            favourite = toggleButtonFavorite.value!!
        }
    }

    private fun sendNotification(title :String,message : String)
    {
        notificationManager.sendNotification(title, message,CrudType.UPDATE,getApplication())
    }

    fun onDisplayPerformerContent() {displayedPerformer.value = lyricsModel.performer }
    fun onDisplaySongTitleContent() {displayedTitle.value = lyricsModel.title }
    fun onDisplayLyricsContent() {displayedLyrics_Text.value = lyricsModel.lyrics_text }
    fun onDisplayYoutubeLinkContent() {displayedyoutubelink.value = lyricsModel.youtubeLink }
    fun onDisplayFavoriteContent() {toggleButtonFavorite.value = lyricsModel.favourite }

    fun updateLyricsFromLocalDb()
    {
        updateLyricsModelLocally()
        viewModelScope.launch { repository.updateInDb(lyricsModel) }
        sendNotification("You have updated a Lyrics!",
            "Your updated lyrics was: \n${displayedPerformer.value} - ${displayedTitle.value}")
    }

    fun onSelectedFormOfToggleFavoriteButton()
    {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) { callbacks.add(callback) }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) { callbacks.remove(callback) }

}