package com.example.containers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyRecyclerViewAdapter(
    private val onMyItemClick: (String) -> Unit
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    var itemList: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContent: TextView = itemView.findViewById(R.id.tvContent)
        val tvPosition: TextView = itemView.findViewById(R.id.tvPosition)
        val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("MyRecyclerViewAdapter_TAG", "onBindViewHolder: ")
        holder.tvContent.text = itemList[position]
        holder.tvPosition.text = position.toString()

        if (position % 2 == 0) {
            Glide.with(holder.ivImage)
                .load("https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201809211351")
                .into(holder.ivImage)
        } else {
            Glide.with(holder.ivImage).load("https://i.blogs.es/2b63f8/androidze/1366_2000.jpg")
                .into(holder.ivImage)
        }

        //all item click
        holder.itemView.setOnClickListener {
            onMyItemClick(itemList[position])
        }

        //specific Item click
        /*holder.tvPosition.setOnClickListener {
            onMyItemClick(itemList[position])
        }*/
    }
}