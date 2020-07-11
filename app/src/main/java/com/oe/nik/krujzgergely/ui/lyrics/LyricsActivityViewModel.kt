package com.oe.nik.krujzgergely.ui.lyrics

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.repository.LyricsRepository

class LyricsActivityViewModel(application: Application) : AndroidViewModel(application)
{
    private val lyricsRepository: LyricsRepository

    init
    {
        val lyricsDao= LyricsDatabase
            .getDatabase(application,viewModelScope,application.resources)
            .lyricsDao()
        lyricsRepository = LyricsRepository(lyricsDao)
    }

    fun getAllLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getAllLyricsFromLocalDB() }

    fun getFavoriteLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getFavouriteLyricsFromLocalDB() }
}