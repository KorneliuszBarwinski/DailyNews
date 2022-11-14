package com.korneliuszbarwinski.dailynews.presentation.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.korneliuszbarwinski.dailynews.R
import com.korneliuszbarwinski.dailynews.common.Resource
import com.korneliuszbarwinski.dailynews.common.hide
import com.korneliuszbarwinski.dailynews.common.show
import com.korneliuszbarwinski.dailynews.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsBinding.bind(view)
        binding.newsRV.adapter = newsAdapter

        handleApiData()
    }


    private fun handleApiData() {
        viewModel.latestNews.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    newsAdapter.dataSet = it.data!!
                    binding.newsRV.show()
                    binding.newsPB.hide()
                }
                is Resource.Loading -> {
                    binding.newsPB.show()
                    binding.newsRV.hide()
                }
                is Resource.Error -> {
                    binding.newsRV.show()
                    binding.newsPB.hide()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}