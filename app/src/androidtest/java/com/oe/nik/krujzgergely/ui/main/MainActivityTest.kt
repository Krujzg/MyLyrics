package com.oe.nik.krujzgergely.ui.main

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.oe.nik.krujzgergely.R.drawable.*
import com.oe.nik.krujzgergely.R.id.*
import com.oe.nik.krujzgergely.ui.androidtestutil.checkThat
import com.oe.nik.krujzgergely.ui.androidtestutil.perform
import com.oe.nik.krujzgergely.ui.androidtestutil.testrule.EspressoIdlingResourceRule
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest
{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    //val context = ApplicationProvider.getApplicationContext<Application>()

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun test_isAMainActivityDisplayed() { constraintmain checkThat isDisplayed() }

    @Test
    fun test_isGoogleSignInButtonIsVisible() { signInButton checkThat isDisplayed() }

    @Test
    fun test_isSpotifySignInButtonIsVisible() { spotify_login_btn checkThat isDisplayed()}

    @Test
    fun test_isGoogleSignInButtonProgressBarIsInVisibleBeforeLoginButtonClicked() { progressBarGoogle checkThat not(isDisplayed()) }

    @Test
    fun test_isSpotifySignInButtonProgressBarIsInVisibleBeforeLoginButtonClicked() { progressBarSpotify checkThat not(isDisplayed()) }

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
    fun test_isMainActivityBackgroundWelcomePagePNG() { constraintmain checkThat hasBackground(welcomescreen) }
}