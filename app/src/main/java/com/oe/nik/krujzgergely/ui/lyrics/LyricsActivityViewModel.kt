package com.oe.nik.krujzgergely.ui.lyrics

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.models.enums.CrudType
import com.oe.nik.krujzgergely.repository.LyricsRepository
import com.oe.nik.krujzgergely.ui.main.MainActivity
import com.oe.nik.krujzgergely.util.sendNotification
import kotlinx.coroutines.launch

class LyricsActivityViewModel(application: Application) : AndroidViewModel(application)
{
    private val lyricsRepository: LyricsRepository

    private val notificationManager = ContextCompat.getSystemService(application,
        NotificationManager::class.java) as NotificationManager

    init
    {
        val lyricsDao= LyricsDatabase
            .getDatabase(application,viewModelScope,application.resources)
            .lyricsDao()
        lyricsRepository = LyricsRepository(lyricsDao)
    }
    private fun sendNotification(title :String,message : String)
    {
        notificationManager.sendNotification(title, message, CrudType.DELETE, getApplication())
    }

    fun getAllLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getAllLyricsFromLocalDB() }

    fun getFavoriteLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getFavouriteLyricsFromLocalDB() }

    fun getJazzLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getJazzLyricsFromLocalDB() }

    fun getHipHopLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getHipHopLyricsFromLocalDB() }

    fun getRockLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getRockLyricsFromLocalDB() }

    fun getMetalLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getMetalLyricsFromLocalDB() }

    fun getPunkLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getPunkLyricsFromLocalDB() }

    fun getPopLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getPopLyricsFromLocalDB() }

    fun getCountryLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getCountryLyricsFromLocalDB() }

    fun getOperaLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getOperaLyricsFromLocalDB() }

    fun deleteLyricsFromLocalDB(lyricsModel: LyricsModel)
    {
        val performer = lyricsModel.performer
        val title = lyricsModel.title
        viewModelScope.launch{
            lyricsRepository.deleteFromDb(lyricsModel)
        }
        sendNotification("You have deleted this Lyrics:", "$performer - $title")
    }
}