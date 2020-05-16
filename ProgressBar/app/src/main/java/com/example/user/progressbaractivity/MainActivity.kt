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

const val START_PROGRESS = 100
const val UPDATE_COUNT = 101
const val MAX = 100

class MainActivity : AppCompatActivity() {
    //region UI Views
    private lateinit var progressBar: ProgressBar
    private lateinit var startProgress: Button
    private lateinit var stopProgress: Button
    private lateinit var textView: TextView
    //endregion

    //region Handler and Thread
    var mHandlerThread: Handler? = null
    var thread1: Thread? = null
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //#region SETS THE LAYOUT LIKED TO THIS ACTIVITY
        setContentView(R.layout.activity_main)
        //endregion

        viewBinding()

        setThreadBehavior()
    }

    private fun setThreadBehavior() {
        thread1 = Thread(Runnable {
            for (i in 0..99) {
                Log.d("_TAG", ":$i")

                //region set Progress
                progressBar.progress = i
                //endregion

                //region wait 1 second
                try {
                    Thread.sleep(1000)
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                }
                //endregion

                //region Send Message
                val message = Message()
                message.what = UPDATE_COUNT
                //Arguments = Some alternative data
                message.arg1 = i
                mHandlerThread?.sendMessage(message)
                //endregion
            }
        })
    }

    private fun viewBinding() {
        progressBar = findViewById(R.id.progressBar)
        startProgress = findViewById(R.id.start_progress)
        textView = findViewById(R.id.textView)

        //ESTABLECE NO NULO EL OBJETO
        //progressBar!!.max = MAX
        progressBar.max = MAX

        //button.onClick
        startProgress.setOnClickListener {
            //val currentProgress = progressBar.progress
            mHandlerThread?.sendEmptyMessage(START_PROGRESS)
        }
    }

    override fun onResume() {
        super.onResume()
        /*mHandlerThread = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                if (msg.what == START_PROGRESS) {
                    thread1?.start()
                } else if (msg.what == UPDATE_COUNT) {
                    textView.text = "Count ${msg.arg1}"
                }
            }
        }*/
        thread1?.let {
            mHandlerThread = MyHandler(it) { updateMessage ->
                textView.text = updateMessage
            }
        }
    }

    fun onStopProgressClick(view: View) {
        Log.d("MainActivity_TAG", "onStopProgressClick: $view")
        thread1?.interrupt()
    }
}
