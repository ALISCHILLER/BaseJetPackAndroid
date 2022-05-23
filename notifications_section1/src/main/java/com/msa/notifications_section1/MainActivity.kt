package com.msa.notifications_section1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.msa.notifications_section1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId="com..msa.notificationssection1"
    private var notificationManager:NotificationManager?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        creatNotificationChannel(channelId,"Test","this is a test")

        binding.addAction.setOnClickListener {
            displayNotification()
        }
    }
    private fun displayNotification(){
        val  notifactionId=45
        val notifiction= NotificationCompat.Builder(this@MainActivity,channelId)
            .setContentTitle("Test")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notifactionId,notifiction)
    }

    private fun creatNotificationChannel(id:String,name:String,channelDescription:String){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val importance=NotificationManager.IMPORTANCE_HIGH
            val channel=NotificationChannel(id,name,importance).apply {
                description=channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}