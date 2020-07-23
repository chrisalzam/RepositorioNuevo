package com.example.appmonitor

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList

class AppJobService : JobService() {
    var looking4App = "com.r2devpros.unitmeassuremeter"
    private val TAG = "AppJobService_TAG: "
    var jobCancelled = false

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job Started")
        findInstalledRunningApp(params)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job cancelled before completion")
        jobCancelled = true
        return false
    }



    private fun findInstalledRunningApp(params: JobParameters?) {
        val p = getInstalledApps() ?: return
        var notInstalled : Boolean

//        val timer = Timer()
//        val monitor = object : TimerTask() {
//            override fun run() {
                Log.d("$TAG: Number of INSTALLED APPS: ", "${p.size}")
                for (i in 1..5) {
                    Log.d(TAG,"Second $i")
                    if (jobCancelled) {
                        return
                    }

                    val packageAppFound = p.firstOrNull { it.pName == looking4App } ?: return
                    notInstalled = false
                    Log.d(TAG, "$looking4App IS INSTALLED")
                    when {
                        Helper.isAppRunning(applicationContext, packageAppFound.pName) -> {
                            Log.d(TAG, "App is already Running")
                        }
                        packageAppFound.pName.isNotEmpty() -> {
                            Log.d(TAG, "LUNCHING your App")
                            val intent =
                                packageManager.getLaunchIntentForPackage(packageAppFound.pName)
                            startActivity(intent)
                        }
                    }
                    if (notInstalled)
                        Log.d(TAG, "Your App IS NOT INSTALLED")
                }
                Log.d(TAG, "Job Finished")
                jobFinished(params, true)
//            }
//        }
//        timer.schedule(monitor, 1000, 1000)
    }

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
                Log.d(TAG, "packageName: ${newInfo.pName}")
                res.add(newInfo)
            }
        }
        return res
    }


}