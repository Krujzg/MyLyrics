package com.oe.nik.krujzgergely.repository

import androidx.lifecycle.LiveData
import com.oe.nik.krujzgergely.data.LyricsDao
import com.oe.nik.krujzgergely.models.LyricsModel

class LyricsRepository(private val lyricsDao: LyricsDao)
{
    suspend fun saveNewLyricsIntoDb(newLyrics : LyricsModel) { lyricsDao.insertLyrics(lyricsModel = newLyrics) }

    suspend fun deleteFromDb(lyricsModel: LyricsModel) { lyricsDao.deleteLyrics(lyricsModel) }

    fun getAllLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAll() }

    fun getFavouriteLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllFavourites() }
}