package com.korneliuszbarwinski.dailynews.presentation.news

import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.korneliuszbarwinski.dailynews.R
import com.korneliuszbarwinski.dailynews.common.NewsException
import com.korneliuszbarwinski.dailynews.common.gone
import com.korneliuszbarwinski.dailynews.common.showToast
import com.korneliuszbarwinski.dailynews.common.visible
import com.korneliuszbarwinski.dailynews.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsBinding.bind(view)

        handleBinding()
        inicializeAdapter()
        handleApiData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleBinding(){
        binding.newsRV.adapter = newsAdapter

        binding.newsSRL.apply {
            setOnRefreshListener {
                //There should be logic in ViewModel to refresh currently selected category, for simplification i refresh "top" news and set chip selection to "top" news
                isRefreshing = false
                viewModel.refreshNews()
                binding.apply {
                    topChip.isChecked = true
                    binding.chipViewScroll.fullScroll(ScrollView.FOCUS_UP)
                }
            }
        }

        binding.apply {
            topChip.setOnClickListener {
                viewModel.refreshNews()
            }

            businessChip.setOnClickListener {
                viewModel.getBusinessNews()
            }

            sportChip.setOnClickListener {
                viewModel.getSportNews()
            }

            politicsChip.setOnClickListener {
                viewModel.getPoliticsNews()
            }

            technologyChip.setOnClickListener {
                viewModel.getTechnologyNews()
            }

            beautyChip.setOnClickListener {
                viewModel.getBeautyNews()
            }

        }
    }

    private fun inicializeAdapter(){
        newsAdapter.apply{
            addLoadStateListener { state ->
                when (val loadState = state.source.refresh) {
                    is LoadState.Loading -> showLoader()
                    is LoadState.NotLoading -> hideLoader()
                    is LoadState.Error -> {
                        hideLoader()
                        if (loadState.error is NewsException){
                            loadState.error.message?.let {
                                //Error handling by showing it in Toast is only simplification
                                requireContext().showToast(it)
                                Timber.d(it)
                            }
                        }
                    }
                }
            }

            setOnItemClickListener {
                findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(it))
            }
        }
    }

    private fun handleApiData() {
        viewModel.latestNews.observe(viewLifecycleOwner) {
            newsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun showLoader(){
        binding.newsPB.visible()
    }

    private fun hideLoader(){
        binding.newsPB.gone()
    }
}