package com.example.threadstest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1000)
            return
        }

        testThreads()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        testThreads()
    }

    private fun testThreads() {
        Timber.plant(CustomLog())

        wait(5) {
            coroutinesTest()
            runnableTest()
            threadTest()
        }

        whoIsFirst { result ->
            Timber.d("MainActivity_TAG: onCreate: $result")
        }
    }

    private fun threadTest() {
        Timber.d("MainActivity_TAG: threadTest: ")
        for (i in 1..10) {
            Thread {
                Timber.d("MainActivity_TAG: threadTest: Thread $i")
            }.start()
        }
    }

    private fun runnableTest() {
        Timber.d("MainActivity_TAG: runnableTest: ")
        for (i in 1..10) {
            Runnable {
                Timber.d("MainActivity_TAG: runnableTest: Runnable $i")
            }.run()
        }
    }

    private fun coroutinesTest() {
        Timber.d("MainActivity_TAG: coroutinesTest: ")
        for (i in 1..10) {
            GlobalScope.launch {
                Timber.d("MainActivity_TAG: coroutinesTest: coroutine $i")
            }
        }
    }

    private fun wait(seconds: Int, onDone: () -> Unit) = GlobalScope.launch {
        Timber.d("MainActivity_TAG: wait: $seconds seconds")
        delay((seconds * 1000).toLong())

        onDone()
    }

    private fun whoIsFirst(myOnDoneFunction: (String) -> Unit) = GlobalScope.launch {
        var place = 0
        Thread {
            place++
            myOnDoneFunction("Thread Finished: $place")
        }.start()

        Runnable {
            place++
            myOnDoneFunction("Runnable Finished: $place")
        }.run()

        GlobalScope.launch {
            place++
            myOnDoneFunction("CoRoutines Finished: $place")
        }
    }
}
