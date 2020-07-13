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

    fun getJazzLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getJazzLyricsFromLocalDB() }

    fun getHipHopLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getHipHopLyricsFromLocalDB() }

    fun getRockLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getRockLyricsFromLocalDB() }

    fun getMetalLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getMetalLyricsFromLocalDB() }

    fun getPunkLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getPunkLyricsFromLocalDB() }

    fun getPopLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getPopLyricsFromLocalDB() }

    fun getCountryLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getCountryLyricsFromLocalDB() }

    fun getOperaLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsRepository.getOperaLyricsFromLocalDB() }
}