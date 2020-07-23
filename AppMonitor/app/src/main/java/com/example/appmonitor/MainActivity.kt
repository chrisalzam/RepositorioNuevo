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


class MainActivity : AppCompatActivity() {
    private var startFlag = true
    val TAG = "MainActivity_TAG "
    val JOB_ID = 1001
    val REFRESH_INTERVAL: Long = 15 * 60 * 1000 // 15 MINS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //region functions

    fun onPrintPackagesButtonClicked(view: View) {
        Log.d(TAG, "ButtonID: ${view.id}")
        if (startFlag) {
            findViewById<TextView>(view.id).text = getString(R.string.btn_stop)

            val componentName = ComponentName(this@MainActivity, AppJobService::class.java.name)
            val builder: JobInfo.Builder = JobInfo.Builder(JOB_ID, componentName)
            //.setRequiresCharging(true)
//                //.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                //.setPersisted(true)
            builder.setPeriodic(REFRESH_INTERVAL)
            val jobScheduler =
                getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val jobInfo: JobInfo = builder.build()
            val resultCode: Int = jobScheduler.schedule(jobInfo)

            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "Job Scheduled")
            } else {
                Log.d(TAG, "Job Scheduling fail")
            }
            startFlag = false
        } else {
            findViewById<TextView>(view.id).text = getString(R.string.btn_launch)
            val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.cancel(JOB_ID)
            Log.d(TAG, "Job cancelled")
            startFlag = true
        }
    }

    //CREA UN SERVICIO QUE SE EJECUTE CADA 15 MINUTOS
    //THREAD.SLEEP CADA 15 MINUTOS, QUE SE ESPERE 5 SEGUNDOS Y GENERE UN LOG
    //ALARM MANAGER
    //CAMBIAR LOGS A TIMBER
    //Hacer pruebas con broadcast receiver

}