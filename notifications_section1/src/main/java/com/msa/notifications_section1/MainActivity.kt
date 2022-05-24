package com.msa.notifications_section1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.RemoteInput
import androidx.core.app.NotificationCompat
import com.msa.notifications_section1.databinding.ActivityMainBinding
/**
 * Created by Ali Soleimani on 08,May,2022
 * Github https://github.com/ALISCHILLER
 * in Iran.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId="com..msa.notificationssection1"
    private var notificationManager:NotificationManager?=null
    private val KEY_REPLY = "key_reply"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        createNotificationChannel(channelId,"Test","this is a test")

        binding.addAction.setOnClickListener {
            displayNotification()
        }
    }


        private fun displayNotification() {
            val notificationId = 45
            val tapResultIntent = Intent(this, SecondActivity::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                this,
                0,
                tapResultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            //reply action
            val remoteInput:RemoteInput = RemoteInput.Builder(KEY_REPLY).run{
                setLabel("Insert you name here")
                build()
            }

            val replyAction : NotificationCompat.Action = NotificationCompat.Action.Builder(
                0,
                "REPLY",
                pendingIntent
            ).addRemoteInput(remoteInput)
                .build()

            //action button 1
            val intent2 = Intent(this, DetailsActivity::class.java)
            val pendingIntent2: PendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent2,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val action2 : NotificationCompat.Action =
                NotificationCompat.Action.Builder(0,"Details",pendingIntent2).build()
            // action button 2
            val intent3 = Intent(this, SettingsActivity::class.java)
            val pendingIntent3: PendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent3,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val action3 : NotificationCompat.Action =
                NotificationCompat.Action.Builder(0,"Settings",pendingIntent3).build()



            val notification = NotificationCompat.Builder(this@MainActivity, channelId)
                .setContentTitle("Demo Title")
                .setContentText("This is a demo notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(action2)
                .addAction(action3)
                .addAction(replyAction)
                .build()
            notificationManager?.notify(notificationId, notification)

        }

        private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(id, name, importance).apply {
                    description = channelDescription
                }
                notificationManager?.createNotificationChannel(channel)

            }

        }

}