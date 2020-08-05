package com.oe.nik.krujzgergely.repository

import androidx.lifecycle.LiveData
import com.oe.nik.krujzgergely.data.LyricsDao
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.repository.interfaces.ILyricsRepository

class LyricsRepository(private val lyricsDao: LyricsDao) : ILyricsRepository
{
    override suspend fun saveNewLyricsIntoDb(newLyrics : LyricsModel) { lyricsDao.insertLyrics(lyricsModel = newLyrics) }

    override suspend fun deleteFromDb(lyricsModel: LyricsModel) { lyricsDao.deleteLyrics(lyricsModel) }

    override suspend fun updateInDb(lyricsModel: LyricsModel) { lyricsDao.updateLyrics(lyricsModel) }

    override fun getAllLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAll() }

    override fun getFavouriteLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllFavourites() }

    override fun getJazzLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllJazz() }

    override fun getHipHopLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllHipHop() }

    override fun getRockLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllRock() }

    override fun getMetalLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllMetal() }

    override fun getPunkLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllPunk() }

    override fun getPopLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllPop() }

    override fun getCountryLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllCountry() }

    override fun getOperaLyricsFromLocalDB() : LiveData<List<LyricsModel>> { return lyricsDao.getAllOpera() }
}