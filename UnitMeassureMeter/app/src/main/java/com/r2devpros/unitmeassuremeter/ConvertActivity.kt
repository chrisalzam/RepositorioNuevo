package com.r2devpros.unitmeassuremeter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView


class ConvertActivity : AppCompatActivity() {
    private var tvWidthPx: TextView? = null
    private var tvHeightPx: TextView? = null
    private var tvWidthDp: TextView? = null
    private var tvHeightDp: TextView? = null
    private var etWidthNew: EditText? = null
    private var etHeightNew: EditText? = null
    private var etItemWidthPX: EditText? = null
    private var etItemHeightPX: EditText? = null
    private var etItemWidthDP: EditText? = null
    private var etItemHeightDP: EditText? = null

    var widthPX = 0.0
    var heightPX = 0.0
    var widthDP = 0.0
    var heightDP = 0.0
    var dpi = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        val bundle = intent.getBundleExtra(CONVERTER_FIRST_KEY)
        widthPX = bundle.getDouble(CONVERTER_WIDTH_PIXEL_KEY, 0.0)
        heightPX = bundle.getDouble(CONVERTER_HEIGHT_PIXEL_KEY, 0.0)
        widthDP = bundle.getDouble(CONVERTER_WIDTH_DP_KEY, 0.0)
        heightDP = bundle.getDouble(CONVERTER_HEIGHT_DP_KEY, 0.0)
        dpi = bundle.getDouble(CONVERTER_DPI, 0.0)

        BindTextViews()
        tvWidthPx?.text = widthPX.toString()
        tvHeightPx?.text = heightPX.toString()
        tvWidthDp?.text = widthDP.toString()
        tvHeightDp?.text = heightDP.toString()

    }


    private fun BindTextViews() {
        tvWidthPx = findViewById(R.id.tvWidthPX)
        tvHeightPx = findViewById(R.id.tvHeightPX)
        tvWidthDp = findViewById(R.id.tvWidthDP)
        tvHeightDp = findViewById(R.id.tvHeightDP)
        etItemWidthPX = findViewById(R.id.etPxItemW)
        etItemHeightPX = findViewById(R.id.etPxItemH)
        etWidthNew = findViewById(R.id.etWidthPX)
        etHeightNew = findViewById(R.id.etHeightPX)
        etItemWidthDP = findViewById(R.id.etDpItemW)
        etItemHeightDP = findViewById(R.id.etDpItemH)

        etItemWidthPX?.addTextChangedListener(object : TextWatcher {
            var widthItem = 0
            override fun afterTextChanged(p0: Editable?) {
                Log.d("ConvertActivity_TAG", "afterTextChangedWidth: $p0")

                var ratio = 0.0
                var newWidthPX = 0.0
                var newItemWidthDP = 0.0
                if (p0.toString() != "" && etWidthNew?.text.toString() != "") {
                    var widthNew = etWidthNew?.text.toString().toInt()
                    widthItem = p0.toString().toInt()
                    ratio = widthPX / widthNew
                    newWidthPX = widthItem / ratio
                    newItemWidthDP = newWidthPX * 160 / dpi

                    etItemWidthDP?.setText(newItemWidthDP.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("ConvertActivity_TAG", "beforeTextChangedWidth: $p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("ConvertActivity_TAG", "onTextChangedWidth: $p0")
            }
        })

        etItemHeightPX?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                var HeightItem = 0
                Log.d("ConvertActivity_TAG", "afterTextChangedHeight: $p0")

                var ratio = 0.0
                var newHeightPX = 0.0
                var newItemHeightDP = 0.0
                if (p0.toString() != "" && etHeightNew?.text.toString() != "") {
                    var widthNew = etHeightNew?.text.toString().toInt()
                    HeightItem = p0.toString().toInt()
                    ratio = heightPX / widthNew
                    newHeightPX = HeightItem / ratio
                    newItemHeightDP = newHeightPX * 160 / dpi

                    etItemHeightDP?.setText(newItemHeightDP.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("ConvertActivity_TAG", "beforeTextChangedHeight: $p0")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("ConvertActivity_TAG", "onTextChangedHeight: $p0")
            }
        })


    }

}
