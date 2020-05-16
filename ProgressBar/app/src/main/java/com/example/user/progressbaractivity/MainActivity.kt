package com.example.user.progressbaractivity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.*
import android.view.View


class MainActivity : AppCompatActivity() {
    var progressBar: ProgressBar? = null
    var startProgress: Button? = null
    var stopProgress: Button? = null
    var textView: TextView? = null
    var MAX = 100
    var mHandlerThread: Handler? = null
    private val START_PROGRESS = 100
    private val UPDATE_COUNT = 101
    var thread1: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //SETS THE LAYOUT LIKED TO THIS ACTIVITY
        setContentView(R.layout.activity_main)

        viewBinding()

        //ESTABLECE NO NULO EL OBJETO
        progressBar!!.max = MAX
        thread1 = Thread(Runnable {
            for (i in 0..99) {
                Log.d("I", ":$i")
                progressBar!!.progress = i
                try {
                    Thread.sleep(1000)
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                }
                val message = Message()
                message.what = UPDATE_COUNT
                message.arg1 = i
                mHandlerThread!!.sendMessage(message)
            }
        })
        startProgress!!.setOnClickListener {
            val currentProgess = progressBar!!.progress
            /*Message message = new Message();
            message.what = START_PROGRESS;*/mHandlerThread!!.sendEmptyMessage(START_PROGRESS)
        }
    }

    private fun viewBinding() {
        progressBar = findViewById(R.id.progressBar)
        startProgress = findViewById(R.id.start_progress)
        textView = findViewById(R.id.textView)
    }

    override fun onResume() {
         super.onResume()
         mHandlerThread = object : Handler() {
             override fun handleMessage(msg: Message) {
                 super.handleMessage(msg)
                 if (msg.what == START_PROGRESS) {
                     thread1!!.start()
                 } else if (msg.what == UPDATE_COUNT) {
                     textView!!.text = "Count" + msg.arg1
                 }
             }
         }
     }
}
