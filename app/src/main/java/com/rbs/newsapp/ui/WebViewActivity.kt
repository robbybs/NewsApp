package com.rbs.newsapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rbs.newsapp.R
import com.rbs.newsapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        val url = intent.getStringExtra(URL).toString()
        with(binding) {
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(url)

            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    val alert = getString(R.string.success_load_web)
                    view.loadUrl(alert)
                }
            }

            webView.webChromeClient = object : WebChromeClient() {
                override fun onJsAlert(
                    view: WebView,
                    url: String,
                    message: String,
                    result: android.webkit.JsResult
                ): Boolean {
                    Toast.makeText(this@WebViewActivity, message, Toast.LENGTH_SHORT).show()
                    result.confirm()
                    return true
                }
            }
        }
    }

    companion object {
        const val URL = "url"
    }
}