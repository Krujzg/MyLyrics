package com.oe.nik.krujzgergely.models.builder

import com.oe.nik.krujzgergely.models.SpotifyAccount
import org.json.JSONArray
import org.json.JSONObject

class SpotifyAccountBuilder
{
    lateinit var id : String
    lateinit var name : String
    lateinit var email : String
    lateinit var AvatarArray : JSONArray
    lateinit var AvatarURL : String


    fun setId(id: String) : SpotifyAccountBuilder
    {
        this.id = id
        return this
    }

    fun setEmail(email: String) : SpotifyAccountBuilder
    {
        this.email = email
        return this
    }

    fun setAvatarArray(AvatarArray: JSONArray) : SpotifyAccountBuilder
    {
        this.AvatarArray = AvatarArray
        return this
    }
    fun setAvatarURL(AvatarURL: String) : SpotifyAccountBuilder
    {
        this.AvatarURL = AvatarURL
        return this
    }
    fun setName(name: String) : SpotifyAccountBuilder
    {
        this.name = name
        return this
    }

    fun getSpotifyAccountBuilder() : SpotifyAccount = SpotifyAccount(id,name,email,AvatarArray,AvatarURL)
}