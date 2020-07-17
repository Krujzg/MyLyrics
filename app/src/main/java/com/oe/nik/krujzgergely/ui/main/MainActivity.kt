package com.oe.nik.krujzgergely.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.controllers.GoogleLogin
import com.oe.nik.krujzgergely.controllers.SpotifyLogin
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity
import com.oe.nik.krujzgergely.util.SpotifyConstants

class MainActivity : AppCompatActivity() {

    lateinit var signInButtonGoogle : Button
    lateinit var signInButtonSpotify : Button
    lateinit var spotifyLogin: SpotifyLogin
    lateinit var googleLogin: GoogleLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        signInButtonGoogle  = findViewById(R.id.signInButton)
        signInButtonSpotify = findViewById<Button>(R.id.spotify_login_btn)
        signInButtonGoogle.setOnClickListener{ signInWithGoogle() }

        signInButtonSpotify.setOnClickListener { spotifyLogin.startSpotifyLoginActivity(this) }

        spotifyLogin =  SpotifyLogin(application)
        googleLogin = GoogleLogin(application)

        createNotificationChannel(getString(R.string.mylyrics_notification_channel_id),getString(R.string.mylyrics_notification_channel_name))
    }

    private fun signInWithGoogle()
    {
        val signInIntent = googleLogin.mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, googleLogin.GOOGLE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode)
        {
            googleLogin.GOOGLE_REQUEST_CODE ->   googleLogin.startGoogleLogin(data)
            SpotifyConstants.AUTH_TOKEN_REQUEST_CODE -> spotifyLogin.bootSpotifyLogin(resultCode,data)
        }
        startLyricsActivity()
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
