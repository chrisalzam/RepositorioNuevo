package com.example.appmonitor

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    lateinit var p: ArrayList<PackageInfo>
    private var timer = Timer("schedule", true)
    private var startFlag = true
    var foundApp = "com.r2devpros.unitmeassuremeter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        p = getInstalledApps() ?: return
    }

    //region functions
    private fun getInstalledApps(): ArrayList<PackageInfo>? {
        val res: ArrayList<PackageInfo> = ArrayList()
        val packs: MutableList<android.content.pm.PackageInfo> =
            packageManager.getInstalledPackages(0)

        for (i in packs.indices) {
            val newInfo = PackageInfo("", "")
            newInfo.appName = packs[i].applicationInfo.loadLabel(packageManager).toString()
            newInfo.pName = packs[i].packageName
            val app = packageManager.getLaunchIntentForPackage(packs[i].packageName)
            if (app != null) {
                Log.d("MainActivity_TAG", "packageName: ${newInfo.pName}")
                res.add(newInfo)
            }
        }
        return res
    }


    fun onPrintPackagesButtonClicked(view: View) {
        Log.d("MainActivity_TAG ", "ButtonID: ${view.id}")
        if (startFlag) {
            findViewById<TextView>(view.id).text = getString(R.string.btn_stop)
            startTimeCounter()
            startFlag = false
        } else {
            findViewById<TextView>(view.id).text = getString(R.string.btn_launch)
            timer.cancel()
            startFlag = true
            Log.d("MainActivity_TAG","Stop Thread")
        }
    }


    private fun startTimeCounter() {
        Log.d("MainActivity_TAG", "ENTERED TO STARTTIMECOUNTER2")
        timer = Timer("schedule", true)
        // schedule a single event
        timer.schedule(10, 5000) {
            //Log.d("MainActivity_TAG", "$counter2")
            Log.d("MainActivity_TAG: Number of INSTALLED APPS: ", "${p.size}")
            val packageAppFound = p.firstOrNull { it.pName == foundApp } ?: return@schedule
            Log.d("MainActivity_TAG", "$foundApp IS INSTALLED")

            when {
                Helper.isAppRunning(applicationContext, packageAppFound.pName) -> {
                    Log.d("MainActivity_TAG:", "onTick: App is already Running")
                }
                packageAppFound.pName.isNotEmpty() -> {
                    Log.d("MainActivity_TAG: ", "LUNCHING your App")
                    val intent = packageManager.getLaunchIntentForPackage(packageAppFound.pName)
                    startActivity(intent)
                }
                else -> {
                    Log.d("MainActivity_TAG: ", "Your App is NOT INSTALLED")
                }
            }
        }
    }
    //endregion
}