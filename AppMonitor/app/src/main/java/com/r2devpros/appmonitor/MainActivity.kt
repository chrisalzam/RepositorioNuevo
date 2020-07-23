package com.r2devpros.appmonitor

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    var foundApp = "com.sentrics.engage360"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startTimeCounter()
        moveTaskToBack(true)
    }

    //region functions
    private fun startTimeCounter() {
        Log.d("MainActivity_TAG:", "startTimeCounter: ")
        object : CountDownTimer(120000, 3000) {
            override fun onFinish() {
                Log.d("MainActivity_TAG:", "onFinish: ")
                finish()
            }

            override fun onTick(reminingMilis: Long) {
                Log.d("MainActivity_TAG:", "onTick: reminingSeconds = ${reminingMilis / 1000}")

                when {
                    Helper.isAppRunning(applicationContext, foundApp) -> {
                        Log.d("MainActivity_TAG:", "onTick: App is already Running")
                    }
                    foundApp.isNotEmpty() -> {
                        Log.d("MainActivity_TAG: ", "LUNCHING your App")
                        val intent = packageManager.getLaunchIntentForPackage(foundApp)
                        startActivity(intent)
                    }
                    else -> {
                        Log.d("MainActivity_TAG: ", "Your App is NOT INSTALLED")
                    }
                }
            }
        }.start()
    }
    //endregion
}