package com.oe.nik.krujzgergely.ui.accountInfo

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
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
class AccountInfoActivityTest
{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun test_isYourLyricsCountTextViewTextEqualsWithYour_Lyrics_Count()
    {
        signInButton perform click()
        ProfilePicture perform click()
        yourLyricsCount checkThatTextIs "Your lyrics count:"
    }

    @Test
    fun test_isAllTextViewTextEqualsWithAll()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoAllTextView checkThatTextIs "All"
    }

    @Test
    fun test_isFavoriteTextViewTextEqualsWithAll()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoFavoriteTextView checkThatTextIs "Favorite"
    }

    @Test
    fun test_isJazzTextViewTextEqualsWithJazz()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoJazzTextView checkThatTextIs "Jazz"
    }

    @Test
    fun test_isHipHopTextViewTextEqualsWithHip_Hop()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoHipHopTextView checkThatTextIs "Hip Hop"
    }

    @Test
    fun test_isRockTextViewTextEqualsWithRock()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoRockTextView checkThatTextIs "Rock"
    }

    @Test
    fun test_isMetalTextViewTextEqualsWithMetal()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoMetalTextView checkThatTextIs "Metal"
    }

    @Test
    fun test_isPunkTextViewTextEqualsWithPunk()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoPunkTextView checkThatTextIs "Punk"
    }

    @Test
    fun test_isPopTextViewTextEqualsWithPop()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoPopTextView checkThatTextIs "Pop"
    }

    @Test
    fun test_isCountryTextViewTextEqualsWithCountry()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoCountryTextView checkThatTextIs "Country"
    }

    @Test
    fun test_isOperaTextViewTextEqualsWithOpera()
    {
        signInButton perform click()
        ProfilePicture perform click()
        accountinfoOperaTextView checkThatTextIs "Opera"
    }

    @Test
    fun test_isLogoutButtonTextEqualsWithLogout()
    {
        signInButton perform click()
        ProfilePicture perform click()
        LogoutButton checkThatTextIs "Logout"
    }

    @Test
    fun test_isProfilePictureVisible()
    {
        signInButton perform click()
        ProfilePicture perform click()
        BigProfilePic checkThat  isDisplayed()
    }
}