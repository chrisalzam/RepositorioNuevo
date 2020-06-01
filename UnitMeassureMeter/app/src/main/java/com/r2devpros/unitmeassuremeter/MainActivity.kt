package com.r2devpros.unitmeassuremeter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

const val CONVERTER_FIRST_KEY="CONVERTER KEY"
const val CONVERTER_WIDTH_PIXEL_KEY="WIDTHPX"
const val CONVERTER_HEIGHT_PIXEL_KEY="HEIGHTPX"
const val CONVERTER_WIDTH_DP_KEY="WIDTHDP"
const val CONVERTER_HEIGHT_DP_KEY="HEIGHTDP"
const val CONVERTER_DPI="DPI"
const val ISTEXTVIEW = "ISATEXTVIEW"


class MainActivity : AppCompatActivity() {
    private var skSize: SeekBar? = null
    private var etSize: EditText? = null
    private var skButtonSize: SeekBar? = null
    private var etButtonSize: EditText? = null
    private var tvResult: TextView? = null
    private var btnText: Button? = null
    private var spTextType: Spinner? = null
    private var tvWidthPx: TextView? = null
    private var tvWidthDp: TextView? = null
    private var tvHeightPx: TextView? = null
    private var tvHeightDp: TextView? = null
    private var width = 0
    private var height = 0
    private var widthDP = 0
    private var heightDP = 0
    private var dpi = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Main"

        // get device dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        width = displayMetrics.widthPixels
        height = displayMetrics.heightPixels

        bindScreenSizeViews()

        tvWidthPx?.text = width.toString()
        tvHeightPx?.text = height.toString()


        dpi = getDensity(this)


        widthDP = width * 160 / dpi
        heightDP = height * 160 / dpi

        tvWidthDp?.text = widthDP.toString()
        tvHeightDp?.text = heightDP.toString()

    }

    private fun getDensity(context: Context): Int {
        val density: Float = context.getResources().getDisplayMetrics().density
 //       Log.d("Density", "${density.toString()}")
        return when (density) {
            4f -> 640
            3f -> 480
            2f -> 320
            1.5f -> 240
            1.0f -> 160
            else -> 120
        }
    }

    private fun bindScreenSizeViews() {
        tvWidthPx = findViewById(R.id.tvTotalW_ValuePX)
        tvWidthDp = findViewById(R.id.tvTotalW_ValueDP)
        tvHeightPx = findViewById(R.id.tvTotalH_ValuePX)
        tvHeightDp = findViewById(R.id.tvTotalH_ValueDP)
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
        spTextType = findViewById(R.id.spSizeType)

        ArrayAdapter.createFromResource(
            this,
            R.array.size_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            // Apply the adapter to the spinner
            spTextType?.adapter = adapter
        }

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

    fun convertButtonClick(view: View) {

        val intent = Intent(this,ConvertActivity::class.java)
        intent.putExtra(CONVERTER_FIRST_KEY, Bundle().apply {
            putDouble(CONVERTER_WIDTH_PIXEL_KEY,width.toDouble())
            putDouble(CONVERTER_HEIGHT_PIXEL_KEY,height.toDouble())
            putDouble(CONVERTER_WIDTH_DP_KEY, widthDP.toDouble())
            putDouble(CONVERTER_HEIGHT_DP_KEY, heightDP.toDouble())
            putDouble(CONVERTER_DPI, dpi.toDouble())
        })
        startActivity(intent)
    }

    fun configTextButtonClick(view: View) {
        newWindowData(true)
    }

    fun configButtonClick(view: View) {
        newWindowData(false)
    }

    private fun newWindowData(istv: Boolean)
    {
        val intent = Intent(this,FormatActivity::class.java)
        intent.putExtra(CONVERTER_FIRST_KEY, Bundle().apply {
            putDouble(CONVERTER_WIDTH_PIXEL_KEY,width.toDouble())
            putDouble(CONVERTER_HEIGHT_PIXEL_KEY,height.toDouble())
            putDouble(CONVERTER_WIDTH_DP_KEY, widthDP.toDouble())
            putDouble(CONVERTER_HEIGHT_DP_KEY, heightDP.toDouble())
            putDouble(CONVERTER_DPI, dpi.toDouble())
            putBoolean(ISTEXTVIEW,istv)
        })
        startActivity(intent)
    }
}

