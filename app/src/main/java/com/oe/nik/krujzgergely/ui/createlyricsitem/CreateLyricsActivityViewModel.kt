package com.oe.nik.krujzgergely.ui.createlyricsitem

import android.app.Application
import android.app.NotificationManager
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.models.enums.CrudType
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.repository.LyricsRepository
import com.oe.nik.krujzgergely.util.sendNotification
import kotlinx.coroutines.launch


class CreateLyricsActivityViewModel(application: Application) : AndroidViewModel(application), Observable
{
    private val notificationManager = ContextCompat.getSystemService(application,
        NotificationManager::class.java) as NotificationManager

    private var repository: LyricsRepository

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry()}

    @Bindable
    var displayedPerformer = MutableLiveData<String>()

    @Bindable
    var displayedTitle = MutableLiveData<String>()

    @Bindable
    var displayedLyricsText = MutableLiveData<String>()

    @Bindable
    var displayedyoutubelink = MutableLiveData<String>()

    @Bindable
    var displayedspotifylink = MutableLiveData<String>()

    @Bindable
    var displayedyoutubeMusiclink = MutableLiveData<String>()

    private var songtype  : String = "JAZZ"

    init
    {
        val lyricsDao= LyricsDatabase
            .getDatabase(application,viewModelScope,application.resources)
            .lyricsDao()
        this.repository = LyricsRepository(lyricsDao)
    }

    fun onSelectItem(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) { songtype = parent!!.selectedItem.toString() }

    fun saveNewLyricsIntoLocalDb()
    {
        if(isSaveLyricsButtonClickable())
        {
            viewModelScope.launch {
                repository.saveNewLyricsIntoDb(
                    LyricsModel(
                        performer = displayedPerformer.value!!,
                        title = displayedTitle.value!!,
                        song_type = songtype,
                        times_watched = 0,
                        favourite = true,
                        lyrics_text = displayedLyricsText.value!!,
                        youtubeLink = displayedyoutubelink.value!!,
                        spotifyLink = displayedspotifylink.value!!,
                        youtubeMusicLink = displayedyoutubeMusiclink.value!!
                    )
                )
            }
            sendNotification("Your new lyrics is:", "${displayedPerformer.value} - ${displayedTitle.value}")
        }
        else { showToast("Some of the fields are not filled") }
    }

    private fun isSaveLyricsButtonClickable() : Boolean = checkIfTheEditTextsValuesAreNullOrEmpty()

    private fun checkIfTheEditTextsValuesAreNullOrEmpty() : Boolean
    {
        return !(displayedPerformer.value.isNullOrEmpty() ||
                displayedTitle.value.isNullOrEmpty() ||
                displayedLyricsText.value.isNullOrEmpty() ||
                displayedyoutubelink.value.isNullOrEmpty() ||
                displayedspotifylink.value.isNullOrEmpty() ||
                displayedyoutubeMusiclink.value.isNullOrEmpty())
    }

    private fun sendNotification(title :String,message : String)
    {
        notificationManager.sendNotification(title, message, CrudType.INSERT, getApplication())
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) { callbacks.add(callback) }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) { callbacks.remove(callback) }

    private fun showToast(message: String){ Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show() }
}