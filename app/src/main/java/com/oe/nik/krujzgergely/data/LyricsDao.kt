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

    @Query("SELECT * FROM lyrics_table WHERE song_type = 'JAZZ'")
    fun getAllJazz() : LiveData<List<LyricsModel>>

    @Query("SELECT * FROM lyrics_table WHERE song_type = 'HIPHOP'")
    fun getAllHipHop() : LiveData<List<LyricsModel>>

    @Query("SELECT * FROM lyrics_table WHERE song_type = 'ROCK'")
    fun getAllRock() : LiveData<List<LyricsModel>>

    @Query("SELECT * FROM lyrics_table WHERE song_type = 'METAL'")
    fun getAllMetal() : LiveData<List<LyricsModel>>

    @Query("SELECT * FROM lyrics_table WHERE song_type = 'PUNK'")
    fun getAllPunk() : LiveData<List<LyricsModel>>

    @Query("SELECT * FROM lyrics_table WHERE song_type = 'POP'")
    fun getAllPop() : LiveData<List<LyricsModel>>

    @Query("SELECT * FROM lyrics_table WHERE song_type = 'COUNTRY'")
    fun getAllCountry() : LiveData<List<LyricsModel>>

    @Query("SELECT * FROM lyrics_table WHERE song_type = 'OPERA'")
    fun getAllOpera() : LiveData<List<LyricsModel>>

    @Delete
    suspend fun deleteLyrics(lyricsModel: LyricsModel)

    @Update
    suspend fun updateLyrics(lyricsModel: LyricsModel)
}