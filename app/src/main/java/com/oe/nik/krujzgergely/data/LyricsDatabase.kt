package com.oe.nik.krujzgergely.data

import android.content.Context
import android.content.res.Resources
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.oe.nik.krujzgergely.models.LyricsModel
import kotlinx.coroutines.CoroutineScope

@Database(entities = [LyricsModel::class], version = 8, exportSchema = false)
abstract class LyricsDatabase : RoomDatabase()
{
    abstract fun lyricsDao(): LyricsDao

    abstract fun lyricsCountDao(): LyricsCountDao

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
                    .addMigrations(MIGRATION_6_7)
                    .addCallback(LyricsDatabaseCallback(coroutineScope,resources))
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

        val MIGRATION_6_7 = object : Migration(6, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE lyrics_table ADD COLUMN youtubeMusicLink TEXT DEFAULT 1 NOT NULL")
            }
        }
    }
}