package com.dicoding.academy.githubuser.ui.detail

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academy.githubuser.databinding.FragmentUserFollowBinding
import com.dicoding.academy.githubuser.ui.adapter.UserAdapter
import com.dicoding.academy.githubuser.ui.adapter.UserLoadStateAdapter
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment
import com.dicoding.academy.githubuser.utility.gone
import com.dicoding.academy.githubuser.utility.visible
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFollowFragment: BaseFragment<FragmentUserFollowBinding>(
    FragmentUserFollowBinding::inflate
) {

    private val adapter: UserAdapter by inject()
    private val viewModel: UserFollowViewModel by viewModel()

    private var userJob: Job? = null

    override fun initView(savedInstanceState: Bundle?) {
        val index = arguments?.getInt(INDEX, 0)
        val username = arguments?.getString(USERNAME, "")

        with(binding.rvListUser){
            hasFixedSize()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@UserFollowFragment.adapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        binding.rvListUser.adapter = adapter.withLoadStateHeaderAndFooter(
            header = UserLoadStateAdapter{adapter.retry()},
            footer = UserLoadStateAdapter{adapter.retry()}
        )

        if (index != null && username != null){
            getUsers(index, username)
        }

        adapter.addLoadStateListener {loadState ->
            binding.rvListUser.isVisible = loadState.refresh is LoadState.NotLoading
            isShowLoading(loadState.source.refresh is LoadState.Loading)
        }
    }

    private fun getUsers(index: Int, username: String){
        userJob?.cancel()
        userJob = lifecycleScope.launchWhenCreated {
            viewModel.getUserFollow(username, index).collectLatest { result ->
                adapter.submitData(result)
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

    companion object{
        private const val INDEX = "index"
        private const val USERNAME = "username"

        @JvmStatic
        fun newInstance(index: Int, username: String) =
            UserFollowFragment().apply {
                arguments = Bundle().apply {
                    putInt(INDEX, index)
                    putString(USERNAME, username)
                }
            }
    }
}