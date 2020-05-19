package com.example.user.celebritydeathmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var myAdapter: MyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Recycler List View"
        fillRecyclerView()
    }

    private fun fillRecyclerView() {
        val rv = findViewById<RecyclerView>(R.id.rvCelebrity)

        myAdapter = MyRecyclerViewAdapter {
            //Log.d("RecyclerViewActivity_TAG", "fillRecyclerView: onItemClick: $it")
        }
        //myAdapter.itemList = MyData.values().toList()


        rv.adapter = myAdapter
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}
