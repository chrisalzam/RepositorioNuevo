package com.example.containers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "List View"

        findViewById<Button>(R.id.btnRecyclerView).setOnClickListener {
            intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

        fillListView()
    }

    private fun fillListView() {
        Log.d("MainActivity_TAG", "fillListView: ")

        findViewById<ListView>(R.id.lvPeople).apply {
            adapter = ArrayAdapter(this@MainActivity, R.layout.my_item_layout, MyData.values())

            setOnItemClickListener { adapterView, view, i, l ->
                Log.d(
                    "MainActivity_TAG",
                    "fillListView: adapterView: $adapterView, view: $view, i: $i, l: $l"
                )
                Log.d("MainActivity_TAG", "fillListView: selected Item = ${MyData.values()[i]}")
            }
        }
    }
}
