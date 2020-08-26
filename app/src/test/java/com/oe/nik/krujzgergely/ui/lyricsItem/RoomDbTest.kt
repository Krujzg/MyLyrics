package com.oe.nik.krujzgergely.ui.lyricsItem

import android.os.Build
import android.os.Looper
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.oe.nik.krujzgergely.data.LyricsDao
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.util.testutil.getFakeLyrics
import com.oe.nik.krujzgergely.util.testutil.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class RoomDbTest
{
    private lateinit var lyricsDao: LyricsDao
    private lateinit var db : LyricsDatabase

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val testDispatcher = coroutinesTestRule.testDispatcher

    @Before
    fun createDb()
    {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context,LyricsDatabase::class.java).build()
        lyricsDao = db.lyricsDao()
    }

    @After @Throws(IOException::class) fun closeDb() { db.close() }

    @Test
    @Throws(Exception::class)
    fun `testing insertLyrics into LyricsDatabase`() = testDispatcher.runBlockingTest {

        val lyrics = getFakeLyrics()[0]
        lyricsDao.insertLyrics(lyrics)

        val lyricsResult = lyricsDao.getAll()
        assertEquals(lyricsResult.getOrAwaitValue()[0], equals(lyrics))
    }
}