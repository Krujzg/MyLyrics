package com.oe.nik.krujzgergely.repository.interfaces

import androidx.lifecycle.LiveData
import com.oe.nik.krujzgergely.models.LyricsModel

interface ILyricsRepository
{
    suspend fun saveNewLyricsIntoDb(newLyrics : LyricsModel)

    suspend fun deleteFromDb(lyricsModel: LyricsModel)

    suspend fun updateInDb(lyricsModel: LyricsModel)

    fun getAllLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getFavouriteLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getJazzLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getHipHopLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getRockLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getMetalLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getPunkLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getPopLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getCountryLyricsFromLocalDB() : LiveData<List<LyricsModel>>

    fun getOperaLyricsFromLocalDB() : LiveData<List<LyricsModel>>
}