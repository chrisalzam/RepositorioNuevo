package com.example.iconcardview

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private var ivIcon2: ImageView? = null
    private var ivIcon3: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivIcon2 = findViewById(R.id.ivIcon2)
        ivIcon2?.setImageDrawable(resources.getDrawable(R.drawable.ic_chess_queen, null))

        ivIcon3 = findViewById(R.id.ivIcon3)
        val d = resources.getDrawable(R.drawable.ic_cloud, null)
        ivIcon3?.background = d
        ivIcon3?.apply {
            Log.d("TAG_MainActivity","IVICON HEIGHT: ${ivIcon3?.layoutParams?.height} IVICON TAG: ${ivIcon3?.tag}")
            layoutParams.width = (d.intrinsicWidth.toDouble() / d.intrinsicHeight * layoutParams.height).toInt()
        }

        ivIcon3?.let {
            Log.d(
                "TAGMainActivityIVICON", "WIDTH ${it.layoutParams.width} HEIGHT ${it.layoutParams.height}, " +
                        "INTRINSIC WIDTH ${d.intrinsicWidth}, INTRINSIC HEIGHT ${d.intrinsicHeight}, " +
                        "RESULT: ${d.intrinsicWidth / d.intrinsicHeight * it.tag.toString().toInt()}"
            )
        }
    }

    override fun onResume() {
        super.onResume()

    }
}