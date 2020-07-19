package com.oe.nik.krujzgergely.data

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.models.LyricsModel
import kotlinx.coroutines.*

@Database(entities = [LyricsModel::class], version = 6, exportSchema = false)
abstract class LyricsDatabase : RoomDatabase()
{
    abstract fun lyricsDao(): LyricsDao

    companion object
    {
        @Volatile
        var INSTANCE: LyricsDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope, resources: Resources): LyricsDatabase
        {
            val tempInstance = INSTANCE
            if(tempInstance != null) { return tempInstance }

            synchronized(this)
            {
                val instance =  Room.databaseBuilder(context.applicationContext,LyricsDatabase::class.java,"lyrics_database")
                    .addMigrations(MIGRATION_5_6)
                    .addCallback(LyricsDatabaseCallback(coroutineScope,resources))
                    //.fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance;
            }
        }

        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE lyrics_table ADD COLUMN spotifyLink TEXT")
            }
        }
    }
}