package com.r2devpros.unitmeassuremeter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var skSize: SeekBar? = null
    private var etSize: EditText? = null
    private var tvResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Main"
    }

    override fun onResume() {
        super.onResume()

        bindViews()
    }

    private fun bindViews() {
        Log.d("MainActivity_TAG", "bindViews: ")
        skSize = findViewById(R.id.skSize)
        etSize = findViewById(R.id.etSize)
        tvResult = findViewById(R.id.tvResult)

        skSize?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("MainActivity_TAG", "onProgressChanged: ")

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("MainActivity_TAG", "onStartTrackingTouch: ")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("MainActivity_TAG", "onStopTrackingTouch: ${seekBar?.progress.toString()}")
                val progressval = seekBar?.progress.toString()
                etSize?.setText(progressval)

                //default usa SP
                tvResult?.textSize = progressval.toFloat()
//                tvResult?.setTextSize(TypedValue.COMPLEX_UNIT_PX, 0f)
            }
        })


        etSize?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                Log.d("MainActivity_TAG", "afterTextChanged: $p0")
                skSize?.progress = p0.toString().toInt()
                tvResult?.textSize = p0.toString().toFloat()
                if (p0.toString() == "")
                    Log.d("MainActivity_TAG", "etSize value is null")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("MainActivity_TAG", "beforeTextChanged: $p0")

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("MainActivity_TAG", "onTextChanged: $p0")

            }
        })


    }
}

