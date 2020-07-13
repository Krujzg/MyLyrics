package com.oe.nik.krujzgergely.repository

import androidx.lifecycle.LiveData
import com.oe.nik.krujzgergely.data.LyricsDao
import com.oe.nik.krujzgergely.models.LyricsModel

class LyricsRepository(private val lyricsDao: LyricsDao)
{
    suspend fun saveNewLyricsIntoDb(newLyrics : LyricsModel) { lyricsDao.insertLyrics(lyricsModel = newLyrics) }

    suspend fun deleteFromDb(lyricsModel: LyricsModel) { lyricsDao.deleteLyrics(lyricsModel) }

    suspend fun updateInDb(lyricsModel: LyricsModel) { lyricsDao.updateLyrics(lyricsModel) }

    fun getAllLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAll() }

    fun getFavouriteLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllFavourites() }

    fun getJazzLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllJazz() }

    fun getHipHopLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllHipHop() }

    fun getRockLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllRock() }

    fun getMetalLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllMetal() }

    fun getPunkLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllPunk() }

    fun getPopLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllPop() }

    fun getCountryLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllCountry() }

    fun getOperaLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllOpera() }
}