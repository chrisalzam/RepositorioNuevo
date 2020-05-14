package com.example.user.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import timber.log.Timber
import kotlin.math.sqrt

class CalculatorActivity : AppCompatActivity() {
    private lateinit var calculator: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_constraint_layout)

        calculator = Calculator()

        val bundle = intent.getBundleExtra(CALCULATOR_SECOND_KEY)
        calculator.firstValue = bundle?.getInt(CALCULATOR_KEY, 0).toString()
        showResult(calculator.firstValue)

        title = bundle?.getString(CALCULATOR_BUNDLE_KEY)
    }

    fun onNumberButtonClicked(view: View) {
        val value = (view as Button).text.toString()
        Timber.d("CalculatorActivity_TAG: onNumberButtonClicked: $value")
        if (calculator.firstValue != "0")
            calculator.firstValue += value
        else
            calculator.firstValue = value

        showResult(calculator.firstValue)
    }

    fun onOperationButtonClicked(view: View) {
        val code = (view as Button).text.toString()
        Timber.d("CalculatorActivity_TAG: onOperationButtonClicked: $code, first: $calculator.firstValue, second: $calculator.secondValue")

        val result = calculator.doOperation(code)
        showResult(result)
    }

    fun onDecimalButtonClicked(view: View) {
        Timber.d("CalculatorActivity_TAG: onDecimalButtonClicked: ${(view as Button).text}")
    }

    fun onACButtonClicked(view: View) {
        Timber.d("CalculatorActivity_TAG: onACButtonClicked: ${(view as Button).text}")
        calculator.firstValue = "0"
        calculator.secondValue = ""
        showResult("0")
    }

    private fun showResult(result: String) {
        Timber.d("CalculatorActivity_TAG: showResult: $result")
        val tvScreen = findViewById<TextView>(R.id.tvScreen)
        tvScreen.text = result
    }

    fun onEqualButtonClicked(view: View) {
        if(calculator.firstValue == "") {
            calculator.firstValue = calculator.secondValue
            val result = calculator.doOperation(calculator.currentOperation.code)
            showResult(result)
        }
        else {
            val result = calculator.doOperation(calculator.currentOperation.code)
            showResult(result)
        }
    }
}
