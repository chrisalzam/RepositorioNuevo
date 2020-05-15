package com.example.datapersistence

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.datapersistence.model.Person
import com.example.datapersistence.repository.room.RoomRepository
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

const val SHARED_PREFERENCES_KEY = "shared-preferences"
const val SHARED_PREFERENCES_TEXT = "shared-preferences-text"

const val REQUEST_CODE_WRITE = 1000
const val REQUEST_CODE_READ = 2000

class MainActivity : AppCompatActivity() {
    //region variables
    private lateinit var etText: EditText
    private lateinit var btnSharedPreference: Button
    private lateinit var tvFromSharedPreferences: TextView

    private lateinit var tvFromFile: TextView

    private lateinit var btnRoom: Button
    private lateinit var tvFromRoom: TextView

    private var filePath = ""
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        @Suppress("DEPRECATION")
        filePath =
            "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/data_persistence.txt"
    }

    override fun onResume() {
        super.onResume()
        bindViews()
        getSharedPreferences()
        readFromFile()
        readFromRoom()
    }

    private fun bindViews() {
        etText = findViewById(R.id.etText)

        btnSharedPreference = findViewById(R.id.btnSharedPreference)
        tvFromSharedPreferences = findViewById(R.id.tvFromSharedPreferences)
        btnSharedPreference.setOnClickListener {
            onSharedPreferencesButtonClick()
        }

        tvFromFile = findViewById(R.id.tvFromFile)

        btnRoom = findViewById(R.id.btnRoom)
        tvFromRoom = findViewById(R.id.tvFromRoom)
        btnRoom.setOnClickListener { onRoomClick() }
    }

    //region Shared Preferences
    private fun onSharedPreferencesButtonClick() {
        val sp =
            applicationContext.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

        with(sp.edit()) {
            putString(SHARED_PREFERENCES_TEXT, etText.text.toString())
            apply()
        }

        getSharedPreferences()
    }

    private fun getSharedPreferences() {
        val sp =
            applicationContext.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

        tvFromSharedPreferences.text =
            sp.getString(SHARED_PREFERENCES_TEXT, getString(R.string.no_stored_data))
    }
    //endregion

    //region File
    fun saveIntoFileButtonClick(view: View) {
        Log.d("Main_TAG", "the view: $view")
        val file = File(filePath)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_WRITE
            )
        } else {
            if (!file.exists()) {
                file.createNewFile()
            }

            file.writeText(etText.text.toString())
            readFromFile()
        }
    }

    private fun readFromFile() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_READ
            )
        } else {

            val file = File(filePath)
            tvFromFile.text = if (!file.exists()) {
                getString(R.string.no_stored_data)
            } else {
                file.readText()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_READ -> readFromFile()
            REQUEST_CODE_WRITE -> saveIntoFileButtonClick(btnFile)
        }

    }
    //endregion

    //region Room
    private fun onRoomClick() {
        Thread(Runnable {
            val rr = RoomRepository(this)

            val person = Person(
                name = "Arturo",
                age = 30,
                position = "La mera vena"
            )

            rr.insertPerson(person)

            readFromRoom()
        }).start()
    }

    private fun readFromRoom() {
        Thread(Runnable {
            val rr = RoomRepository(this)

            val persons = rr.getPersons()
            runOnUiThread {
                tvFromRoom.text = if (persons.isEmpty()) {
                    getString(R.string.no_stored_data)
                } else {
                    persons.first().toString()
                }
            }
        }).start()
    }
    //endregion
}
