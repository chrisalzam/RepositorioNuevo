package com.example.user.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PersonDetailsActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvPosition: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        bindControls()
        setData()
    }

    private fun bindControls() {
        tvName = findViewById(R.id.tvPersonName)
        tvAge = findViewById(R.id.tvPersonAge)
        tvPosition = findViewById(R.id.tvPersonPosition)
    }

    private fun setData() {
        val person = intent.getParcelableExtra<Person>(PERSON_KEY)

        person?.let {
            tvName.text = it.name
            tvAge.text = it.age
            tvPosition.text = it.position
        }
    }
}
