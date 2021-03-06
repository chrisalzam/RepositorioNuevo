package com.example.user.celebritydeathmatch

import android.app.LauncherActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//import java.util.ArrayList
//import kotlin.collections.ArrayList

class MyRecyclerViewAdapter(
    private val onMyItemClick: (Celebrity) -> Unit
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    //var itemList2: ArrayList<String> = java.util.ArrayList()
    var itemList: List<Celebrity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPosition: TextView = itemView.findViewById(R.id.tvPosition)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val ivImg: ImageView = itemView.findViewById(R.id.ivImg)
    }

    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//        return if (itemList[position].name.startsWith("M")) 1 else 0
        return if (position % 2 == 0) 1 else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = when {
            viewType % 2 == 0 -> LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_layout, parent, false)
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_layout2, parent, false)
        }

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvName.text = itemList[position].name
        holder.tvPosition.text = position.toString()

//        if (position % 2 == 0) {
//            Glide.with(holder.ivImg)
//                .load("https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201809211351")
//                .into(holder.ivImg)
//        } else {
//            Glide.with(holder.ivImg).load("https://i.blogs.es/2b63f8/androidze/1366_2000.jpg")
//                .into(holder.ivImg)
//        }

        Glide.with(holder.ivImg).load(itemList[position].photoUrl).into(holder.ivImg)

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