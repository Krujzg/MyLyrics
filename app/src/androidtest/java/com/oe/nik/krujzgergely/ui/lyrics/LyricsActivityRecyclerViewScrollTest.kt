package com.oe.nik.krujzgergely.ui.lyrics

import androidx.test.espresso.action.ViewActions.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.oe.nik.krujzgergely.R.id.*
import com.oe.nik.krujzgergely.ui.main.MainActivity
import com.oe.nik.krujzgergely.ui.androidtestutil.perform
import com.oe.nik.krujzgergely.ui.androidtestutil.testrule.EspressoIdlingResourceRule
import org.junit.Rule
import org.junit.Test

class LyricsActivityRecyclerViewScrollTest
{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewAll()
    {
        signInButton perform click()
        all_recycler_view perform scrollTo()
        all_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewFavorite()
    {
        signInButton perform click()
        favourite_recycler_view perform scrollTo()
        favourite_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewJazz()
    {
        signInButton perform click()
        jazz_recycler_view perform scrollTo()
        jazz_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewPop()
    {
        signInButton perform click()
        pop_recycler_view perform scrollTo()
        pop_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewPunk()
    {
        signInButton perform click()
        punk_recycler_view perform scrollTo()
        punk_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewRock()
    {
        signInButton perform click()
        rock_recycler_view perform scrollTo()
        rock_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewMetal()
    {
        signInButton perform click()
        metal_recycler_view perform scrollTo()
        metal_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewCountry()
    {
        signInButton perform click()
        country_recycler_view perform scrollTo()
        country_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewOpera()
    {
        signInButton perform click()
        opera_recycler_view perform scrollTo()
        opera_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }

    @Test
    fun test_checkIfAppDoesntCrashWhenScrollingToEndOfRecyclerViewHipHop()
    {
        signInButton perform click()
        hiphop_recycler_view perform scrollTo()
        hiphop_recycler_view perform ScrollToEndOfRecyclerViewAction()
    }
}