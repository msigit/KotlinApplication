package com.mcc.kotlinapplication.notification

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Sahidul Islam on 10/2/2019.
 */
open class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.size > 0) {
            val data: Map<String, String> = remoteMessage.data

            val type = data.get("type").toString()
            val title = data.get("title").toString()
            val message = data.get("message").toString()
            val imageUrl = data.get("image").toString()

            createNotification(title, message, imageUrl, type)
        }
    }

    private fun createNotification(
        title: String,
        messageBody: String,
        imageUrl: String,
        type: String
    ) {

    }

    private fun fetchBitmap(src: String?): Bitmap? {
        try {
            if (src != null) {
                val url = URL(src)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connectTimeout = 1200000
                connection.readTimeout = 1200000
                connection.connect()
                val input = connection.inputStream
                return BitmapFactory.decodeStream(input)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return null
    }
}