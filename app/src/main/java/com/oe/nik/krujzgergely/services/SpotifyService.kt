package com.oe.nik.krujzgergely.services

import android.content.Context
import android.util.Log
import com.oe.nik.krujzgergely.models.enums.PlayingState
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

object SpotifyService
{
    val CLIENT_ID = "df6077aad3b34873aec586a46c7861e1"
    val AUTH_TOKEN_REQUEST_CODE = 0x10
    val REDIRECT_URI = "com.oe.nik.krujzgergely://callback"

    private var spotifyAppRemote: SpotifyAppRemote? = null
    private var connectionParams: ConnectionParams = ConnectionParams.Builder(CLIENT_ID)
        .setRedirectUri(REDIRECT_URI)
        .showAuthView(true)
        .build()

    fun connect(context: Context) {
        if (spotifyAppRemote?.isConnected == true) { return }
        val connectionListener = object : Connector.ConnectionListener {
            override fun onConnected(spotifyAppRemote: SpotifyAppRemote) {
                SpotifyService.spotifyAppRemote = spotifyAppRemote
            }
            override fun onFailure(throwable: Throwable) {
                Log.e("SpotifyService", throwable.message, throwable)
            }
        }
        SpotifyAppRemote.connect(context, connectionParams, connectionListener)
    }

    fun getAuthenticationRequest(type: AuthenticationResponse.Type): AuthenticationRequest {
        return AuthenticationRequest.Builder(CLIENT_ID, type, REDIRECT_URI)
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-email"))
            .build()
    }

    fun play(uri: String) { spotifyAppRemote?.playerApi?.play(uri) }

    fun resume() { spotifyAppRemote?.playerApi?.resume() }

    fun pause() { spotifyAppRemote?.playerApi?.pause() }

    fun playingState(handler: (PlayingState) -> Unit)
    {
        spotifyAppRemote?.playerApi?.playerState?.setResultCallback { result ->
            if (result.track.uri == null) {
                handler(PlayingState.STOPPED)
            } else if (result.isPaused) {
                handler(PlayingState.PAUSED)
            } else {
                handler(PlayingState.PLAYING)
            }
        }
    }

    fun disconnect() { SpotifyAppRemote.disconnect(spotifyAppRemote) }
}