package com.example.urbandictionaryapp.presentation.base

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

abstract class BaseRVAdapter<IT : Parcelable, IVM : BaseViewModel, VDB : ViewDataBinding>(
    private val listener: (View, IT) -> Unit
) : RecyclerView.Adapter<BaseRVAdapter<IT, IVM, VDB>.ViewHolder>() {
    lateinit var viewModel: IVM
    private lateinit var binding: VDB

    var itemList: List<IVM> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var infinite: Boolean = false

    inner class ViewHolder(val itemBinding: VDB) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: IT, clickListener: (View, IT) -> Unit) {
            itemView.setOnClickListener { clickListener(itemView, item) }
        }
    }

    protected abstract fun inflateDataBinding(inflater: LayoutInflater, container: ViewGroup?): VDB
    protected abstract fun getBindItem(itemViewModel: IVM): IT?

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        inflateDataBinding(LayoutInflater.from(parent.context), parent).run {
            binding = this

            ViewHolder(binding)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Timber.d("BaseRVAdapter_TAG: onBindViewHolder: ")
        try {
            val realPosition = if (infinite) position % itemList.size else position
            holder.itemBinding.setVariable(BR.viewModel, itemList[realPosition])
            getBindItem(itemList[realPosition])?.let {
                holder.bind(it, listener)
            }
            holder.itemBinding.executePendingBindings()
        } catch (e: Exception) {
            Timber.d("BaseRVAdapter_TAG: onBindViewHolder: exception: $e")
        }
    }

    override fun getItemCount(): Int = if (infinite) Integer.MAX_VALUE else itemList.size

    fun addItem(item: IVM) {
        val tempList = itemList.toMutableList().apply {
            add(item)
        }
        itemList = tempList
        notifyDataSetChanged()
    }

    fun addItemFirst(item: IVM) {
        val tempList = itemList.toMutableList().apply {
            add(0, item)
        }
        itemList = tempList
        notifyDataSetChanged()
    }

    fun clear() {
        Timber.d("BaseRVAdapter_TAG: onClear: ")
        itemList = emptyList()
        notifyDataSetChanged()
    }
}