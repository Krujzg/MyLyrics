package com.oe.nik.krujzgergely.models.enums

enum class SongTypes
{
    JAZZ, HIPHOP, ROCK, METAL, PUNK, POP, COUNTRY, OPERA;

    override fun toString(): String
    {
        return when (this)
        {
            JAZZ -> "JAZZ"
            HIPHOP -> "HIPHOP"
            ROCK -> "ROCK"
            METAL -> "METAL"
            PUNK -> "PUNK"
            POP -> "POP"
            COUNTRY -> "COUNTRY"
            else -> "OPERA"
        }
    }
}