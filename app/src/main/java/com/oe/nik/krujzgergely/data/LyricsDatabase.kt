package com.oe.nik.krujzgergely.data

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.models.LyricsModel
import kotlinx.coroutines.*

@Database(entities = [LyricsModel::class], version = 5, exportSchema = false)
abstract class LyricsDatabase : RoomDatabase()
{
    abstract fun lyricsDao(): LyricsDao

    private class LyricsDatabaseCallback(private val scope: CoroutineScope, private val resources: Resources) : RoomDatabase.Callback()
    {
        override fun onCreate(db: SupportSQLiteDatabase)
        {
            super.onCreate(db)

            INSTANCE?.let {database ->
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

    companion object
    {
        @Volatile
        var INSTANCE: LyricsDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope, resources: Resources): LyricsDatabase
        {
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }

            synchronized(this)
            {
                val instance =  Room.databaseBuilder(context.applicationContext,LyricsDatabase::class.java,"lyrics_database")
                    .addCallback(LyricsDatabaseCallback(coroutineScope,resources))
                    //.fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance;
            }
        }
    }
}