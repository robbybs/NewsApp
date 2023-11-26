package com.rbs.newsapp.ui.article_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbs.newsapp.databinding.ActivityArticleBinding
import com.rbs.newsapp.ui.WebViewActivity
import com.rbs.newsapp.utils.VMFactoryWithData

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sources = intent.getStringExtra(SOURCES).toString()
        val viewModel: ArticleViewModel by viewModels {
            VMFactoryWithData(sources)
        }

        setAdapter(viewModel)
    }

    private fun setAdapter(viewModel: ArticleViewModel) {
        val adapter = ArticleAdapter()
        with(binding) {
            rvArticle.layoutManager = LinearLayoutManager(this@ArticleActivity)
            rvArticle.adapter = adapter
        }

        with(viewModel) {
            getArticles()
            articles.observe(this@ArticleActivity) {
                adapter.submitList(it)
            }
        }

        adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                startActivity(
                    Intent(this@ArticleActivity, WebViewActivity::class.java).putExtra(
                        WebViewActivity.URL,
                        data
                    )
                )
            }
        })
    }

    companion object {
        const val SOURCES = "sources"
    }
}