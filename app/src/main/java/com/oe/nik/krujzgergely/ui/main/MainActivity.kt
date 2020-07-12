package com.oe.nik.krujzgergely.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.btn_Start)
        listenerForStartButtonClickToStartLyricsActivity(startButton)

        startButton.setOnClickListener{startActivity( Intent(this, LyricsActivity::class.java)) }

        createNotificationChannel(getString(R.string.mylyrics_notification_channel_id),getString(R.string.mylyrics_notification_channel_name))
    }

    private fun listenerForStartButtonClickToStartLyricsActivity(startButton : Button)
    {
        startButton.setOnClickListener{startActivity( Intent(this, LyricsActivity::class.java)) }
    }

    private fun createNotificationChannel(channelId: String, channelName: String)
    {
        if (checkIfCurrentVersionOfAPIIsGreaterThenOrEqualsWithAPI26())
        {
            val notificationChannel = NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.apply {
                setShowBadge(true)
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                enableVibration(true)
                description = getString(R.string.mylyrics_notification_channel_description) }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun checkIfCurrentVersionOfAPIIsGreaterThenOrEqualsWithAPI26() : Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}
