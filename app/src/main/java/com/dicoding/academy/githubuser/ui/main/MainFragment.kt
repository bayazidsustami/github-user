package com.dicoding.academy.githubuser.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academy.githubuser.databinding.FragmentMainBinding
import com.dicoding.academy.githubuser.ui.adapter.UserAdapter
import com.dicoding.academy.githubuser.ui.adapter.UserLoadStateAdapter
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment
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

    override fun initView(bind: FragmentMainBinding, savedInstanceState: Bundle?) {
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