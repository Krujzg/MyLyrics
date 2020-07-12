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
import com.oe.nik.krujzgergely.models.CrudType
import com.oe.nik.krujzgergely.util.sendNotification

class LyricsItemActivityViewModel(application: Application) : AndroidViewModel(application)
{
    val notificationManager = ContextCompat.getSystemService(application,
        NotificationManager::class.java) as NotificationManager

    private var repository: LyricsRepository

    private var lyricsModel : LyricsModel = LyricsActivityAdapter.currentLyrics

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

    private fun sendNotification(title :String,message : String)
    {
        notificationManager.sendNotification(title, message, CrudType.DELETE, getApplication())
    }

    fun onDisplayPerformerContent() {_displayedPerformer.value = lyricsModel.performer }
    fun onDisplaySongTitleContent() {_displayedTitle.value = lyricsModel.title }
    fun onDisplayLyricsContent() {_displayedLyrics_Text.value = lyricsModel.lyrics_text }
    fun onDisplayYoutubeLinkContent() {_displayedyoutubelink.value = lyricsModel.youtubeLink }

    fun deleteLyricsFromLocalDb()
    {
        val performer = displayedPerformer.value
        val title = displayedTitle.value
        //AlertDialogBeforeDeletion(title!!,performer!!)
        viewModelScope.launch { repository.deleteFromDb(lyricsModel) }

        sendNotification("You have deleted a Lyrics!",
            "Your lyrics was: \n${performer} - ${title}")
    }

    /*
    fun AlertDialogBeforeDeletion(performer: String,title: String )
    {
        val builder = AlertDialog.Builder(getApplication()).apply {
            setTitle("Delete Lyrics")
            setMessage("Do you want to delete to following lyrics:\n$performer - $title")
            setPositiveButton("Yes",null)
            setNegativeButton("No",null)
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
     */
}