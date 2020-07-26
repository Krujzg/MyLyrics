package com.oe.nik.krujzgergely.ui.lyrics

import com.oe.nik.krujzgergely.models.LyricsModel

interface IonLyricsClickEvent
{
    fun onLyricsSelected()

    fun onUpdateOptionsClicked()

    fun onDeleteOptionsClicked(lyricsModel: LyricsModel)
}