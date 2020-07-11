package com.oe.nik.krujzgergely.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="lyrics_table")
data class LyricsModel(

    @PrimaryKey(autoGenerate = true)
    val id: Long=0L,

    @ColumnInfo(name="performer")
    var performer: String= "",

    @ColumnInfo(name="title")
    var title: String= "",

    @ColumnInfo(name="lyrics_text")
    var lyrics_text: String= "",

    @ColumnInfo(name="song_type")
    var song_type: String,

    @ColumnInfo(name="times_watched")
    var times_watched: Int=0,

    @ColumnInfo(name="favourite")
    var favourite: Boolean = false,

    @ColumnInfo(name="youtubeLink")
    var youtubeLink: String = "")






