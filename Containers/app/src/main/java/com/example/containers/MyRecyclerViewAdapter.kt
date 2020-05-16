package com.example.containers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("MyRecyclerViewAdapter_TAG", "onBindViewHolder: ")
        holder.tvContent.text = itemList[position]
        holder.tvPosition.text = position.toString()

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