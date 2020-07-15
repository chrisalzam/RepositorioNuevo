package com.example.appmonitor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var p: ArrayList<PackageInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        p = getInstalledApps(false) ?: return
    }

    var counter = 0
    var foundApp = "com.sentrics.engage360"

    private fun startTimeCounter() {
        val timerApp: TextView = findViewById(R.id.tvAppRunning)
        object : CountDownTimer(500000, 5000) {
            override fun onTick(millisUntilFinished: Long) {
                timerApp.text = counter.toString()
                counter++
                Log.d("MainActivity_TAG: TimerCountdown, SECOND: ", "$counter")
                Log.d("MainActivity_TAG: Number of INSTALLED APPS: ", "${p.size}")
                val packageAppFound = p.firstOrNull { it.pName == foundApp } ?: return

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

            override fun onFinish() {
                timerApp.text = getString(R.string.finished_text)
            }
        }.start()
    }

    private var netflixId = "43598743" // <== isn't a real movie id
    private var watchUrl = "http://www.netflix.com/watch/$netflixId"

    fun onLunchButtonClicked(view: View) {
        Log.d("MainActivity_TAG", "OnLunchButtonClick, ${view.id}")
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setClassName(
                "com.netflix.mediaclient",
                "com.netflix.mediaclient.ui.launch.UIWebViewActivity"
            )
            intent.data = Uri.parse(watchUrl)
            startActivity(intent)
        } catch (e: Exception) {
            // netflix app isn't installed, send to website.
            Log.d("MainActivity_TAG", "OnLunchButtonClick: Lost App")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("https://play.google.com/store/apps/details?id=org.mozilla.firefox")
            startActivity(intent)
        }
    }

    private fun getInstalledApps(getSysPackages: Boolean): ArrayList<PackageInfo>? {
        val res: ArrayList<PackageInfo> = ArrayList()
        val packs: MutableList<android.content.pm.PackageInfo> =
            packageManager.getInstalledPackages(0)

        for (i in packs.indices) {
            val p = packs[i]
            if (!getSysPackages && p.versionName == null) {
                continue
            }
            val newInfo = PackageInfo("", "")
            newInfo.appName = p.applicationInfo.loadLabel(packageManager).toString()
            newInfo.pName = p.packageName
            val app = packageManager.getLaunchIntentForPackage(p.packageName)
            if (app != null) {
                Log.d("MainActivity_TAG", "packageName: ${newInfo.pName}")
                res.add(newInfo)
            }
        }
        return res
    }

    fun onPrintPackagesButtonClicked(view: View) {
        Log.d("MainActivity_TAG", "Clicked on Print Packages Button: ${view.id}")
        startTimeCounter()
    }
}