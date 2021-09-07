package com.dicoding.academy.githubuser.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.databinding.FragmentMainBinding
import com.dicoding.academy.githubuser.ui.adapter.UserAdapter
import com.dicoding.academy.githubuser.ui.adapter.UserLoadStateAdapter
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment
import com.dicoding.academy.githubuser.ui.detail.DetailUserFragment
import com.dicoding.academy.githubuser.utility.gone
import com.dicoding.academy.githubuser.utility.visible
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {

    private val viewModel: MainViewModel by viewModel()
    private val adapter: UserAdapter by inject()

    private var searchJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        initAdapter()
        initSearch()
    }

    private fun initAdapter(){
        with(binding.rvListUser){
            hasFixedSize()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MainFragment.adapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        binding.rvListUser.adapter = adapter.withLoadStateHeaderAndFooter(
            header = UserLoadStateAdapter{adapter.retry()},
            footer = UserLoadStateAdapter{adapter.retry()}
        )

        adapter.onItemClick = { item ->
            val bundles = Bundle().apply {
                putString(DetailUserFragment.EXTRA_USERNAME, item.login)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailUserFragment, bundles)
        }

        adapter.addLoadStateListener { loadState ->

            binding.rvListUser.isVisible = loadState.refresh is LoadState.NotLoading
            binding.lavSearch.isVisible = loadState.refresh is  LoadState.NotLoading && adapter.itemCount == 0

            isShowLoading(loadState.source.refresh is LoadState.Loading)

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let { _ ->
                binding.lavSearch.run {
                    playAnimation()
                    setAnimation(R.raw.not_found)
                    visible()
                }
                binding.rvListUser.gone()
            }
        }
    }
    private fun isShowLoading(isShow: Boolean){
        if (isShow){
            binding.viewLoading.apply {
                root.visible()
                tvError.isVisible = false
                btnRetry.isVisible = false
            }
        }else{
            binding.viewLoading.root.gone()
        }
    }

    private fun initSearch(){
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO){
                updateUserSearch()
                true
            }else{
                false
            }
        }

        binding.etSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                updateUserSearch()
                true
            } else{
                false
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvListUser.scrollToPosition(0) }
        }
    }

    private fun searchUser(query: String){
        searchJob?.cancel()
        searchJob = lifecycleScope.launchWhenCreated {
            viewModel.searchUser(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun updateUserSearch(){
        binding.etSearch.text.trim().let {
            if (it.isNotEmpty()){
                searchUser(it.toString())
            }
        }
    }
}