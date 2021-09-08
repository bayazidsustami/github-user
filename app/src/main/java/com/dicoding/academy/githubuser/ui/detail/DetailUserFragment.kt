package com.dicoding.academy.githubuser.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import com.dicoding.academy.githubuser.databinding.FragmentDetailUserBinding
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment
import com.dicoding.academy.githubuser.utility.Result
import com.dicoding.academy.githubuser.utility.reformatNumber
import com.dicoding.academy.githubuser.utility.showImage
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserFragment: BaseFragment<FragmentDetailUserBinding>(
    FragmentDetailUserBinding::inflate
) {

    private val viewModel: DetailUserViewModel by viewModel()

    override fun initView(savedInstanceState: Bundle?) {
        val username = arguments?.getString(EXTRA_USERNAME)
        if (username != null){
            viewModel.requestUserDetail(username)
            initViewPager(username)
        }

        viewModel.getUserDetail.observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    Log.d("RESPONSE", "LOADING")
                }
                is Result.Error -> {
                    Log.d("RESPONSE", "ERROR")
                }
                is Result.Success -> {
                    populateDetailUser(result.data)
                }
            }
        }

    }

    private fun populateDetailUser(detail: DetailUserUIModel){
        binding.ivProfile.showImage(detail.avatarUrl)

        binding.tvRepositoryCount.text = resources.getString(R.string.repository, detail.publicRepos)
        binding.tvFollowers.text = resources.getString(R.string.followers, detail.followers?.reformatNumber())
        binding.tvFollowing.text = resources.getString(R.string.following, detail.following?.reformatNumber())

        binding.tvAddress.isVisible = !detail.location.isNullOrEmpty()
        binding.tvCompany.isVisible = !detail.company.isNullOrEmpty()

        binding.tvCompany.text = detail.company
        binding.tvAddress.text = detail.location

        with(binding.viewHeader){
            title.text = resources.getString(R.string.user_detail)
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        }

        var favorite = false
        binding.fabFavorite.setOnClickListener {
            favorite = !favorite
            setFavorite(favorite)
        }

    }

    private fun setFavorite(isFavorite: Boolean){
        if (isFavorite){
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
            showSnackBar("Favorite added")
        }else{
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unfavorite))
            showSnackBar("Favorite remove")
        }
    }

    private fun showSnackBar(text: String){
        Snackbar.make(binding.rootCoordinator, text, Snackbar.LENGTH_SHORT).show()
    }

    private fun initViewPager(username: String){
        val sectionAdapter = SectionPagerAdapter(this, username)
        binding.vpFollow.adapter = sectionAdapter

        TabLayoutMediator(binding.tlFollow, binding.vpFollow){tabs, position ->
            tabs.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following
        )
    }
}