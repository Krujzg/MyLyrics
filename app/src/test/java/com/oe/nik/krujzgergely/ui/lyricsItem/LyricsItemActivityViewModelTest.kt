package com.oe.nik.krujzgergely.ui.lyricsItem

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LyricsItemActivityViewModelTest {

    private lateinit var lyricsItemActivityViewModel : LyricsItemActivityViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp()
    {
        lyricsItemActivityViewModel = LyricsItemActivityViewModel(ApplicationProvider.getApplicationContext())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun deleteLyricsFromLocalDb_DeletesUserFromLocalDb()
    {
        lyricsItemActivityViewModel.deleteLyricsFromLocalDb()
    }
}