package com.korneliuszbarwinski.dailynews.presentation.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.korneliuszbarwinski.dailynews.R
import com.korneliuszbarwinski.dailynews.common.Resource
import com.korneliuszbarwinski.dailynews.common.gone
import com.korneliuszbarwinski.dailynews.common.visible
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

        binding.newsSRL.apply {
            setOnRefreshListener {
                isRefreshing = false
                viewModel.refreshNews()
            }
        }

        handleApiData()
    }


    private fun handleApiData() {
        viewModel.latestNews.observe(viewLifecycleOwner) {
            when (it){
                is Resource.Success -> {
                    newsAdapter.dataSet = it.data!!
                    binding.newsRV.visible()
                    binding.newsPB.gone()
                }
                is Resource.Loading -> {
                    binding.newsPB.visible()
                    binding.newsRV.gone()
                }
                is Resource.Error -> {
                    binding.newsRV.visible()
                    binding.newsPB.gone()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}