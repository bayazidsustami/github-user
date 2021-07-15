package com.dicoding.academy.githubuser.ui.detail

import android.os.Bundle
import android.util.Log
import com.dicoding.academy.githubuser.databinding.FragmentUserFollowBinding
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment

class UserFollowFragment: BaseFragment<FragmentUserFollowBinding>(
    FragmentUserFollowBinding::inflate
) {
    override fun initView(savedInstanceState: Bundle?) {
        val index = arguments?.getInt(INDEX, 0)
        Log.d("INDEX", "$index")
    }

    companion object{
        private const val INDEX = "index"

        @JvmStatic
        fun newInstance(index: Int) =
            UserFollowFragment().apply {
                arguments = Bundle().apply {
                    putInt(INDEX, index)
                }
            }
    }
}