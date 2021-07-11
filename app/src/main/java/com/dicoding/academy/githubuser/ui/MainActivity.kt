package com.dicoding.academy.githubuser.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academy.githubuser.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModel()
    private val userAdapter: UserAdapter by inject()

    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.rvListUser){
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        }

        /*userAdapter.onItemClick = { userItem ->
            val intent = Intent(this, DetailUserActivity::class.java).apply {
                putExtra(DetailUserActivity.DETAIL_EXTRA, userItem)
            }
            startActivity(intent)
        }*/
        val query = "baya"
        searchUser(query)
        initSearch()
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