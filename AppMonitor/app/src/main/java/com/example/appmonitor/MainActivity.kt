package com.example.appmonitor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startTimeCounter()
    }

    var counter = 0
    fun startTimeCounter() {
        val timerApp: TextView = findViewById(R.id.tvAppRunning)
        object : CountDownTimer(50000000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerApp.text = counter.toString()
                counter++


            }
            override fun onFinish() {
                timerApp.text = "Finished"
            }
        }.start()
    }



    var watchUrl = "http://www.google.com.mx"
    fun OnLunchButtonClicked(view: View) {
        Log.d("MainActivity_TAG","OnLunchButtonClick")
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
            Log.d("MainActivity_TAG","OnLunchButtonClick: Lost App")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://play.google.com/store/apps/details?id=org.mozilla.firefox")
            startActivity(intent)
        }
    }

    fun getPackages(): ArrayList<PackageInfo>? {
        Log.d("AppNo", "Inside Packages")
        val apps: ArrayList<PackageInfo>? = getInstalledApps(false) /* false = no system packages */
        val max: Int = apps?.size ?: 0
        Log.d("AppNo", "no of apps $max")
        for (i in 0 until max) {
            apps?.get(i)?.prettyPrint()
        }
        return apps
    }

    fun getInstalledApps(getSysPackages: Boolean): ArrayList<PackageInfo>? {
        val res: ArrayList<PackageInfo> = ArrayList<PackageInfo>()
        val packs: MutableList<android.content.pm.PackageInfo> =
            packageManager.getInstalledPackages(0)
        for (i in packs.indices) {
            val p = packs[i]
            if (!getSysPackages && p.versionName == null) {
                continue
            }
            val newInfo = PackageInfo()
            newInfo.appname = p.applicationInfo.loadLabel(packageManager).toString()
            newInfo.pname = p.packageName
            newInfo.classname = p.applicationInfo.className
            newInfo.versionCode = p.versionCode
            newInfo.icon = p.applicationInfo.loadIcon(packageManager)
            val app = packageManager.getLaunchIntentForPackage(p.packageName)
            if (app != null) {
                //dleteintent(app, newInfo.appname, newInfo.icon)
                res.add(newInfo)
            }
        }
        return res
    }

//    Just some Android Apps intents for help anyone.
//
//    Skype: "com.skype.raider", "com.skype.raider.Main"
//
//    Netflix: "com.netflix.mediaclient","com.netflix.mediaclient.ui.launch.UIWebViewActivity"
//
//    ESexplorer: "com.estrongs.android.pop","com.estrongs.android.pop.view.FileExplorerActivity"
//
//    Youtube: "com.google.android.youtube","com.google.android.youtube.HomeActivity"
//
//    Chrome: "com.android.chrome","com.google.android.apps.chrome.Main"
//
//    VLC: "org.videolan.vlc", "org.videolan.vlc.gui.MainActivity"
//
//    MBOXSettings: "com.mbx.settingsmbox","com.mbx.settingsmbox.SettingsMboxActivity"

}