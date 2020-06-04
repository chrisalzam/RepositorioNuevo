package com.r2devpros.unitmeassuremeter

import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import kotlinx.android.synthetic.main.activity_format.*
import java.util.*

@ExperimentalStdlibApi
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
    private var etTColor: EditText? = null
    private var etBackgroundColor: EditText? = null
    private var chkT: CheckBox? = null
    private var spFontFamily: Spinner? = null
    private var spFontStyle: Spinner? = null
    //endregion

    //region variables
    private var widthPX: Double? = 0.0
    private var heightPX: Double? = 0.0
    private var widthDP: Double? = 0.0
    private var heightDP: Double? = 0.0
    private var dpi: Double? = 0.0
    private var istextview: Boolean = false
    private var format = FontFormat()
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

    //region binding
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
        etTColor = findViewById(R.id.etTextColor)
        etBackgroundColor = findViewById(R.id.etTextBackgroundColor)
        chkT = findViewById(R.id.chkBackgroundTransparent)
        spFontFamily = findViewById(R.id.spTextFontFamily)
        spFontStyle = findViewById(R.id.spTextFontStyle)

        ArrayAdapter.createFromResource(
            this,
            R.array.fonts_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spFontFamily?.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.font_style_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spFontStyle?.adapter = adapter
        }

        skPadding?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("FormatActivity_TAG", "onProgressChanged: ")
                etPS?.setText(progress.toString())
                etPT?.setText(progress.toString())
                etPB?.setText(progress.toString())
                etPE?.setText(progress.toString())

                etSize?.setSelection(seekBar?.progress.toString().length)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("FormatActivity_TAG", "onStartTrackingTouch: ")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("FormatActivity_TAG", "onStopTrackingTouch: ")
                etPS?.setText(seekBar?.progress.toString())
                etPT?.setText(seekBar?.progress.toString())
                etPB?.setText(seekBar?.progress.toString())
                etPE?.setText(seekBar?.progress.toString())

                //default usa SP
                tvConfigResult?.setPadding(seekBar?.progress ?: 0)
                etPS?.setSelection(seekBar?.progress.toString().length)

            }
        })

    }

    private fun bindEvents() {
        Log.d("FormatActivity_TAG", "bindEvents: ")
        val onFocused: ((View, Boolean) -> Unit) = { v, hasFocus ->
            if (!hasFocus) {
                Log.d("FormatActivity_TAG", "bindEvents: onLostFocus: $v")
                setFontFormat()
                FontApply.apply(
                    fontFormat = format,
                    tv = if (istextview) tvResult else null,
                    btn = if (!istextview) btnConfigResult else null
                )
            }
        }
        val onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("FormatActivity_TAG", "onNothingSelected: ")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setFontFormat()
                FontApply.apply(
                    fontFormat = format,
                    tv = if (istextview) tvResult else null,
                    btn = if (!istextview) btnConfigResult else null
                )
            }
        })

        etText?.setOnFocusChangeListener(onFocused)

        //region Padding
        etPaddingStart?.setOnFocusChangeListener(onFocused)
        etPaddingTop?.setOnFocusChangeListener(onFocused)
        etPaddingBottom?.setOnFocusChangeListener(onFocused)
        etPaddingEnd?.setOnFocusChangeListener(onFocused)
        //endregion

        //region Margin
        etMarginStart?.setOnFocusChangeListener(onFocused)
        etMarginTop?.setOnFocusChangeListener(onFocused)
        etMarginBottom?.setOnFocusChangeListener(onFocused)
        etMarginEnd?.setOnFocusChangeListener(onFocused)
        //endregion

        etTextSize?.setOnFocusChangeListener(onFocused)
        etTextColor?.setOnFocusChangeListener(onFocused)
        etBackgroundColor?.setOnFocusChangeListener(onFocused)
        chkT?.setOnCheckedChangeListener(onFocused)

        spFontFamily?.onItemSelectedListener = onItemSelectedListener
        spFontStyle?.onItemSelectedListener = onItemSelectedListener

        findViewById<Button>(R.id.btnGoBack).setOnClickListener {
            Log.d("FormatActivity_TAG", "bindEvents: goBack")
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(if (istextview) FORMAT_DATA_TEXT else FORMAT_DATA_BUTTON, format)

            startActivity(intent)
        }
    }
//endregion

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

    private fun getDeviceScreenSize() {
        val bundle = intent.getBundleExtra(CONVERTER_FIRST_KEY)

        widthPX = bundle?.getDouble(CONVERTER_WIDTH_PIXEL_KEY, 0.0)
        heightPX = bundle?.getDouble(CONVERTER_HEIGHT_PIXEL_KEY, 0.0)
        widthDP = bundle?.getDouble(CONVERTER_WIDTH_DP_KEY, 0.0)
        heightDP = bundle?.getDouble(CONVERTER_HEIGHT_DP_KEY, 0.0)
        dpi = bundle?.getDouble(CONVERTER_DPI, 0.0)
        istextview = bundle?.getBoolean(ISTEXTVIEW, true) ?: false
    }

    private fun initViewsWithInitialData() {
        //set every item with their corresponding style
        //Get previous styles (if any) and apply them to current views
        val view: View = if (istextview) {
            tvResult?.let {
                etText.setValue(it.text)
                etTextSize.setValue(it.textSize)
                etTextColor.setValue(it.getColorHex())

                val fontFamilyPosition = spFontFamily.getFontFamilyPosition(it.tag)
                spFontFamily?.setSelection(fontFamilyPosition)

                val fontStylePosition =
                    spFontStyle.getFontStylePosition(FontStyle.fromValue(it.typeface.style))
                spFontStyle?.setSelection(fontStylePosition)
            }
            tvResult ?: return
        } else {
            btnConfigResult?.let {
                etText.setValue(it.text)
                etTextSize.setValue(it.textSize)
                etTextColor.setValue(it.getColorHex())

                val fontFamilyPosition = spFontFamily.getFontFamilyPosition(it.tag)
                spFontFamily?.setSelection(fontFamilyPosition)

                val fontStylePosition =
                    spFontStyle.getFontStylePosition(FontStyle.fromValue(it.typeface.style))
                spFontStyle?.setSelection(fontStylePosition)
            }
            btnConfigResult ?: return
        }

        view.let {
            //region Padding
            etPS.setValue(it.paddingStart)
            etPT.setValue(it.paddingTop)
            etPB.setValue(it.paddingBottom)
            etPE.setValue(it.paddingEnd)
            //endregion

            //region Margin
            etMS.setValue(it.marginStart)
            etMT.setValue(it.marginTop)
            etMB.setValue(it.marginBottom)
            etME.setValue(it.marginEnd)
            //endregion

            etBackgroundColor.setValue(it.getBackgroundColorHex())
            chkT?.isChecked = (etBackgroundColor?.text.isNullOrEmpty())
        }
    }

    private fun setFontFormat() {
        format.styleName = etStyle.getText()
        format.text = etText.getText()

        //region Padding
        format.pTop = etPT.getIntValue()
        format.pStart = etPS.getIntValue()
        format.pEnd = etPE.getIntValue()
        format.pBottom = etPB.getIntValue()
        //endregion

        //region Margin
        format.mStart = etMS.getIntValue()
        format.mTop = etMT.getIntValue()
        format.mBottom = etMB.getIntValue()
        format.mEnd = etME.getIntValue()
        //endregion

        format.textSize = etTextSize.getFloatValue(8f)
        format.tColor = etTextColor.getText("#FFFFFF")
        format.backgroundColor = etBackgroundColor.getText("")
        format.backgroundTransparent = chkT?.isChecked ?: false
        format.fontFamily = spFontFamily?.selectedItem?.toString()?.let {
            Fonts.fromDescription(it)?.resourceName ?: ""
        } ?: ""
        format.fontStyle = spFontStyle?.selectedItem?.toString()?.let {
            FontStyle.fromDescription(it).value
        } ?: Typeface.NORMAL
    }

    //region Extension Functions
    private fun Spinner?.getFontFamilyPosition(fontFamily: Any): Int {
        val sp = this ?: return 0
        for (i in 0 until sp.adapter.count) {
            if (sp.adapter.getItem(i).toString() == fontFamily.toString().toReadableName()) {
                return i
            }
        }

        return 0
    }

    private fun Spinner?.getFontStylePosition(fontStyle: FontStyle): Int {
        val sp = this ?: return 0
        for (i in 0 until sp.adapter.count) {
            if (sp.adapter.getItem(i).toString() == fontStyle.description) {
                return i
            }
        }

        return 0
    }

    private fun EditText?.getIntValue(defaultValue: Int = 0): Int =
        this?.text?.toString()?.toInt() ?: defaultValue

    private fun EditText?.getFloatValue(defaultValue: Float = 0f): Float =
        this?.text?.toString()?.toFloat() ?: defaultValue

    private fun EditText?.getText(defaultValue: String = ""): String =
        this?.text?.toString() ?: defaultValue

    private fun EditText?.setValue(defaultValue: Any) = this?.setText(defaultValue.toString())

    private fun TextView.getColorHex(): String =
        java.lang.String.format("#%06X", 0xFFFFFF and this.currentTextColor)

    private fun View.getBackgroundColorHex(): String {
        try {
            return java.lang.String.format(
                "#%06X",
                0xFFFFFF and (this.background as ColorDrawable).color
            )
        } catch (e: Exception) {
            Log.d("FormatActivity_TAG", "getBackgroundColorHex: exception: ${e.message}")
        }

        return ""
    }

    private fun String.toReadableName(): String {
        var result = ""
        this.split("_").forEach {
            result += it.capitalize(Locale.US).replace("_", "") + " "
        }

        return result.trim()
    }

    private fun Spinner?.getSelectedValue() = this?.selectedItem?.toString() ?: ""
//endregion
}
