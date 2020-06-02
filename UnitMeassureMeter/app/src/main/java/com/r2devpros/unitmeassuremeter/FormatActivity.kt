package com.r2devpros.unitmeassuremeter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_format.*

class FormatActivity : AppCompatActivity() {
    //region Views
    private var tvWidthPx: TextView? = null
    private var tvHeightPx: TextView? = null
    private var tvWidthDp: TextView? = null
    private var tvHeightDp: TextView? = null
    private var tvIsText: TextView? = null
    private var tvConfigResult: TextView? = null
    private var btnConfigResult: Button? = null
    private var etStyle: EditText? = null
    private var etText: EditText? = null
    private var etPS: EditText? = null
    private var etPT: EditText? = null
    private var etPB: EditText? = null
    private var etPE: EditText? = null
    private var skPadding: SeekBar? = null
    private var etMS: EditText? = null
    private var etMT: EditText? = null
    private var etMB: EditText? = null
    private var etME: EditText? = null
    private var skMargin: SeekBar? = null
    private var etSize: EditText? = null
    private var skSize: SeekBar? = null
    private var etTextColor: EditText? = null
    private var chkT: CheckBox? = null
    private var spFontFamily: Spinner? = null
    private var spFontStyle: Spinner? = null
    //endregion

    //region variables
    var widthPX: Double? = 0.0
    var heightPX: Double? = 0.0
    var widthDP: Double? = 0.0
    var heightDP: Double? = 0.0
    var dpi: Double? = 0.0
    var istextview: Boolean = false
    var format = FontFormat()
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_format)

        getDeviceScreenSize()
        bindTextViews()
        initViews()
        initViewsWithInitialData()
        bindEvents()
    }

    private fun initViews() {
        tvWidthPx?.text = widthPX.toString()
        tvHeightPx?.text = heightPX.toString()
        tvWidthDp?.text = widthDP.toString()
        tvHeightDp?.text = heightDP.toString()

        val tvEnable = istextview
        if (tvEnable) {
            tvIsText?.text = getString(R.string.text_view_config_label)
            tvConfigResult?.visibility = View.VISIBLE
        } else {
            tvIsText?.text = getString(R.string.button_config_label)
            btnConfigResult?.visibility = View.VISIBLE
        }
    }

    private fun initViewsWithInitialData() {
        //set every item with their corresponding style
        //Get previous styles (if any) and apply them to current views
        if (istextview) {
            tvResult?.let {
                etText?.setText(it.text.toString())
                etPaddingStart?.setText(it.paddingStart.toString())
                etPaddingTop?.setText(it.paddingTop.toString())
                etPaddingBottom?.setText(it.paddingBottom.toString())
                etPaddingEnd?.setText(it.paddingEnd.toString())
            }
        } else {
            btnConfigResult?.let {
                etText?.setText(it.text.toString())
                etPaddingStart?.setText(it.paddingStart.toString())
                etPaddingTop?.setText(it.paddingTop.toString())
                etPaddingBottom?.setText(it.paddingBottom.toString())
                etPaddingEnd?.setText(it.paddingEnd.toString())
            }
        }
    }

    private fun getDeviceScreenSize() {
        val bundle = intent.getBundleExtra(CONVERTER_FIRST_KEY)

        widthPX = bundle?.getDouble(CONVERTER_WIDTH_PIXEL_KEY, 0.0)
        heightPX = bundle?.getDouble(CONVERTER_HEIGHT_PIXEL_KEY, 0.0)
        widthDP = bundle?.getDouble(CONVERTER_WIDTH_DP_KEY, 0.0)
        heightDP = bundle?.getDouble(CONVERTER_HEIGHT_DP_KEY, 0.0)
        dpi = bundle?.getDouble(CONVERTER_DPI, 0.0)
        istextview = bundle?.getBoolean(ISTEXTVIEW, true) ?: false
    }

    private fun setFontFormat() {
        format.styleName = etStyle?.text.toString()
        format.text = etText?.text.toString()
        format.pTop = etPaddingTop?.text?.toString()?.toInt() ?: 0
        format.pStart = etPaddingStart?.text?.toString()?.toInt() ?: 0
        format.pEnd = etPaddingEnd?.text?.toString()?.toInt() ?: 0
        format.pBottom = etPaddingBottom?.text?.toString()?.toInt() ?: 0

        val param = tvConfigResult?.layoutParams as ViewGroup.MarginLayoutParams
        format.mTop = param.topMargin
        format.mStart = param.marginStart
        format.mEnd = param.marginEnd
        format.mBottom = param.bottomMargin
        format.textSize = tvConfigResult?.textSize ?: 8f
        //format?.tcolor = tvConfigResult?.currentTextColor
        val decColor = tvConfigResult?.currentTextColor
        val hexColor = String.format("#%06X", 0xFFFFFF and 2584)
        format.tColor = hexColor

    }

    private fun bindTextViews() {
        tvWidthPx = findViewById(R.id.tvWidthPX)
        tvHeightPx = findViewById(R.id.tvHeightPX)
        tvWidthDp = findViewById(R.id.tvWidthDP)
        tvHeightDp = findViewById(R.id.tvHeightDP)
        tvIsText = findViewById(R.id.tvIsTextView)
        tvConfigResult = findViewById(R.id.tvResult)
        btnConfigResult = findViewById(R.id.btnResult)
        etStyle = findViewById(R.id.etTextStyle)
        etText = findViewById(R.id.etText)
        etPS = findViewById(R.id.etPaddingStart)
        etPT = findViewById(R.id.etPaddingTop)
        etPB = findViewById(R.id.etPaddingBottom)
        etPE = findViewById(R.id.etPaddingEnd)
        skPadding = findViewById(R.id.skPadding)
        etMS = findViewById(R.id.etMarginStart)
        etMT = findViewById(R.id.etMarginTop)
        etMB = findViewById(R.id.etMarginBottom)
        etME = findViewById(R.id.etMarginEnd)
        skMargin = findViewById(R.id.skMargin)
        etSize = findViewById(R.id.etTextSize)
        skSize = findViewById(R.id.skTextSize)
        etTextColor = findViewById(R.id.etTextColor)
        chkT = findViewById(R.id.chkTransparent)
        spFontFamily = findViewById(R.id.spTextFontFamily)
        spFontStyle = findViewById(R.id.spTextFontStyle)
    }

    private fun bindEvents() {
        Log.d("FormatActivity_TAG", "bindEvents: ")
        val onFocused: ((View, Boolean) -> Unit) = { v, hasFocus ->
            if (!hasFocus) {
                Log.d("FormatActivity_TAG", "bindEvents: onLostFocus: $v")
                setFontFormat()
                FontApply.apply(
                    fontFormat = format,
                    tv = tvResult,
                    btn = btnConfigResult
                )
            }
        }

        etText?.setOnFocusChangeListener(onFocused)
        etPaddingStart?.setOnFocusChangeListener(onFocused)
        etPaddingTop?.setOnFocusChangeListener(onFocused)
        etPaddingBottom?.setOnFocusChangeListener(onFocused)
        etPaddingEnd?.setOnFocusChangeListener(onFocused)

        findViewById<Button>(R.id.btnGoBack).setOnClickListener {
            Log.d("FormatActivity_TAG", "bindEvents: goBack")
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(if (istextview) FORMAT_DATA_TEXT else FORMAT_DATA_BUTTON, format)

            startActivity(intent)
        }
    }


    private fun setFormatOnView() {

    }
}
