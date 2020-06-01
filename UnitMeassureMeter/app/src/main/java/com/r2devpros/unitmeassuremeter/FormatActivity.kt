package com.r2devpros.unitmeassuremeter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class FormatActivity : AppCompatActivity() {

    private var tvWidthPx: TextView? = null
    private var tvHeightPx: TextView? = null
    private var tvWidthDp: TextView? = null
    private var tvHeightDp: TextView? = null
    private var tvIsText: TextView? = null

    var widthPX: Double? = 0.0
    var heightPX: Double? = 0.0
    var widthDP: Double? = 0.0
    var heightDP: Double? = 0.0
    var dpi: Double? = 0.0
    var istextview: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_format)

        val bundle = intent.getBundleExtra(CONVERTER_FIRST_KEY)

        widthPX = bundle?.getDouble(CONVERTER_WIDTH_PIXEL_KEY, 0.0)
        heightPX = bundle?.getDouble(CONVERTER_HEIGHT_PIXEL_KEY, 0.0)
        widthDP = bundle?.getDouble(CONVERTER_WIDTH_DP_KEY, 0.0)
        heightDP = bundle?.getDouble(CONVERTER_HEIGHT_DP_KEY, 0.0)
        dpi = bundle?.getDouble(CONVERTER_DPI, 0.0)
        istextview = bundle?.getBoolean(ISTEXTVIEW, true)

        bindTextViews()

        tvWidthPx?.text = widthPX.toString()
        tvHeightPx?.text = heightPX.toString()
        tvWidthDp?.text = widthDP.toString()
        tvHeightDp?.text = heightDP.toString()

        if (istextview ?: true)
            tvIsText?.text = "TextView Configuration"
        else
            tvIsText?.text = "Button Configuration"
    }

    private fun bindTextViews() {
        tvWidthPx = findViewById(R.id.tvWidthPX)
        tvHeightPx = findViewById(R.id.tvHeightPX)
        tvWidthDp = findViewById(R.id.tvWidthDP)
        tvHeightDp = findViewById(R.id.tvHeightDP)
        tvIsText = findViewById(R.id.tvIsTextView)
    }
}
