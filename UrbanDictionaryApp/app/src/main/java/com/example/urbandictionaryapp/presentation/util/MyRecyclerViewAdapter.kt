package com.example.urbandictionaryapp.presentation.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.urbandictionaryapp.R
import com.example.urbandictionaryapp.model.Definition
import kotlinx.android.synthetic.main.recycler_view_item_layout.view.*
import timber.log.Timber

class MyRecyclerViewAdapter(
    private val onMyItemClick: (Definition) -> Unit
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    var itemList: List<Definition> = emptyList()
        set(value) {
            //Field is the value of the property
            //This property is ItemList
            field = value
            notifyDataSetChanged()
        }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDefinitionId: TextView = itemView.findViewById(R.id.tvId)
        val tvDefinitionWord: TextView = itemView.findViewById(R.id.tvWord)
        val tvDefinition: TextView = itemView.findViewById(R.id.tvDefinition)
        val tvDefinitionPermalink: TextView = itemView.findViewById(R.id.tvPermalink)
        val tvDefinitionThumbsUp: TextView = itemView.findViewById(R.id.tvThumbsUp)
        val tvSoundURL: TextView = itemView.findViewById(R.id.tvSoundsURL)
        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        val tvCurrentVote: TextView = itemView.findViewById(R.id.tvCurrentVote)
        val tvWrittenOn: TextView = itemView.findViewById(R.id.tvWrittenOn)
        val tvExample: TextView = itemView.findViewById(R.id.tvExample)
        val tvThumbsDown: TextView = itemView.findViewById(R.id.tvThumbsDown)

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
        //holder.tvDefinitionId.text = itemList[position].id.toString()
        holder.tvDefinitionWord.text = itemList[position].word
        holder.tvDefinition.text = itemList[position].definition
        //holder.tvDefinitionPermalink.text = itemList[position].permalink
        holder.tvDefinitionThumbsUp.text = itemList[position].thumbsUp.toString()
        //holder.tvSoundURL.text = itemList[position].soundUrls.toString()
        holder.tvAuthor.text = "by ${itemList[position].author}"
        //holder.tvCurrentVote.text = itemList[position].currentVote
        //holder.tvWrittenOn.text = itemList[position].writtenOn
        holder.tvExample.text = "For example: \n${itemList[position].example}"
        holder.tvThumbsDown.text = itemList[position].thumbsDown.toString()

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