package com.example.user.helloworld

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculatorButtonClick(view: View) {
        Timber.d("MainActivity_TAG: calculatorButtonClick: ")
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }

    fun activityForResultButtonClick(view: View) {
        Timber.d("MainActivity_TAG: activityForResultButtonClick: ")
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d("MainActivity_TAG: onActivityResult: requestCode: $requestCode, resultCode: $resultCode, data: $data")
    }

    fun implicitIntentButtonClick(view: View) {
        Timber.d("MainActivity_TAG: implicitIntentButtonClick: ")
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    @SuppressLint("MissingPermission")
    fun makePhoneCallButtonClick(view: View) {
        Timber.d("MainActivity_TAG: makePhoneCallButtonClick: ")
        checkPermissions()
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8120142536"))
        startActivity(intent)
    }

    private fun checkPermissions() {
        Timber.d("SplashActivity_TAG: checkPermissions: ")
        PermissionManager.checkPermission(
            context = this,
            permissions = listOf(PermissionManager.Permissions.PHONE_CALL)
        ) { permissionCode, permissionGranted ->
            Timber.d("MainActivity_TAG: checkPermissions: permissionCode: $permissionCode, permissionGranted: $permissionGranted")
            if (!permissionGranted) {
                checkSelfPermission(PermissionManager.Permissions.PHONE_CALL.toString())
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Timber.d("MainActivity_TAG: onRequestPermissionsResult: ")
        makePhoneCallButtonClick(findViewById(R.id.btnMakePhoneCall))
    }
}
