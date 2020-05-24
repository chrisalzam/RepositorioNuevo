package com.example.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var isBound = false
    private var boundService: MyBoundService? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("MainActivity_TAG", "onServiceDisconnected: ")
            boundService = null
            isBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("MainActivity_TAG", "onServiceConnected: ")
            val myBinder = service as MyBoundService.MyBinder
            boundService = myBinder.service
            isBound = true
        }

    }

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

    fun onBoundService(view: View) {
        Log.d("MainActivity_TAG", "onBoundService: isBound: $isBound")
        val boundIntent = Intent(applicationContext, MyBoundService::class.java)

        when (view.id) {
            R.id.btnBoundService -> bindService(
                boundIntent,
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
            R.id.btnGetData -> {
                val data = boundService?.getDataFromServer()
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
            }
            R.id.btnUnbind -> {
                unbindService(serviceConnection)
//                isBound = false
            }
        }
    }
}
