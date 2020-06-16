package com.example.iconcardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView

private var ivIcon1: ImageView? = null
private var cvIcon3: CardView? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivIcon1 = findViewById(R.id.ivIcon2)
        cvIcon3 = findViewById(R.id.cdIcon3)
        ivIcon1?.setImageDrawable(resources.getDrawable(R.drawable.ic_chess_queen))
        cvIcon3?.setBackgroundResource(R.drawable.ic_heart)
    }
}