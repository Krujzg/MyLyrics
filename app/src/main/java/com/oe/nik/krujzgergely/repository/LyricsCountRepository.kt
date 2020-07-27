package com.oe.nik.krujzgergely.repository

import com.oe.nik.krujzgergely.data.LyricsCountDao
import com.oe.nik.krujzgergely.repository.interfaces.ILyricsCountRepository

class LyricsCountRepository(private val lyricsCountDao: LyricsCountDao) : ILyricsCountRepository
{
    override suspend fun getAllLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllCount()

    override suspend fun getFavouriteLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllFavouritesCount()

    override suspend fun getJazzLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllJazzCount()

    override suspend fun getHipHopLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllHipHopCount()

    override suspend fun getRockLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllRockCount()

    override suspend fun getMetalLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllMetalCount()

    override suspend fun getPunkLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllPunkCount()

    override suspend fun getPopLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllPopCount()

    override suspend fun getCountryLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllCountryCount()

    override suspend fun getOperaLyricsCountFromLocalDB(): Int = lyricsCountDao.getAllOperaCount()
}