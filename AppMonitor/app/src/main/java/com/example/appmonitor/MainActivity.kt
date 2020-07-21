package com.example.appmonitor

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var p: ArrayList<PackageInfo>
    private var timer = Timer("schedule", true)
    private var startFlag = true
    var foundApp = "com.r2devpros.unitmeassuremeter"
    val TAG = "MainActivity_TAG "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        p = getInstalledApps() ?: return

    }

    //region functions

    fun onPrintPackagesButtonClicked(view: View) {
        Log.d(TAG, "ButtonID: ${view.id}")
        if (startFlag) {
            findViewById<TextView>(view.id).text = getString(R.string.btn_stop)
            val componentName = ComponentName(this, AppJobService::class.java)
            val info = JobInfo.Builder(123, componentName)
                //.setRequiresCharging(true)
                //.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(1 * 60 * 1000)
                .build()

            val scheduler =
                getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val resultCode = scheduler.schedule(info)

            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "Job Scheduled")
            } else {
                Log.d(TAG, "Job Scheduling fail")
            }
            startFlag = false
        }
        else
        {
            findViewById<TextView>(view.id).text = getString(R.string.btn_launch)
            val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.cancel(123)
            Log.d(TAG, "Job cancelled")
            startFlag = true
        }
    }

    //    private fun getInstalledApps(): ArrayList<PackageInfo>? {
//        val res: ArrayList<PackageInfo> = ArrayList()
//        val packs: MutableList<android.content.pm.PackageInfo> =
//            packageManager.getInstalledPackages(0)
//
//        for (i in packs.indices) {
//            val newInfo = PackageInfo("", "")
//            newInfo.appName = packs[i].applicationInfo.loadLabel(packageManager).toString()
//            newInfo.pName = packs[i].packageName
//            val app = packageManager.getLaunchIntentForPackage(packs[i].packageName)
//            if (app != null) {
//                Log.d("MainActivity_TAG", "packageName: ${newInfo.pName}")
//                res.add(newInfo)
//            }
//        }
//        return res
//    }

//    private fun startTimeCounter() {
//        Log.d("MainActivity_TAG", "ENTERED TO STARTTIMECOUNTER2")
//        timer = Timer("schedule", true)
//        // schedule a single event
//        timer.schedule(10, 5000) {
//            //Log.d("MainActivity_TAG", "$counter2")
//            Log.d("MainActivity_TAG: Number of INSTALLED APPS: ", "${p.size}")
//            val packageAppFound = p.firstOrNull { it.pName == foundApp } ?: return@schedule
//            Log.d("MainActivity_TAG", "$foundApp IS INSTALLED")
//
//            when {
//                Helper.isAppRunning(applicationContext, packageAppFound.pName) -> {
//                    Log.d("MainActivity_TAG:", "onTick: App is already Running")
//                }
//                packageAppFound.pName.isNotEmpty() -> {
//                    Log.d("MainActivity_TAG: ", "LUNCHING your App")
//                    val intent = packageManager.getLaunchIntentForPackage(packageAppFound.pName)
//                    startActivity(intent)
//                }
//                else -> {
//                    Log.d("MainActivity_TAG: ", "Your App is NOT INSTALLED")
//                }
//            }
//        }
//    }


    //endregion

    //CREA UN SERVICIO QUE SE EJECUTE CADA 15 MINUTOS
    //THREAD.SLEEP CADA 15 MINUTOS, QUE SE ESPERE 5 SEGUNDOS Y GENERE UN LOG
}