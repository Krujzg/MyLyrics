package com.oe.nik.krujzgergely.data

import androidx.room.Dao
import androidx.room.Query
import java.util.concurrent.atomic.AtomicInteger

@Dao
interface LyricsCountDao
{
    @Query("SELECT COUNT(performer) FROM lyrics_table")
    suspend fun getAllCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE favourite = 1")
    suspend fun getAllFavouritesCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE song_type = 'JAZZ'")
    suspend fun getAllJazzCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE song_type = 'HIPHOP'")
    suspend fun getAllHipHopCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE song_type = 'ROCK'")
    suspend fun getAllRockCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE song_type = 'METAL'")
    suspend fun getAllMetalCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE song_type = 'PUNK'")
    suspend fun getAllPunkCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE song_type = 'POP'")
    suspend fun getAllPopCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE song_type = 'COUNTRY'")
    suspend fun getAllCountryCount() : Int

    @Query("SELECT COUNT(performer) FROM lyrics_table WHERE song_type = 'OPERA'")
    suspend fun getAllOperaCount() : Int
}