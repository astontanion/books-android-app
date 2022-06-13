package com.example.books.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.books.BR
import com.example.books.R
import com.example.books.model.Volume
import com.google.gson.Gson
import timber.log.Timber

class SearchListAdapter(diffCallback: DiffUtil.ItemCallback<Volume>): PagingDataAdapter<Volume, SearchListAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.volume_item, parent, false)
        val binding: ViewDataBinding? = DataBindingUtil.bind(rootView)
        return ViewHolder(rootView, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        itemView: View,
        private val binding: ViewDataBinding?): RecyclerView.ViewHolder(itemView) {
        fun bind(volume: Volume?)  {
            binding?.apply {
                setVariable(BR.item, volume)
                executePendingBindings()
            }
        }
    }
}