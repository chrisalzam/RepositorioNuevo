package com.example.iconcardview

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    private var ivIcon2: ImageView? = null
    private var ivIcon3: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivIcon2 = findViewById(R.id.ivIcon2)
        ivIcon2?.setImageDrawable(resources.getDrawable(R.drawable.ic_chess_queen, null))

//        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//
//        val width = displayMetrics.widthPixels
//        val height = displayMetrics.heightPixels

        ivIcon3 = findViewById(R.id.ivIcon3)
        var d = resources.getDrawable(R.drawable.ic_cloud, null)
        ivIcon3?.background = d
        val height = ivIcon3?.layoutParams?.height
        ivIcon3?.layoutParams?.width = (d.intrinsicWidth / d.intrinsicHeight) * height!!

        //ivIcon3?.layoutParams?.width = 250

        //Log.d("TAGMainActivityIVICON","HEIGHT calculated is ${ivIcon3?.layoutParams?.height}")
        //Log.d("TAGMainActivityIVICON","WIDTH calculated is ${ivIcon3?.layoutParams?.width}")


        //Log.d("TAGMainActivityDRAWABLE","WIDTH ${d.intrinsicWidth} HEIGHT ${d.intrinsicHeight}")
        Log.d("TAGMainActivityIVICON","WIDTH ${ivIcon3?.layoutParams?.width} HEIGHT ${ivIcon3?.layoutParams?.height}")

        //val layoutParams = LinearLayout.LayoutParams(100, 100)
        //ivIcon3?.layoutParams = ConstraintLayout.LayoutParams(100, 100)

    }
}