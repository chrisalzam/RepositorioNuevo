package com.example.servicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService_TAG", "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService_TAG", "onStartCommand: ${intent?.getStringExtra("data")}")
        Log.d("MyService_TAG", "onStartCommand: Thread: ${Thread.currentThread().name}")
        Log.d("MyService_TAG", "onStartCommand: Performing a Task")

        Thread.sleep(3000)
        Log.d("MyService_TAG", "onStartCommand: onTaskDone")

        //Add stopSelf() after task is complete
        stopSelf()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d("MyService_TAG", "onBind: ")
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onDestroy() {
        Log.d("MyService_TAG", "onDestroy: ")
        super.onDestroy()
    }
}
