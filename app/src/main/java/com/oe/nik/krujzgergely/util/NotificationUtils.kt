package com.oe.nik.krujzgergely.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.oe.nik.krujzgergely.R
import com.oe.nik.krujzgergely.models.enums.CrudType
import com.oe.nik.krujzgergely.ui.lyrics.LyricsActivity

private const val NOTIFICATION_ID = 0


fun NotificationManager.sendNotification(title: String, messageBody: String, crudType: CrudType, applicationContext: Context) {
    val contentIntent = selectNavigationDirection(crudType,applicationContext)
    val contentPendingIntent = PendingIntent.getActivity(applicationContext, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    val myLyricsLogo = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.myliricslogo)

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(myLyricsLogo)
        .bigLargeIcon(null)

    val builder = NotificationCompat.Builder(applicationContext,applicationContext.getString(R.string.mylyrics_notification_channel_id))
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle(title)
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setStyle(bigPicStyle)
        .setLargeIcon(myLyricsLogo)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, builder.build())
}

//In case of refactor
private fun selectNavigationDirection(crudType: CrudType, applicationContext: Context) : Intent
{
    return when(crudType)
    {
        CrudType.SELECT -> Intent(applicationContext, LyricsActivity::class.java)
        CrudType.UPDATE -> Intent(applicationContext, LyricsActivity::class.java)
        CrudType.DELETE -> Intent(applicationContext, LyricsActivity::class.java)
        CrudType.INSERT -> Intent(applicationContext, LyricsActivity::class.java)
    }
}

fun NotificationManager.cancelNotifications() { cancelAll() }