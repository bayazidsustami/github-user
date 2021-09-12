package com.dicoding.academy.githubuser.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.core.common.Result
import com.dicoding.academy.githubuser.core.domain.model.DetailUserUIModel
import com.dicoding.academy.githubuser.databinding.FragmentDetailUserBinding
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment
import com.dicoding.academy.githubuser.utility.reformatNumber
import com.dicoding.academy.githubuser.utility.showImage
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserFragment: BaseFragment<FragmentDetailUserBinding>(
    FragmentDetailUserBinding::inflate
) {

    private val viewModel: DetailUserViewModel by viewModel()

    private var isFavorite = false

    private var detailUser: DetailUserUIModel? = null

    override fun initView(savedInstanceState: Bundle?) {
        val username = arguments?.getString(EXTRA_USERNAME)
        if (username != null){
            viewModel.requestUserDetail(username)
            viewModel.setUserId(username)
            initViewPager(username)
        }

        viewModel.getUserDetail.observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    Log.d("RESPONSE", "LOADING")
                }
                is Result.Error -> {
                    showSnackBar(result.message)
                }
                is Result.Success -> {
                    populateDetailUser(result.data)
                }
            }
        }

        observeIsFavorite()

    }

    private fun populateDetailUser(detail: DetailUserUIModel){
        detailUser = detail
        binding.apply {
            ivProfile.showImage(detail.avatarUrl)

            tvRepositoryCount.text = resources.getString(R.string.repository, detail.publicRepos)
            tvFollowers.text = resources.getString(R.string.followers, detail.followers?.reformatNumber())
            tvFollowing.text = resources.getString(R.string.following, detail.following?.reformatNumber())

            tvAddress.isVisible = !detail.location.isNullOrEmpty()
            tvCompany.isVisible = !detail.company.isNullOrEmpty()

            tvCompany.text = detail.company
            tvAddress.text = detail.location

            with(viewHeader){
                title.text = resources.getString(R.string.user_detail)
                toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
            }
        }

    }

    private fun observeIsFavorite(){
        viewModel.isFavoriteUser.observe(viewLifecycleOwner, {
            setButton(it)
            isFavorite = it

            binding.fabFavorite.setOnClickListener {
                isFavorite = !isFavorite
                setButton(isFavorite)
                setSnackBar(isFavorite)
                detailUser?.let { details->
                    details.isFavorite = isFavorite
                    viewModel.saveUser(details)
                }
                if (!isFavorite){
                    findNavController().navigateUp()
                }
            }
        })
    }

    private fun setButton(isFavorite: Boolean){
        if (isFavorite){
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
        }else{
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unfavorite))
        }
    }

    private fun setSnackBar(isFavorite: Boolean){
        if (isFavorite){
            showSnackBar("Favorite added")
        }else{
            showSnackBar("Favorite removed")
        }
    }

    private fun showSnackBar(text: String?){
        if (text != null) {
            Snackbar.make(binding.rootCoordinator, text, Snackbar.LENGTH_SHORT).show()
        }
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