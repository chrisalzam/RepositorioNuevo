package com.example.servicetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onServicesButtonClick(view: View) {
        Log.d("MainActivity_TAG", "onStartNormalService: ")

        //region Intent for Service
        val serviceIntent = Intent(applicationContext, MyService::class.java)
        serviceIntent.putExtra("data", "Data from main activity")
        //endregion

        //region intent service
        val intentForIntentService = Intent(applicationContext, MyIntentService::class.java)
        intentForIntentService.putExtra("data", "Here is a message from main activity")
        //endregion

        when (view.id) {
            R.id.btnStartNormalService -> startService(serviceIntent)
            R.id.btnStopNormalService -> stopService(serviceIntent)
            R.id.btnStartIntentService -> startService(intentForIntentService)
        }
    }
}
