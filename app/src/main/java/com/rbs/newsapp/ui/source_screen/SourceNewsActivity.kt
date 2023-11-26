package com.rbs.newsapp.ui.source_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbs.newsapp.databinding.ActivitySourceNewsBinding
import com.rbs.newsapp.ui.article_screen.ArticleActivity
import com.rbs.newsapp.utils.VMFactoryWithData

class SourceNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySourceNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra(CATEGORY).toString()
        val viewModel: SourceNewsViewModel by viewModels {
            VMFactoryWithData(category)
        }

        setAdapter(viewModel)
    }

    private fun setAdapter(viewModel: SourceNewsViewModel) {
        val adapter = SourceAdapter()
        with(binding) {
            rvSource.layoutManager = LinearLayoutManager(this@SourceNewsActivity)
            rvSource.adapter = adapter
        }

        with(viewModel) {
            getSources()
            sources.observe(this@SourceNewsActivity) {
                adapter.submitList(it)
            }
        }

        adapter.setOnItemClickCallback(object : SourceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                startActivity(
                    Intent(this@SourceNewsActivity, ArticleActivity::class.java).putExtra(
                        ArticleActivity.SOURCES,
                        data
                    )
                )
            }
        })
    }

    companion object {
        const val CATEGORY = "category"
    }
}