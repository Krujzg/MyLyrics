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

class LyricsActivityMetalRecyclerViewTest
{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    val LIST_ITEM_IN_TEST = 0
    val LYRICS_IN_TEST_METAL = getFakeLyrics().filter{it.song_type == "METAL"}[LIST_ITEM_IN_TEST]

    @Test
    fun test_isLyricsItemPerformerIsEqualsWithTestDataAfterItHasBeenClickedInTheMETALRecyclerView()
    {
        signInButton perform click()
        metal_recycler_view perform scrollTo()
        metal_recycler_view performClickOnTheIndexOfRecyclerViewItem LIST_ITEM_IN_TEST
        performer checkThatTextIs LYRICS_IN_TEST_METAL.performer
    }

    @Test
    fun test_isLyricsItemTitleIsEqualsWithTestDataAfterItHasBeenClickedInTheMETALRecyclerView()
    {
        signInButton perform click()
        metal_recycler_view perform scrollTo()
        metal_recycler_view performClickOnTheIndexOfRecyclerViewItem LIST_ITEM_IN_TEST
        performer checkThatTextIs LYRICS_IN_TEST_METAL.title
    }

    @Test
    fun test_isLyricsItemLyricsTextIsEqualsWithTestDataAfterItHasBeenClickedInTheMETALRecyclerView()
    {
        signInButton perform click()
        metal_recycler_view perform scrollTo()
        metal_recycler_view performClickOnTheIndexOfRecyclerViewItem LIST_ITEM_IN_TEST
        performer checkThatTextIs LYRICS_IN_TEST_METAL.lyrics_text
    }
}