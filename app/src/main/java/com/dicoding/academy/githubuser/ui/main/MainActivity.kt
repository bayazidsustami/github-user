package com.dicoding.academy.githubuser.ui.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academy.githubuser.databinding.ActivityMainBinding
import com.dicoding.academy.githubuser.ui.adapter.UserAdapter
import com.dicoding.academy.githubuser.ui.adapter.UserLoadStateAdapter
import com.dicoding.academy.githubuser.ui.baseUI.BaseActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private val viewModel: MainViewModel by viewModel()
    private val userAdapter: UserAdapter by inject()

    private var searchJob: Job? = null

    override fun initView(bind: ActivityMainBinding, savedInstanceState: Bundle?) {
        initAdapter()

        val query = "baya"
        searchUser(query)
        initSearch()
    }

    private fun initAdapter(){
        with(binding.rvListUser){
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        }

        binding.rvListUser.adapter = userAdapter.withLoadStateHeaderAndFooter(
            header = UserLoadStateAdapter{userAdapter.retry()},
            footer = UserLoadStateAdapter{userAdapter.retry()}
        )
    }

    private fun initSearch(){
        lifecycleScope.launchWhenCreated {
            userAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.rvListUser.scrollToPosition(0) }
        }
    }

    private fun searchUser(query: String){
        searchJob?.cancel()
        searchJob = lifecycleScope.launchWhenCreated {
            viewModel.searchUser(query).collectLatest {
                userAdapter.submitData(it)
            }
        }
    }
}