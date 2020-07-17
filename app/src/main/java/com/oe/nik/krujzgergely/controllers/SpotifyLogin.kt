package com.oe.nik.krujzgergely.controllers

import android.app.Application
import android.content.Intent
import android.widget.Button
import com.oe.nik.krujzgergely.models.SpotifyAccount
import com.oe.nik.krujzgergely.models.enums.LoginType
import com.oe.nik.krujzgergely.ui.main.MainActivity
import com.oe.nik.krujzgergely.util.SpotifyAccountBuilder
import com.oe.nik.krujzgergely.util.SpotifyConstants
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class SpotifyLogin(var application: Application)
{
    private val getUserProfileURLforSpotifyNativeAuthUtil = "https://api.spotify.com/v1/me"
    lateinit var jsonObjectSpotify : JSONObject
    lateinit var spotifyId : String
    lateinit var spotifyDisplayName : String
    lateinit var spotifyEmail : String
    lateinit var spotifyAvatarArray : JSONArray
    lateinit var spotifyAvatarURL : String
    lateinit var spotifyAccountBuilder: SpotifyAccountBuilder

    companion object
    {
        lateinit var spotifyAccount : SpotifyAccount
    }

    fun startSpotifyLoginActivity(activity: MainActivity)
    {
        val request = getAuthenticationRequest(AuthenticationResponse.Type.TOKEN)
        AuthenticationClient.openLoginActivity(activity, SpotifyConstants.AUTH_TOKEN_REQUEST_CODE, request)
    }

    fun bootSpotifyLogin(resultCode: Int, data: Intent?)
    {
        val response = AuthenticationClient.getResponse(resultCode, data)
        val accessToken: String? = response.accessToken
        fetchUserProfile(accessToken)
    }

    private fun getAuthenticationRequest(type: AuthenticationResponse.Type): AuthenticationRequest {
        return AuthenticationRequest.Builder(SpotifyConstants.CLIENT_ID, type, SpotifyConstants.REDIRECT_URI)
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-email"))
            .build()
    }

    private fun fetchUserProfile(token: String?)
    {
        if (checkIfTokenIsNullOrEmpty(token)) { return }

        GlobalScope.launch(Dispatchers.Default)
        {
            val url = URL(getUserProfileURLforSpotifyNativeAuthUtil)
            val httpsURLConnection = withContext(Dispatchers.IO) { url.openConnection() as HttpsURLConnection }

            httpsURLConnection.apply {
                requestMethod = "GET"
                setRequestProperty("Authorization", "Bearer $token")
                doInput = true
                doOutput = false
            }
            val inputSteam = httpsURLConnection.inputStream

            val response = inputSteam.bufferedReader().use { it.readText() }

            withContext(Dispatchers.Main) { getSpotifyAccountData(response) }
        }
    }

    private fun getSpotifyAccountData(response : String)
    {
        jsonObjectSpotify = JSONObject(response)
        spotifyId = jsonObjectSpotify.getString("id")
        spotifyDisplayName = jsonObjectSpotify.getString("display_name")
        spotifyEmail = jsonObjectSpotify.getString("email")
        spotifyAvatarArray = jsonObjectSpotify.getJSONArray("images")

        if (isSpotifyAvatarArrayLengthIsGreaterThenZero())
        {
            spotifyAvatarURL = spotifyAvatarArray.getJSONObject(0).getString("url")
        }

        buildSpotifyAccount()
    }

    private fun buildSpotifyAccount()
    {
        spotifyAccountBuilder = SpotifyAccountBuilder()
        spotifyAccount = spotifyAccountBuilder.apply {
            setSpotifyId(spotifyId)
            setSpotifyDisplayName(spotifyDisplayName)
            setSpotifyEmail(spotifyEmail)
            setSpotifyAvatarUrl(spotifyAvatarURL)
            setSpotifyAvataraRRAY(spotifyAvatarArray)}
            .getSpotifyAccountBuilder()
    }

    private fun isSpotifyAvatarArrayLengthIsGreaterThenZero() : Boolean = spotifyAvatarArray.length() > 0

    private fun checkIfTokenIsNullOrEmpty(token: String?) : Boolean = token.isNullOrEmpty()
}