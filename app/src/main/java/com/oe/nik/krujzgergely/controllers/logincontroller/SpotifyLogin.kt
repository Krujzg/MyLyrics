package com.oe.nik.krujzgergely.controllers.logincontroller

import com.oe.nik.krujzgergely.models.SpotifyAccount
import com.oe.nik.krujzgergely.models.builder.SpotifyAccountBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class SpotifyLogin
{
    private lateinit var spotifyAccountBuilder: SpotifyAccountBuilder
    private lateinit var avatarArray : JSONArray

    private var id = ""
    private var displayName = ""
    private var email = ""
    private var avatarURL : String = ""
    private var accessToken = ""
    private val userProfileURL = "https://api.spotify.com/v1/me"

    companion object { var spotifyAccount :SpotifyAccount? = null }

    fun fetchSpotifyUserProfile(token: String?)
    {
        if (token == null) { return }

        GlobalScope.launch(Dispatchers.Default)
        {

            val httpsURLConnection = setHTTPSUrlConnection(token)
            val response = setResponeFromHTTPSUrlConnection(httpsURLConnection)

            startSpotifyDataBuildingFromResponse(response,token)
        }
    }

    private suspend fun setHTTPSUrlConnection(token: String?) : HttpsURLConnection
    {
        val url = URL(userProfileURL)
        val httpsURLConnection = withContext(Dispatchers.IO) {url.openConnection() as HttpsURLConnection }
        httpsURLConnection.apply {
            requestMethod = "GET"
            setRequestProperty("Authorization", "Bearer $token")
            doInput = true
            doOutput = false
        }

        return httpsURLConnection
    }

    private fun setResponeFromHTTPSUrlConnection(httpsURLConnection: HttpsURLConnection) : String
    {
        return httpsURLConnection.inputStream.bufferedReader().use { it.readText() }
    }

    private suspend fun startSpotifyDataBuildingFromResponse(response : String, token : String?)
    {
        withContext(Dispatchers.Main)
        {
            val jsonObject = JSONObject(response)

            setDataFromJsonObject(jsonObject)

            buildSpotifyAccount()

            accessToken = token!!
        }
    }

    private fun setDataFromJsonObject(jsonObject : JSONObject)
    {
        id = jsonObject.getString("id")
        displayName = jsonObject.getString("display_name")
        email = jsonObject.getString("email")
        avatarArray = jsonObject.getJSONArray("images")

        if (checkIfAvatarArrayLengthIsBiggerThenZero())
        {
            avatarURL = avatarArray.getJSONObject(0).getString("url")
        }
    }

    private fun checkIfAvatarArrayLengthIsBiggerThenZero() : Boolean = avatarArray.length() > 0

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