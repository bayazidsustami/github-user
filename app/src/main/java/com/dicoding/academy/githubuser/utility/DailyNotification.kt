package com.dicoding.academy.githubuser.utility

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.ui.main.MainActivity

class DailyNotification: BroadcastReceiver() {
    companion object{
        const val NOTIFICATION_ID = 101
        const val NOTIFICATION_REQUEST_CODE = 102
        private const val CHANNEL_ID = "101"
        private const val CHANNEL_NAME = "daily_channel"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        showAlarmNotification(context)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun showAlarmNotification(context: Context?){
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_REQUEST_CODE, intent, 0)
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val vibrationPatterns = longArrayOf(1000, 1000, 1000)

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_alarm)
            .setContentTitle("Daily Reminder")
            .setContentText("Open your app and explore")
            .setContentIntent(pendingIntent)
            .setVibrate(vibrationPatterns)
            .setSound(alarmSound)
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableVibration(true)
                vibrationPattern = vibrationPatterns
            }
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

}