package it.polito.progettolocker

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        getFirebaseMessage(message.notification!!.title,message.notification!!.body )
    }

    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    /*  private fun createNotificationChannel() {
          val channelId = "notify"


  // Crea un oggetto NotificationChannel solo se stai eseguendo l'app su Android Oreo o versioni successive
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val name = "Nome del tuo canale"
      val descriptionText = "Descrizione del tuo canale"
      val importance = NotificationManager.IMPORTANCE_DEFAULT

      val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
          description = descriptionText
      }

      // Registra il canale con il NotificationManager
      val notificationManager: NotificationManager =
          getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      notificationManager.createNotificationChannel(channel)
  }
          }
      }*/
    companion object{
        val channelId = "GiovanniMalnati"
        fun createNotificationChannel(notificationManager: NotificationManager?) {

            val channelName = "channelNotifica"
            val channelDescription = "Canale di notifica per Jetpack Compose"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, channelName, importance).apply {
                    description = channelDescription
                    enableLights(true)
                    enableVibration(true)
                    vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                }

                notificationManager?.createNotificationChannel(channel)
            }
        }
        fun sendNotification(context: Context, notificationText: String) {
            val builder = NotificationCompat.Builder(context, channelId)
                .setContentTitle("Jetpack Compose Notification")
                .setContentText(notificationText)
                .setSmallIcon(R.drawable.logo_round)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager = NotificationManagerCompat.from(context)
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notificationManager.notify(101, builder.build())
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun getFirebaseMessage(title: String?, body: String?) {
       var builder= Notification.Builder(this, "notifica")
           .setSmallIcon(R.drawable.logo_round).setContentTitle(title).setContentText(body).setAutoCancel(false)
        var managerCompact= NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            managerCompact.notify(102, builder.build())

        }
        managerCompact.notify(102, builder.build())

    }


}