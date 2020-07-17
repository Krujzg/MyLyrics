package com.oe.nik.krujzgergely.models

import org.json.JSONArray

data class SpotifyAccount(val Id : String,
                          val DisplayName : String,
                          val Email : String,
                          val AvatarURL : String,
                          val AvatarArray : JSONArray)