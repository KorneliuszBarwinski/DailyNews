package com.korneliuszbarwinski.dailynews.presentation.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.korneliuszbarwinski.dailynews.R
import com.korneliuszbarwinski.dailynews.databinding.FragmentNewsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment(R.layout.fragment_news_details) {
    private val args: NewsDetailsFragmentArgs by navArgs()
    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding: FragmentNewsDetailsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsDetailsBinding.bind(view)

        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}