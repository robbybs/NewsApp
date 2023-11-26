package com.rbs.newsapp.ui.article_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rbs.newsapp.R
import com.rbs.newsapp.data.model.ArticlesItem
import com.rbs.newsapp.databinding.ArticleItemBinding

class ArticleAdapter : ListAdapter<ArticlesItem, ArticleAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class MyViewHolder(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticlesItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .apply(RequestOptions().override(70, 70))
                    .into(ivArticle)

                if (data.author.isNullOrEmpty()) tvAuthor.text =
                    itemView.resources.getString(R.string.unknown) else tvAuthor.text = data.author

                tvTitle.text = data.title
                if (data.description.isNullOrEmpty()) itemView.resources.getString(R.string.no_description) else tvDescription.text =
                    data.description

                val url = data.url.toString()
                cvSource.setOnClickListener { onItemClickCallback?.onItemClicked(url) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.author == newItem.author
            }
        }
    }
}