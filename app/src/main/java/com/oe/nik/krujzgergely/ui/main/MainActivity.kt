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
import com.oe.nik.krujzgergely.ui.lyrics.LyricsesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_Start)

        button.setOnClickListener{startActivity( Intent(this, LyricsesActivity::class.java)) }
        createNotificationChannel(getString(R.string.mylyrics_notification_channel_id),getString(R.string.mylyrics_notification_channel_name))
    }


    private fun createNotificationChannel(channelId: String, channelName: String)
    {
        // TODO: Step 1.6 START create a channel
        // A notificationök API 26 tól működnek, így lekell ellenőrizni, hogy adott készülék verziója eléri-e ezt!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)//SDK_INT -> adott készülék API LEVEL // O = API 26
        {
            // TODO: Step 2.4 change importance
            // Bekell állítani a channel nek az ID-ját , Nevét és hogy milyen fontossági szinten legyen.
            // A Channelname lesz az amit a felhasználó látni fog
            // A NotificationManager.Importance_High fogja beállítani a priority- jét a notification nek
            val notificationChannel = NotificationChannel(channelId,channelName,
                NotificationManager.IMPORTANCE_HIGH)
                // TODO: Step 2.6 disable badges for this channel
                .apply {setShowBadge(true)} // Ez arra kell, hogy az asztalon levő ikonnon ne látszódjon az értesítés pont, hogy ha értesítést kaptunk

            // Ez fogja a kis ledet villogtatni, hogy ha van notification ünk
            notificationChannel.enableLights(true)
            // Pirosan fog villogni a LED
            notificationChannel.lightColor = Color.RED
            // Rezegni fog értesítés esetén
            notificationChannel.enableVibration(true)
            // Ez lesz a leírása a notification-nek
            notificationChannel.description = getString(R.string.mylyrics_notification_channel_description)

            // Kell egy instance a notification managerről
            val notificationManager = getSystemService(NotificationManager::class.java)
            // A notification channel létrehozását a notification manager fogja megoldani
            notificationManager.createNotificationChannel(notificationChannel)
        }
        // TODO: Step 1.6 END create a channel
    }
}
