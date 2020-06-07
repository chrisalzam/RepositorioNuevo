package com.r2devpros.consumerest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NativeCallHelper.makeCall("http://www.mocky.io/v2/5edc521431000080006b21af")
    }
}
