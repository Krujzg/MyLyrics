package com.oe.nik.krujzgergely.ui.lyrics

import androidx.test.espresso.action.ViewActions.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.oe.nik.krujzgergely.R.id.*
import com.oe.nik.krujzgergely.ui.androidtestutil.checkThatTextIs
import com.oe.nik.krujzgergely.ui.main.MainActivity
import com.oe.nik.krujzgergely.ui.androidtestutil.perform
import com.oe.nik.krujzgergely.ui.androidtestutil.performClickOnTheIndexOfRecyclerViewItem
import com.oe.nik.krujzgergely.ui.androidtestutil.testrule.EspressoIdlingResourceRule
import com.oe.nik.krujzgergely.util.testutil.getFakeLyrics
import org.junit.Rule
import org.junit.Test

class LyricsActivityOperaRecyclerViewTest
{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    val LIST_ITEM_IN_TEST = 0
    val LYRICS_IN_TEST_OPERA = getFakeLyrics().filter{it.song_type == "OPERA"}[LIST_ITEM_IN_TEST]

    @Test
    fun test_isLyricsItemPerformerIsEqualsWithTestDataAfterItHasBeenClickedInTheOPERARecyclerView()
    {
        signInButton perform click()
        opera_recycler_view perform scrollTo()
        opera_recycler_view performClickOnTheIndexOfRecyclerViewItem LIST_ITEM_IN_TEST
        performer checkThatTextIs LYRICS_IN_TEST_OPERA.performer
    }

    @Test
    fun test_isLyricsItemTitleIsEqualsWithTestDataAfterItHasBeenClickedInTheOPERARecyclerView()
    {
        signInButton perform click()
        opera_recycler_view perform scrollTo()
        opera_recycler_view performClickOnTheIndexOfRecyclerViewItem LIST_ITEM_IN_TEST
        performer checkThatTextIs LYRICS_IN_TEST_OPERA.title
    }

    @Test
    fun test_isLyricsItemLyricsTextIsEqualsWithTestDataAfterItHasBeenClickedInTheOPERARecyclerView()
    {
        signInButton perform click()
        opera_recycler_view perform scrollTo()
        opera_recycler_view performClickOnTheIndexOfRecyclerViewItem LIST_ITEM_IN_TEST
        performer checkThatTextIs LYRICS_IN_TEST_OPERA.lyrics_text
    }
}