package com.oe.nik.krujzgergely.models

import org.json.JSONArray

data class SpotifyAccount (val id : String,
                           val name : String,
                           val email : String,
                           val AvatarArray : JSONArray,
                           val AvatarURL : String)


