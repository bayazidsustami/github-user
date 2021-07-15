package com.dicoding.academy.githubuser.ui.detail

import android.os.Bundle
import android.util.Log
import com.dicoding.academy.githubuser.databinding.FragmentDetailUserBinding
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment
import com.dicoding.academy.githubuser.utility.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserFragment: BaseFragment<FragmentDetailUserBinding>(
    FragmentDetailUserBinding::inflate
) {

    private val viewModel: DetailUserViewModel by viewModel()

    override fun initView(savedInstanceState: Bundle?) {
        val username = arguments?.getString(EXTRA_USERNAME)
        if (username != null){
            viewModel.requestUserDetail(username)
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
                    val data = result.data
                    Log.d("RESPONSE", data.toString())
                }
            }
        }
    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }
}