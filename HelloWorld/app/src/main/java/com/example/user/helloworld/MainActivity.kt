package com.example.user.helloworld

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

const val CALCULATOR_FIRST_VALUE = 101
const val CALCULATOR_KEY = "calculator-key"
const val CALCULATOR_SECOND_KEY = "calculator-second-key"
const val CALCULATOR_BUNDLE_KEY = "calculator-bundle-key"

const val PERSON_KEY = "person-key"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculatorButtonClick(view: View) {
        Timber.d("MainActivity_TAG: calculatorButtonClick: ")
        val intent = Intent(this, CalculatorActivity::class.java)
        intent.putExtra(CALCULATOR_SECOND_KEY, Bundle().apply {
            putInt(CALCULATOR_KEY, CALCULATOR_FIRST_VALUE)
            putString(CALCULATOR_BUNDLE_KEY, "SINCE MAIN ACTIVITY")
        })
        startActivity(intent)
    }

    fun activityForResultButtonClick(view: View) {
        Timber.d("MainActivity_TAG: activityForResultButtonClick: ")
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivityForResult(intent,1000)
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

    fun makePhoneCallButtonClick(view: View) {
        Timber.d("MainActivity_TAG: makePhoneCallButtonClick: ")
        checkPermissions()
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
            } else {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8120142536"))
                startActivity(intent)
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

    fun parcelableClick(view: View) {}

    fun showPersonDetailsClick(view: View) {
        Timber.d("MainActivity_TAG: showPersonDetailsClick: ")
        val person = Person().apply {
            name = "Christian"
            age = "30 years"
            position = "Manager"
        }

        val intent = Intent(this, PersonDetailsActivity::class.java)
        intent.putExtra(PERSON_KEY, person)

        startActivity(intent)
    }
}
