package com.dicoding.academy.githubuser.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(
    fragment: Fragment,
    val username: String): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return UserFollowFragment.newInstance(position, username)
    }
}