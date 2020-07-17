package com.oe.nik.krujzgergely.controllers

import android.app.Application
import android.net.Uri
import android.util.Log
import com.oe.nik.krujzgergely.models.SpotifyAccount
import com.oe.nik.krujzgergely.models.builder.SpotifyAccountBuilder
import com.oe.nik.krujzgergely.services.SpotifyService
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpCookie.parse
import java.net.URL
import java.util.logging.Level.parse
import javax.net.ssl.HttpsURLConnection

class SpotifyLogin(application: Application)
{

    lateinit var spotifyAccountBuilder: SpotifyAccountBuilder
    var id = ""
    var displayName = ""
    var email = ""
    lateinit var avatarArray : JSONArray
    var avatarURL : String = ""
    var accessToken = ""
    companion object
    {
        var spotifyAccount :SpotifyAccount? = null
    }

    fun fetchSpotifyUserProfile(token: String?) {
        Log.d("Status: ", "Please Wait...")
        if (token == null) {
            Log.i("Status: ", "Something went wrong - No Access Token found")
            return
        }

        val getUserProfileURL = "https://api.spotify.com/v1/me"

        GlobalScope.launch(Dispatchers.Default) {
            val url = URL(getUserProfileURL)
            val httpsURLConnection = withContext(Dispatchers.IO) {url.openConnection() as HttpsURLConnection }
            httpsURLConnection.apply {
                requestMethod = "GET"
                setRequestProperty("Authorization", "Bearer $token")
                doInput = true
                doOutput = false
            }

            val response = httpsURLConnection.inputStream.bufferedReader().use { it.readText() }
            withContext(Dispatchers.Main) {
                val jsonObject = JSONObject(response)

                id = jsonObject.getString("id")
                Log.d("Spotify Id :", id)

                displayName = jsonObject.getString("display_name")
                Log.d("Spotify Display Name :", displayName)

                email = jsonObject.getString("email")
                Log.d("Spotify Email :", email)

                avatarArray = jsonObject.getJSONArray("images")
                avatarURL = ""
                if (avatarArray.length() > 0) {
                    avatarURL = avatarArray.getJSONObject(0).getString("url")
                    Log.d("Spotify Avatar : ", avatarURL)
                }
                buildSpotifyAccount()

                Log.d("Spotify AccessToken :", token)
                accessToken = token
            }
        }
    }

    private fun buildSpotifyAccount()
    {
        spotifyAccountBuilder = SpotifyAccountBuilder()
        spotifyAccount = spotifyAccountBuilder
            .setId(id)
            .setName(displayName)
            .setEmail(email)
            .setAvatarArray(avatarArray)
            .setAvatarURL(avatarURL)
            .getSpotifyAccountBuilder()
    }
}