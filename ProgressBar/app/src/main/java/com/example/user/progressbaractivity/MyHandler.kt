package com.example.user.progressbaractivity

import android.os.Handler
import android.os.Message
import android.util.Log

class MyHandler(
    private val thread: Thread,
    private val onReportUpdate: (String) -> Unit
) : Handler() {
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        Log.d("MyHandler_TAG", "handleMessage: ")
        when (msg.what) {
            START_PROGRESS -> {
                Log.d("MyHandler_TAG", "handleMessage: START_PROGRESS")
                /*when(thread.state) {

                }*/
                thread.start()
            }
            else -> {
                Log.d("MyHandler_TAG", "handleMessage: UPDATE_PROGRESS")
                onReportUpdate("Count: ${msg.arg1}")
            }
        }
    }
}