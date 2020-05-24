package com.example.servicetest

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {

    override fun onCreate() {
        super.onCreate()
        Log.d("MyIntentService_TAG", "onCreate: ")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("MyIntentService_TAG", "onBind: ")
        return super.onBind(intent)
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d("MyIntentService_TAG", "onHandleIntent: ${intent?.getStringExtra("data")}")
        Log.d("MyIntentService_TAG", "onHandleIntent: Thread: ${Thread.currentThread().name}")

        Log.d("MyIntentService_TAG", "onHandleIntent: Task starting")

        for (i in 0..4) {
            try {
                Thread.sleep(500)
                Log.d("MyIntentService_TAG", "onHandleIntent: $i")
            } catch (e: InterruptedException) {
                Log.d("MyIntentService_TAG", "onHandleIntent: $e")
            }
        }
    }

    override fun onDestroy() {
        Log.d("MyIntentService_TAG", "onDestroy: ")
        super.onDestroy()
    }
}