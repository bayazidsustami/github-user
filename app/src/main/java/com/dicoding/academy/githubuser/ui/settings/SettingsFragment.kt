package com.dicoding.academy.githubuser.ui.settings

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.databinding.FragmentSettingsBinding
import com.dicoding.academy.githubuser.ui.baseUI.BaseFragment

class SettingsFragment :BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
) {
    override fun initView(savedInstanceState: Bundle?) {
        binding.viewHeader.run {
            title.text = getString(R.string.settings)
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.container, SettingsPreference())
            .commit()
    }
}