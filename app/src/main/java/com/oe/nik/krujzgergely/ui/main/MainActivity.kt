package com.oe.nik.krujzgergely.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.controllers.logincontroller.GoogleLogin
import com.oe.nik.krujzgergely.controllers.logincontroller.SpotifyLogin
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity
import com.oe.nik.krujzgergely.services.SpotifyService
import com.oe.nik.krujzgergely.services.SpotifyService.getAuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse

class MainActivity : AppCompatActivity() {

    lateinit var signInButtonGoogle : Button
    lateinit var signInButtonSpotify : Button
    lateinit var googleLogin: GoogleLogin
    lateinit var spotifyLogin: SpotifyLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        createNotificationChannel(getString(R.string.mylyrics_notification_channel_id),getString(R.string.mylyrics_notification_channel_name))
        signInButtonGoogle  = findViewById(R.id.signInButton)
        signInButtonSpotify = findViewById(R.id.spotify_login_btn)
        signInButtonGoogle.setOnClickListener{ signInWithGoogle() }

        googleLogin =
            GoogleLogin(
                application
            )
        spotifyLogin =
            SpotifyLogin(
                application
            )

        signInWithSpotifyButtonClickListener()
    }

    private fun signInWithSpotifyButtonClickListener()
    {

        signInButtonSpotify.setOnClickListener {
            startSpotifyProgressBar()
            SpotifyService.connect(this)
            val request = getAuthenticationRequest(AuthenticationResponse.Type.TOKEN)
            AuthenticationClient.openLoginActivity(this, SpotifyService.AUTH_TOKEN_REQUEST_CODE, request)
        }
    }

    private fun startGoogleProgressBar()
    {
        val progressBarGoogle = findViewById<ProgressBar>(R.id.progressBarGoogle).apply { visibility = View.VISIBLE }

        setColorFilter(progressBarGoogle.indeterminateDrawable, ResourcesCompat.getColor(resources, R.color.youtubeRed, null))
    }

    private fun startSpotifyProgressBar()
    {
        val progressBarSpotify = findViewById<ProgressBar>(R.id.progressBarSpotify).apply { visibility = View.VISIBLE }

        setColorFilter(progressBarSpotify.indeterminateDrawable, ResourcesCompat.getColor(resources, R.color.spotifygreen, null))
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    private fun signInWithGoogle()
    {
        startGoogleProgressBar()
        val signInIntent = googleLogin.mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, googleLogin.GOOGLE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val response = AuthenticationClient.getResponse(resultCode, data)
        val accessToken: String? = response.accessToken

        when(requestCode)
        {
            googleLogin.GOOGLE_REQUEST_CODE -> googleLogin.startGoogleLogin(data)
            SpotifyService.AUTH_TOKEN_REQUEST_CODE -> spotifyLogin.fetchSpotifyUserProfile(accessToken)

        }
        //This needs to be added because spotifybuilder does not have time to build
        val handler = Handler()
        handler.postDelayed({startLyricsActivity()}, 1500)
    }

    private fun startLyricsActivity() { startActivity(Intent(this, LyricsActivity::class.java)) }

    private fun createNotificationChannel(channelId: String, channelName: String) {

        if (checkIfCurrentVersionOfAPIIsGreaterThenOrEqualsWithAPI26())
        {
            val notificationChannel = NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.apply {
                setShowBadge(true)
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = getString(R.string.mylyrics_notification_channel_description) }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun checkIfCurrentVersionOfAPIIsGreaterThenOrEqualsWithAPI26() : Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}
