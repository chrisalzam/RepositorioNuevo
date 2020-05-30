package com.r2devpros.unitmeassuremeter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var skSize: SeekBar? = null
    private var etSize: EditText? = null
    private var skButtonSize: SeekBar? = null
    private var etButtonSize: EditText? = null
    private var tvResult: TextView? = null
    private var btnText: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Main"
    }

    override fun onResume() {
        super.onResume()

        bindTextViews()
        bindButtonViews()
    }

    private fun bindTextViews() {
        Log.d("MainActivity_TAG", "bindTextViews: ")
        skSize = findViewById(R.id.skSize)
        etSize = findViewById(R.id.etSize)
        tvResult = findViewById(R.id.tvResult)

        skSize?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("MainActivity_TAG", "onProgressChanged: ")
                etSize?.setText(progress.toString())
                etSize?.setSelection(seekBar?.progress.toString().length)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("MainActivity_TAG", "onStartTrackingTouch: ")


            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("MainActivity_TAG", "onStopTrackingTouch: ${seekBar?.progress.toString()}")
                etSize?.setText(seekBar?.progress.toString())

                //default usa SP
                tvResult?.textSize = seekBar?.progress.toString().toFloat()
//                tvResult?.setTextSize(TypedValue.COMPLEX_UNIT_PX, 0f)
                etSize?.setSelection(seekBar?.progress.toString().length)
            }
        })


        etSize?.addTextChangedListener(object : TextWatcher {
            var value = ""
            override fun afterTextChanged(p0: Editable?) {
                Log.d("MainActivity_TAG", "afterTextChanged: $p0")
                if (p0.toString() != "") {
                    value = p0.toString()
                    skSize?.progress = value.toInt()
                    tvResult?.textSize = value.toFloat()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("MainActivity_TAG", "beforeTextChanged: $p0")

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("MainActivity_TAG", "onTextChanged: $p0")

            }
        })


    }

    private fun bindButtonViews() {
        Log.d("MainActivity_TAG", "bindButtonViews: ")
        skButtonSize = findViewById(R.id.skButtonSize)
        etButtonSize = findViewById(R.id.etButtonSize)
        btnText = findViewById(R.id.btn_Text)

        skButtonSize?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("MainActivity_TAG", "onProgressChanged: ")
                etButtonSize?.setText(progress.toString())
                etButtonSize?.setSelection(seekBar?.progress.toString().length)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("MainActivity_TAG", "onStartTrackingTouch: ")


            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("MainActivity_TAG", "onStopTrackingTouch: ${seekBar?.progress.toString()}")
                etButtonSize?.setText(seekBar?.progress.toString())

                //default usa SP
                btnText?.textSize = seekBar?.progress.toString().toFloat()
//                tvResult?.setTextSize(TypedValue.COMPLEX_UNIT_PX, 0f)
                etButtonSize?.setSelection(seekBar?.progress.toString().length)
            }
        })


        etButtonSize?.addTextChangedListener(object : TextWatcher {
            var value = ""
            override fun afterTextChanged(p0: Editable?) {
                Log.d("MainActivity_TAG", "afterTextChanged: $p0")
                if (p0.toString() != "") {
                    value = p0.toString()
                    skButtonSize?.progress = value.toInt()
                    btnText?.textSize = value.toFloat()
                }
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

