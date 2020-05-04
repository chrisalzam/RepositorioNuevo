package com.example.user.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import timber.log.Timber

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_constraint_layout)
    }

    fun onNumberButtonClicked(view: View) {
        Timber.d("CalculatorActivity_TAG: onNumberButtonClicked: ${view.id}")
    }
}
