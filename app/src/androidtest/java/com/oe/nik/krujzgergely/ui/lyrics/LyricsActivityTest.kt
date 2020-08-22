package com.oe.nik.krujzgergely.ui.lyrics

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.oe.nik.krujzgergely.R.id.*
import com.oe.nik.krujzgergely.ui.androidtestutil.checkThat
import com.oe.nik.krujzgergely.ui.androidtestutil.checkThatTextIs
import com.oe.nik.krujzgergely.ui.main.MainActivity
import com.oe.nik.krujzgergely.ui.androidtestutil.perform
import com.oe.nik.krujzgergely.ui.androidtestutil.testrule.EspressoIdlingResourceRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class LyricsActivityTest
{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    val LIST_ITEM_IN_TEST = 0

    @Test
    fun test_isLyricsScrollViewVisibleAfterGoogleLoginButtonClicked()
    {
        signInButton perform click()
        lyricsScrollView checkThat isDisplayed()
    }

    @Test
    fun test_isLyricsScrollViewVisibleAfterSpotifyLoginButtonClicked()
    {
        spotify_login_btn perform click()
        lyricsScrollView checkThat isDisplayed()
    }

    @Test
    fun test_isTitleAllTextViewTextEqualsWithALL() { titleAll checkThatTextIs "All" }

    @Test
    fun test_isTitleFavoriteTextViewTextEqualsWithYourFavorites() { titleFavorites checkThatTextIs "Your Favorites" }

    @Test
    fun test_isTitleJazzTextViewTextEqualsWithJazz() { titleJazz checkThatTextIs "Jazz" }

    @Test
    fun test_isTitleHipHopTextViewTextEqualsWithHipHop() { titleJazz checkThatTextIs "Hip Hop" }

    @Test
    fun test_isTitleRockTextViewTextEqualsWithRock() { titleRock checkThatTextIs "Rock" }

    @Test
    fun test_isTitleMetalTextViewTextEqualsWithMetal() { titleMetal checkThatTextIs "Metal" }

    @Test
    fun test_isTitlePunkTextViewTextEqualsWithPunk() { titlePunk checkThatTextIs "Punk" }

    @Test
    fun test_isTitlePopTextViewTextEqualsWithPop() { titlePop checkThatTextIs "Pop" }

    @Test
    fun test_isTitleCountryTextViewTextEqualsWithCountry() { titleCountry checkThatTextIs "Country" }

    @Test
    fun test_isTitleOperaTextViewTextEqualsWithOpera() { titleOpera checkThatTextIs "Opera" }
}