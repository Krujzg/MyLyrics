package com.oe.nik.krujzgergely.util

import com.oe.nik.krujzgergely.models.SpotifyAccount
import org.json.JSONArray
import org.json.JSONObject

class SpotifyAccountBuilder
{
    private lateinit var spotifyId : String
    private lateinit var spotifyDisplayName : String
    private lateinit var spotifyEmail : String
    private lateinit var spotifyAvatarURL : String
    private lateinit var spotifyAvatarArray : JSONArray

    fun setSpotifyId(spotifyId : String) : SpotifyAccountBuilder
    {
        this.spotifyId = spotifyId
        return this
    }

    fun setSpotifyDisplayName(spotifyEmail : String) : SpotifyAccountBuilder
    {
        this.spotifyDisplayName = spotifyId
        return this
    }

    fun setSpotifyEmail(spotifyEmail : String) : SpotifyAccountBuilder
    {
        this.spotifyEmail = spotifyId
        return this
    }

    fun setSpotifyAvatarUrl(spotifyAvatarURL : String) : SpotifyAccountBuilder
    {
        this.spotifyAvatarURL = spotifyId
        return this
    }

    fun setSpotifyAvataraRRAY(spotifyAvatarArray : JSONArray) : SpotifyAccountBuilder
    {
        this.spotifyAvatarArray = spotifyAvatarArray
        return this
    }

    fun getSpotifyAccountBuilder() : SpotifyAccount = SpotifyAccount(spotifyId,spotifyDisplayName,spotifyEmail,spotifyAvatarURL,spotifyAvatarArray)


}