package com.oe.nik.krujzgergely.models

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

data class SpotifyAccount (val id : String,
                           val name : String,
                           val email : String,
                           val AvatarArray : JSONArray,
                           val AvatarURL : String)


