package com.oe.nik.krujzgergely.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.repository.interfaces.ILyricsRepository

class FakeLyricsRepository(var lyricsList : MutableList<LyricsModel>) :
    ILyricsRepository
{

    override suspend fun saveNewLyricsIntoDb(newLyrics: LyricsModel) {
        lyricsList.add(newLyrics)
    }

    override suspend fun deleteFromDb(lyricsModel: LyricsModel) {
        lyricsList.remove(lyricsModel)
    }

    override suspend fun updateInDb(lyricsModel: LyricsModel) {
        val isLyricsListContainsLyricsModelParameter = lyricsList.contains(lyricsModel)

        if (isLyricsListContainsLyricsModelParameter)
        {
            val lyricsItemIndex = lyricsList.indexOf(lyricsModel)
            lyricsList[lyricsItemIndex] = lyricsModel
        }
    }

    override fun getAllLyricsFromLocalDB(): LiveData<List<LyricsModel>> { return MutableLiveData(lyricsList) }

    override fun getFavouriteLyricsFromLocalDB(): LiveData<List<LyricsModel>> {

        val favoriteLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.favourite) { favoriteLyricsList.add(it) }
        }
        return MutableLiveData(favoriteLyricsList)
    }

    override fun getJazzLyricsFromLocalDB(): LiveData<List<LyricsModel>> {
        val jazzLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.song_type == "JAZZ") { jazzLyricsList.add(it) }
        }
        return MutableLiveData(jazzLyricsList)
    }

    override fun getHipHopLyricsFromLocalDB(): LiveData<List<LyricsModel>> {
        val hiphopLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.song_type == "HIPHOP") { hiphopLyricsList.add(it) }
        }
        return MutableLiveData(hiphopLyricsList)
    }

    override fun getRockLyricsFromLocalDB(): LiveData<List<LyricsModel>> {
        val rockLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.song_type == "ROCK") { rockLyricsList.add(it) }
        }
        return MutableLiveData(rockLyricsList)
    }

    override fun getMetalLyricsFromLocalDB(): LiveData<List<LyricsModel>> {
        val metalLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.song_type == "METAL") { metalLyricsList.add(it) }
        }
        return MutableLiveData(metalLyricsList)
    }

    override fun getPunkLyricsFromLocalDB(): LiveData<List<LyricsModel>> {
        val punkLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.song_type == "PUNK") { punkLyricsList.add(it) }
        }
        return MutableLiveData(punkLyricsList)
    }

    override fun getPopLyricsFromLocalDB(): LiveData<List<LyricsModel>> {
        val popLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.song_type == "POP") { popLyricsList.add(it) }
        }
        return MutableLiveData(popLyricsList)
    }

    override fun getCountryLyricsFromLocalDB(): LiveData<List<LyricsModel>> {
        val countryLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.song_type == "COUNTRY") { countryLyricsList.add(it) }
        }
        return MutableLiveData(countryLyricsList)
    }

    override fun getOperaLyricsFromLocalDB(): LiveData<List<LyricsModel>> {
        val operaLyricsList = mutableListOf<LyricsModel>()
        lyricsList.forEach {
            if (it.song_type == "OPERA") { operaLyricsList.add(it) }
        }
        return MutableLiveData(operaLyricsList)
    }

}