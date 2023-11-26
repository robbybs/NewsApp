package com.rbs.newsapp.ui.source_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rbs.newsapp.data.model.SourcesItem
import com.rbs.newsapp.databinding.SourceItemBinding

class SourceAdapter : ListAdapter<SourcesItem, SourceAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            SourceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class MyViewHolder(private val binding: SourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SourcesItem) {
            with(binding) {
                tvName.text = data.name
                tvDescription.text = data.description
                tvUrl.text = data.url
                tvCategory.text = data.category

                val sourcesId = data.id.toString()
                cvSource.setOnClickListener { onItemClickCallback?.onItemClicked(sourcesId) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SourcesItem>() {
            override fun areItemsTheSame(oldItem: SourcesItem, newItem: SourcesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SourcesItem, newItem: SourcesItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}