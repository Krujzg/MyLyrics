package com.oe.nik.krujzgergely.data

import android.content.res.Resources
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.models.LyricsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LyricsDatabaseCallback(private val scope: CoroutineScope, private val resources: Resources) : RoomDatabase.Callback()
{
    override fun onCreate(db: SupportSQLiteDatabase)
    {
        super.onCreate(db)

        LyricsDatabase.INSTANCE?.let { database ->
            scope.launch {
                val lyricsDao = database.lyricsDao()
                setPrePopulationDataIntoLocalDB(lyricsDao)
            }
        }
    }

    private suspend fun setPrePopulationDataIntoLocalDB(lyricsDao: LyricsDao)
    {
        val lyricsList = getPrePopulationDataFromJson()
        lyricsDao.insertAllLyrics(lyricsList)
    }
    private fun getPrePopulationDataFromJson() : List<LyricsModel>
    {
        val jsonString = resources.openRawResource(R.raw.lyrics).bufferedReader().use { it.readText() }
        val typeToken = object : TypeToken<List<LyricsModel>>() {}.type
        return Gson().fromJson(jsonString, typeToken)
    }
}