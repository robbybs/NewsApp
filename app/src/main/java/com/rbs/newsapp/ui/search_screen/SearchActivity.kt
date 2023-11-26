package com.rbs.newsapp.ui.search_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbs.newsapp.R
import com.rbs.newsapp.databinding.ActivitySearchBinding
import com.rbs.newsapp.ui.WebViewActivity
import com.rbs.newsapp.utils.LoadingStateAdapter
import com.rbs.newsapp.utils.VMFactoryWithoutData

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels {
        VMFactoryWithoutData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val query = binding.tfSearch.editText?.text?.trim().toString()
            if (query.isEmpty()) {
                Toast.makeText(
                    this,
                    resources.getString(R.string.no_search),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                searchData(query)
            }
        }
    }

    private fun searchData(query: String) {
        setAdapter()

        viewModel.search(query).observe(this) {
            adapter.submitData(lifecycle, it)
        }

        adapter.addLoadStateListener {
            if (it.append.endOfPaginationReached) {
                if (adapter.itemCount < 1)
                    Toast.makeText(
                        this,
                        resources.getString(R.string.no_data),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }

        adapter.setOnItemClickCallback(object : SearchAdapter.OnItemClickCallback {
            override fun onItemClicked(data: String) {
                startActivity(
                    Intent(this@SearchActivity, WebViewActivity::class.java).putExtra(
                        WebViewActivity.URL,
                        data
                    )
                )
            }
        })
    }

    private fun setAdapter() {
        adapter = SearchAdapter()
        with(binding) {
            rvArticle.layoutManager = LinearLayoutManager(this@SearchActivity)
            rvArticle.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter()
            )
        }
    }
}