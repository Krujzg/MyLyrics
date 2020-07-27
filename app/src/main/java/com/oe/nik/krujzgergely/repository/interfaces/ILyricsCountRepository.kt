package com.oe.nik.krujzgergely.repository.interfaces

import androidx.lifecycle.LiveData
import com.oe.nik.krujzgergely.models.LyricsModel

interface ILyricsCountRepository
{
    suspend fun getAllLyricsCountFromLocalDB() : Int

    suspend fun getFavouriteLyricsCountFromLocalDB() : Int

    suspend fun getJazzLyricsCountFromLocalDB() : Int

    suspend fun getHipHopLyricsCountFromLocalDB() : Int

    suspend fun getRockLyricsCountFromLocalDB() : Int

    suspend fun getMetalLyricsCountFromLocalDB() :Int

    suspend fun getPunkLyricsCountFromLocalDB() : Int

    suspend fun getPopLyricsCountFromLocalDB() : Int

    suspend fun getCountryLyricsCountFromLocalDB() : Int

    suspend fun getOperaLyricsCountFromLocalDB() : Int
}