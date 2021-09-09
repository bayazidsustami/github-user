package com.dicoding.academy.githubuser.ui.favoite

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.databinding.FragmentFavoriteBinding
import com.dicoding.academy.githubuser.ui.adapter.UserAdapter
import com.dicoding.academy.githubuser.ui.adapter.UserLoadStateAdapter
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment
import com.dicoding.academy.githubuser.ui.detail.DetailUserFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
) {

    private val viewModel: FavoriteViewModel by viewModel()

    private val adapter: UserAdapter by inject()

    override fun initView(savedInstanceState: Bundle?) {
        with(binding.rvListUser){
            hasFixedSize()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FavoriteFragment.adapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        binding.rvListUser.adapter = adapter.withLoadStateHeaderAndFooter(
            header = UserLoadStateAdapter{adapter.retry()},
            footer = UserLoadStateAdapter{adapter.retry()}
        )

        adapter.onItemClick = { item ->
            val bundles = Bundle().apply {
                putString(DetailUserFragment.EXTRA_USERNAME, item)
            }
            findNavController().navigate(R.id.action_favoriteFragment_to_detailUserFragment, bundles)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.favoriteUser.collectLatest {
                adapter.submitData(it)
            }
        }

        binding.viewHeader.run {
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            title.text = getString(R.string.favorite)
        }

    }

}