package com.example.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyBoundService : Service() {
    private val binder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d("MyBoundService_TAG", "onCreate: ")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d("MyBoundService_TAG", "onBind: ")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("MyBoundService_TAG", "onUnbind: ")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d("MyBoundService_TAG", "onRebind: ")
    }

    override fun onDestroy() {
        Log.d("MyBoundService_TAG", "onDestroy: ")
        super.onDestroy()
    }

    fun getDataFromServer() = "This is the data from the server"

    class MyBinder : Binder() {
        val service: MyBoundService
            get() = MyBoundService()
    }
}
