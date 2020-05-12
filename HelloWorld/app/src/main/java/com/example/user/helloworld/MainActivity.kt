package com.example.user.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculatorButtonClick(view: View) {
        Timber.d("MainActivity_TAG: calculatorButtonClick: ")
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }

    fun activityForResultButtonClick(view: View) {
        Timber.d("MainActivity_TAG: activityForResultButtonClick: ")
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("MainActivity_TAG: onActivityResult: requestCode: $requestCode, resultCode: $resultCode, data: $data")
    }
}
