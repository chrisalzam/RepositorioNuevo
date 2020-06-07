package com.r2devpros.consumerest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        NativeCallHelper.makeCall("http://www.mocky.io/v2/5edc521431000080006b21af")

        val client = RetrofitHelper.createOkHttpClient()
        val serverAPI = RetrofitHelper.createWebService<ServerAPI>(
            okHttpClient = client,
            url = "http://www.mocky.io/"
        )

        GlobalScope.launch {
            val response = serverAPI.getOtherAsync().await()

            Log.d("MainActivity_TAG", "onCreate: response: $response")
        }
    }
}
