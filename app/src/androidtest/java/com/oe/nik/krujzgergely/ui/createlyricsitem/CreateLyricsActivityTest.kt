package com.oe.nik.krujzgergely.ui.createlyricsitem

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.oe.nik.krujzgergely.R.id.*
import com.oe.nik.krujzgergely.ui.androidtestutil.*
import com.oe.nik.krujzgergely.ui.main.MainActivity
import com.oe.nik.krujzgergely.ui.androidtestutil.testrule.EspressoIdlingResourceRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CreateLyricsActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    val toastMessageText  = "Some of the fields are not filled"

    @Test
    fun test_isCreateActivityDisplayedAfterPlusSignButtonPressedInLyricsActivity()
    {
        signInButton perform click()
        CreateNewLyrics perform click()
        CreateNewLyricsScrollView checkThat isDisplayed()
    }

    @Test
    fun test_isSaveButtonCanBeExecutedWhenOnlyTheNewPerfomerEditTextsEmpty()
    {
        signInButton perform click()
        CreateNewLyrics perform click()
        newtitle replaceTextWith "something"
        newyoutubelink replaceTextWith "something"
        newyoutubeMusiclink replaceTextWith "something"
        newspotifylink replaceTextWith "something"
        newlyrics_text replaceTextWith "something"
        btn_create perform click()
        CreateNewLyricsScrollView checkToastMessageText toastMessageText
    }

    @Test
    fun test_isSaveButtonCanBeExecutedWhenOnlyTheNewTitleEditTextsEmpty()
    {
        signInButton perform click()
        CreateNewLyrics perform click()
        newperformer replaceTextWith "something"
        newyoutubelink replaceTextWith "something"
        newyoutubeMusiclink replaceTextWith "something"
        newspotifylink replaceTextWith "something"
        newlyrics_text replaceTextWith "something"
        btn_create perform click()
        CreateNewLyricsScrollView checkToastMessageText toastMessageText
    }

    @Test
    fun test_isSaveButtonCanBeExecutedWhenOnlyTheNewYoutubeLinkEditTextsEmpty()
    {
        signInButton perform click()
        CreateNewLyrics perform click()
        newperformer replaceTextWith "something"
        newtitle replaceTextWith "something"
        newyoutubeMusiclink replaceTextWith "something"
        newspotifylink replaceTextWith "something"
        newlyrics_text replaceTextWith "something"
        btn_create perform click()
        CreateNewLyricsScrollView checkToastMessageText toastMessageText
    }

    @Test
    fun test_isSaveButtonCanBeExecutedWhenOnlyTheNewYoutubeMusicLinkEditTextsEmpty()
    {
        signInButton perform click()
        CreateNewLyrics perform click()
        newperformer replaceTextWith "something"
        newtitle replaceTextWith "something"
        newyoutubelink replaceTextWith "something"
        newspotifylink replaceTextWith "something"
        newlyrics_text replaceTextWith "something"
        btn_create perform click()
        CreateNewLyricsScrollView checkToastMessageText toastMessageText
    }

    @Test
    fun test_isSaveButtonCanBeExecutedWhenOnlyTheNewSpotifyLinkEditTextsEmpty()
    {
        signInButton perform click()
        CreateNewLyrics perform click()
        newperformer replaceTextWith "something"
        newtitle replaceTextWith "something"
        newyoutubelink replaceTextWith "something"
        newyoutubeMusiclink replaceTextWith "something"
        newlyrics_text replaceTextWith "something"
        btn_create perform click()
        CreateNewLyricsScrollView checkToastMessageText toastMessageText
    }

    @Test
    fun test_isSaveButtonCanBeExecutedWhenOnlyTheNewLyricsTextEditTextsEmpty()
    {
        signInButton perform click()
        CreateNewLyrics perform click()
        newperformer replaceTextWith "something"
        newtitle replaceTextWith "something"
        newyoutubelink replaceTextWith "something"
        newyoutubeMusiclink replaceTextWith "something"
        newspotifylink replaceTextWith "something"
        btn_create perform click()
        CreateNewLyricsScrollView checkToastMessageText toastMessageText
    }
}