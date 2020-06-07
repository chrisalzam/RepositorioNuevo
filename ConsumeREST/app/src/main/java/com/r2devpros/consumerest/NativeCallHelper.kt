package com.r2devpros.consumerest

import android.util.Log
import java.net.MalformedURLException
import java.net.URL
import java.util.*

object NativeCallHelper {
    fun makeCall(baseUrl: String) {
        Log.d("NativeCallHelper_TAG", "makeCall: $baseUrl")
        Thread(Runnable {
            try {
                val url = URL(baseUrl)
                val urlConnection = url.openConnection()
                val stream = urlConnection.getInputStream()
                val scanner = Scanner(stream)

                while (scanner.hasNext()) {
                    Log.d("NativeCallHelper_TAG", "makeCall: scanner: ${scanner.nextLine()}")
                }
            } catch (malformed: MalformedURLException) {
                Log.d("NativeCallHelper_TAG", "makeCall: malformed: $malformed")
            } catch (e: Exception) {
                Log.d("NativeCallHelper_TAG", "makeCall: exception: $e")
            }
        }).start()
    }
}