package com.r2devpros.myrestapptest.presentation.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.r2devpros.myrestapptest.R
import com.r2devpros.myrestapptest.model.Store
import kotlinx.android.synthetic.main.recycler_view_item_layout.view.*
import timber.log.Timber

class MyRecyclerViewAdapter(
    private val onMyItemClick: (Store) -> Unit
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    var itemList: List<Store> = emptyList()
        set(value) {
            //Field is the value of the property
            //This property is ItemList
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tvIdStore)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddressStore)
        val tvPhone: TextView = itemView.findViewById(R.id.tvPhoneStore)
        val tvHours: TextView = itemView.findViewById(R.id.tvServiceHoursStore)
        val tvLatitude: TextView = itemView.findViewById(R.id.tvLatitudeStore)
        val tvLongitude: TextView = itemView.findViewById(R.id.tvLongitudeStore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Timber.d("MyRecyclerViewAdapter_TAG: onBindViewHolder: ")
        holder.tvId.text = itemList[position].id
        holder.tvPhone.text = itemList[position].phone
        holder.tvAddress.text = itemList[position].address
        holder.tvHours.text = itemList[position].serviceHoursDescription
        holder.tvLatitude.text = itemList[position].latitude.toString()
        holder.tvLongitude.text = itemList[position].longitude.toString()

//        if (position % 2 == 0) {
//            Glide.with(holder.ivImage)
//                .load("https://www.apple.com/ac/structured-data/images/open_graph_logo.png?201809211351")
//                .into(holder.ivImage)
//        } else {
//            Glide.with(holder.ivImage).load("https://i.blogs.es/2b63f8/androidze/1366_2000.jpg")
//                .into(holder.ivImage)
//        }

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