package com.oe.nik.krujzgergely.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oe.nik.krujzgergely.models.LyricsModel

@Dao
interface LyricsDao
{
    @Insert
    suspend fun insertLyrics(lyricsModel: LyricsModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllLyrics(lyricsModelList: List<LyricsModel>)

    @Query("SELECT * FROM lyrics_table")
    fun getAll() : LiveData<List<LyricsModel>>

    @Query("SELECT * FROM lyrics_table WHERE favourite = 1")
    fun getAllFavourites() : LiveData<List<LyricsModel>>

    @Delete
    suspend fun deleteLyrics(lyricsModel: LyricsModel)

    @Update
    suspend fun updateLyrics(lyricsModel: LyricsModel)
}