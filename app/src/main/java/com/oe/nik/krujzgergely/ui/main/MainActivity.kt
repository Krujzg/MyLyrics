package com.oe.nik.krujzgergely.ui.main

import android.app.ActivityOptions
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TaskInfo
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        doAnimatonAutomatically()

        createNotificationChannel(getString(R.string.mylyrics_notification_channel_id),getString(R.string.mylyrics_notification_channel_name))
    }

    private fun doAnimatonAutomatically()
    {
        findViewById<MotionLayout>(R.id.motion_base).transitionToEnd()
        delayStartActivityTillAnimationEnds()
    }

    private fun delayStartActivityTillAnimationEnds()
    {
        val runnableFunction = Runnable { startLyricsActivity() }
        val delayHandler = Handler()

        delayHandler.postDelayed(runnableFunction,3000)
    }

    private fun startLyricsActivity()
    {
        startActivity(Intent(this, LyricsActivity::class.java))
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
