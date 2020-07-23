package com.r2devpros.appmonitor

import android.app.ActivityManager
import android.content.Context
import android.util.Log

object Helper {
    @Suppress("DEPRECATION")
    fun isAppRunning(context: Context, packageName: String): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        val runningApp =
            activityManager.runningAppProcesses.firstOrNull { it.processName == packageName }

        val services = activityManager.getRunningServices(Int.MAX_VALUE)

        val runningService = services.firstOrNull { it.clientPackage == packageName || it.process == packageName }

        Log.d("Helper_TAG:", "isAppRunning: ${runningApp?.processName}, isProcessRunning: ${runningService?.process}")
        return (runningApp != null || (runningService != null && runningService.started))
    }
}