package com.oe.nik.krujzgergely.ui.accountInfo

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oe.nik.krujzgergely.controllers.logincontroller.GoogleLogin
import com.oe.nik.krujzgergely.controllers.logincontroller.SpotifyLogin
import com.oe.nik.krujzgergely.data.LyricsDatabase
import com.oe.nik.krujzgergely.repository.LyricsCountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AccountInfoActivityViewModel(application: Application) : AndroidViewModel(application)
{
    private var countLyricsRepository : LyricsCountRepository

    private var allLyricsCount      : Int = 0
    private var favoriteLyricsCount : Int = 0
    private var jazzLyricsCount     : Int = 0
    private var hipHopLyricsCount   : Int = 0
    private var rockLyricsCount     : Int = 0
    private var metalLyricsCount    : Int = 0
    private var punkLyricsCount     : Int = 0
    private var popLyricsCount      : Int = 0
    private var countryLyricsCount  : Int = 0
    private var operaLyricsCount    : Int = 0

    private var _displayedAccountCompany : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedName  : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedEmail : MutableLiveData<String> = MutableLiveData<String>()

    private var _displayedAllLyricsCount      : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedFavoriteLyricsCount : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedJazzLyricsCount     : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedHipHopLyricsCount   : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedRockLyricsCount     : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedMetalLyricsCount    : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedPunkLyricsCount     : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedPopLyricsCount      : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedCountryLyricsCount  : MutableLiveData<String> = MutableLiveData<String>()
    private var _displayedOperaLyricsCount    : MutableLiveData<String> = MutableLiveData<String>()

    val displayedAccountCompany: LiveData<String>
        get() = _displayedAccountCompany

    val displayedName: LiveData<String>
        get() = _displayedName

    val displayedEmail: LiveData<String>
        get() = _displayedEmail

    val displayedAllLyricsCount: LiveData<String>
        get() = _displayedAllLyricsCount

    val displayedFavoriteLyricsCount: LiveData<String>
        get() = _displayedFavoriteLyricsCount

    val displayedJazzLyricsCount: LiveData<String>
        get() = _displayedJazzLyricsCount

    val displayedHipHopLyricsCount: LiveData<String>
        get() = _displayedHipHopLyricsCount

    val displayedRockLyricsCount: LiveData<String>
        get() = _displayedRockLyricsCount

    val displayedMetalLyricsCount: LiveData<String>
        get() = _displayedMetalLyricsCount

    val displayedPunkLyricsCount: LiveData<String>
        get() = _displayedPunkLyricsCount

    val displayedPopLyricsCount: LiveData<String>
        get() = _displayedPopLyricsCount

    val displayedCountryLyricsCount: LiveData<String>
        get() = _displayedCountryLyricsCount

    val displayedOperaLyricsCount: LiveData<String>
        get() = _displayedOperaLyricsCount

    init
    {
        val lyricsCountDao= LyricsDatabase
            .getDatabase(application,viewModelScope,application.resources)
            .lyricsCountDao()
        this.countLyricsRepository = LyricsCountRepository(lyricsCountDao)

        viewModelScope.launch { getDataCountsFromTheLocalDB() }

        delayAccountInfoDisplayBy400MillisecondsToLoadPerfectly()
    }

    private fun onDisplayAccountContent()
    {
        val googleAccount = GoogleLogin.googleAccount
        val spotifyAccount = SpotifyLogin.spotifyAccount
        when(googleAccount)
        {
            null -> {
                _displayedAccountCompany.value = "Logged in with Spotify"
                _displayedName.value  = spotifyAccount!!.name
                _displayedEmail.value = spotifyAccount.email
            }
            else -> {
                _displayedAccountCompany.value = "Logged in with Google"
                _displayedName.value  = googleAccount.givenName
                _displayedEmail.value = googleAccount.email
            }
        }
    }

    private fun onDisplayAccountInfoData()
    {
        onDisplayAccountContent()
        onDisplayAllLyricsCountContent()
        onDisplayFavoriteLyricsCountContent()
        onDisplayJazzLyricsCountContent()
        onDisplayHipHopLyricsCountContent()
        onDisplayRockLyricsCountContent()
        onDisplayMetalLyricsCountContent()
        onDisplayPunkLyricsCountContent()
        onDisplayPopLyricsCountContent()
        onDisplayCountryLyricsCountContent()
        onDisplayOperaLyricsCountContent()
    }

    private fun onDisplayAllLyricsCountContent()      {_displayedAllLyricsCount.value      = allLyricsCount.toString() }
    private fun onDisplayFavoriteLyricsCountContent() {_displayedFavoriteLyricsCount.value = favoriteLyricsCount.toString() }
    private fun onDisplayJazzLyricsCountContent()     {_displayedJazzLyricsCount.value     = jazzLyricsCount.toString() }
    private fun onDisplayHipHopLyricsCountContent()   {_displayedHipHopLyricsCount.value   = hipHopLyricsCount.toString() }
    private fun onDisplayRockLyricsCountContent()     {_displayedRockLyricsCount.value     = rockLyricsCount.toString() }
    private fun onDisplayMetalLyricsCountContent()    {_displayedMetalLyricsCount.value    = metalLyricsCount.toString() }
    private fun onDisplayPunkLyricsCountContent()     {_displayedPunkLyricsCount.value     = punkLyricsCount.toString()}
    private fun onDisplayPopLyricsCountContent()      {_displayedPopLyricsCount.value      = popLyricsCount.toString() }
    private fun onDisplayCountryLyricsCountContent()  {_displayedCountryLyricsCount.value  = countryLyricsCount.toString() }
    private fun onDisplayOperaLyricsCountContent()    {_displayedOperaLyricsCount.value    = operaLyricsCount.toString() }

    private suspend fun getAllLyricsCountContent()      : Int = countLyricsRepository.getAllLyricsCountFromLocalDB()
    private suspend fun getFavoriteLyricsCountContent() : Int = countLyricsRepository.getFavouriteLyricsCountFromLocalDB()
    private suspend fun getJazzLyricsCountContent()     : Int = countLyricsRepository.getJazzLyricsCountFromLocalDB()
    private suspend fun getHipHopLyricsCountContent()   : Int = countLyricsRepository.getHipHopLyricsCountFromLocalDB()
    private suspend fun getRockLyricsCountContent()     : Int = countLyricsRepository.getRockLyricsCountFromLocalDB()
    private suspend fun getMetalLyricsCountContent()    : Int = countLyricsRepository.getMetalLyricsCountFromLocalDB()
    private suspend fun getPunkLyricsCountContent()     : Int = countLyricsRepository.getPunkLyricsCountFromLocalDB()
    private suspend fun getPopLyricsCountContent()      : Int = countLyricsRepository.getPopLyricsCountFromLocalDB()
    private suspend fun getCountryLyricsCountContent()  : Int = countLyricsRepository.getCountryLyricsCountFromLocalDB()
    private suspend fun getOperaLyricsCountContent()    : Int = countLyricsRepository.getOperaLyricsCountFromLocalDB()

    private suspend fun getDataCountsFromTheLocalDB()
    {
        withContext(Dispatchers.IO) { allLyricsCount      = getAllLyricsCountContent()}
        withContext(Dispatchers.IO) { favoriteLyricsCount = getFavoriteLyricsCountContent()}
        withContext(Dispatchers.IO) { jazzLyricsCount     = getJazzLyricsCountContent()}
        withContext(Dispatchers.IO) { hipHopLyricsCount   = getHipHopLyricsCountContent()}
        withContext(Dispatchers.IO) { rockLyricsCount     = getRockLyricsCountContent()}
        withContext(Dispatchers.IO) { metalLyricsCount    = getMetalLyricsCountContent()}
        withContext(Dispatchers.IO) { punkLyricsCount     = getPunkLyricsCountContent()}
        withContext(Dispatchers.IO) { popLyricsCount      = getPopLyricsCountContent()}
        withContext(Dispatchers.IO) { countryLyricsCount  = getCountryLyricsCountContent()}
        withContext(Dispatchers.IO) { operaLyricsCount    = getOperaLyricsCountContent()}
    }

    private fun delayAccountInfoDisplayBy400MillisecondsToLoadPerfectly()
    {
        Handler().postDelayed({onDisplayAccountInfoData()}, 400)
    }
}