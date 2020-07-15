package com.example.appmonitor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    //com.google.android.youtube
    //com.skype.raider
    //com.example.user.progressbaractivity
    var counter = 0
    var foundApp = "com.example.user.progressbaractivity"
    var packageAppFound = PackageInfo("","")
    private fun startTimeCounter() {
        val timerApp: TextView = findViewById(R.id.tvAppRunning)
        object : CountDownTimer(500000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerApp.text = counter.toString()
                counter++
                val p: ArrayList<PackageInfo>? = getInstalledApps(false)
                Log.d("MainActivity_TAG: TimerCountdown, SECOND: ","$counter")
                Log.d("MainActivity_TAG: Number of INSTALLED APPS: ","${p?.size}")
                if (p != null) {
                    for (i in 0 until p.size){
                        //Log.d("MainActivity_TAG", "${p[i].pname}")
                        if(p[i].pname == foundApp) {
                            Log.d("MainActivity_TAG", "$foundApp IS INSTALLED")
                            packageAppFound = p[i]
                        }
                    }
                    val myRunApps= MyRunApps()
                    if(myRunApps.isAppRunning(this@MainActivity, packageAppFound.pname ?: ""))
                    {
                        Log.d("MainActivity_TAG: ","Your App is already RUNNING")
                    }
                    else if (!packageAppFound.pname.isNullOrEmpty())
                    {
                        Log.d("MainActivity_TAG: ","LUNCHING your App")
                        //val pm = packageManager
                        val intent = packageManager.getLaunchIntentForPackage(packageAppFound.pname)
                        startActivity(intent)
                    }
                    else
                    {
                        Log.d("MainActivity_TAG: ","Your App is NOT INSTALLED")
                    }
                } //get PackageAppNameFound

            }
            override fun onFinish() {
                timerApp.text = "Finished"
            }
        }.start()
    }

    private var netflixId = "43598743" // <== isn't a real movie id
    private var watchUrl = "http://www.netflix.com/watch/$netflixId"
    fun onLunchButtonClicked(view: View) {
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

//    fun getPackages(): ArrayList<PackageInfo>? {
//        Log.d("AppNo", "Inside Packages")
//        val apps: ArrayList<PackageInfo>? = getInstalledApps(false) /* false = no system packages */
//        val max: Int = apps?.size ?: 0
//        Log.d("AppNo", "no of apps $max")
//        for (i in 0 until max) {
//            apps?.get(i)?.PrintInfo()
//        }
//        return apps
//    }

    fun getInstalledApps(getSysPackages: Boolean): ArrayList<PackageInfo>? {
        val res: ArrayList<PackageInfo> = ArrayList()
        val packs: MutableList<android.content.pm.PackageInfo> =
            packageManager.getInstalledPackages(0)

        for (i in packs.indices) {
            val p = packs[i]
            if (!getSysPackages && p.versionName == null) {
                continue
            }
            val newInfo = PackageInfo("","")
            newInfo.appname = p.applicationInfo.loadLabel(packageManager).toString()
            newInfo.pname = p.packageName
            //newInfo.classname = p.applicationInfo.className
            //newInfo.icon = p.applicationInfo.loadIcon(packageManager)
            val app = packageManager.getLaunchIntentForPackage(p.packageName)
            if (app != null) {
                //dleteintent(app, newInfo.appname, newInfo.icon)
                res.add(newInfo)
            }
        }
        return res
    }

    fun onPrintPackagesButtonClicked(view: View) {
        Log.d("MainActivity_TAG","Clicked on Print Packages Button")
        startTimeCounter()
    }

//    fun getIfAppIsRunning(packName: String): Boolean
//    {
//        // The first in the list of RunningTasks is always the foreground task.
//        val foregroundTaskInfo =
//            this@AppService.getSystemService(Context.ACTIVITY_SERVICE).getRunningTasks(1)[0]
//        return true
//    }


//    private fun getForegroundApp(): RunningAppProcessInfo? {
//        var result: RunningAppProcessInfo? = null
//        var info: RunningAppProcessInfo? = null
//        if (mActivityManager == null) mActivityManager =
//            mContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        val l: List<RunningAppProcessInfo> =
//            mActivityManager.getRunningAppProcesses()
//        val i = l.iterator()
//        while (i.hasNext()) {
//            info = i.next()
//            if (info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
//                && !isRunningService(info.processName)
//            ) {
//                result = info
//                break
//            }
//        }
//        return result
//    }

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