package com.dicoding.academy.githubuser.ui.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding>(val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> B): Fragment() {
    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: B
        get() = _binding as B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = bindingFactory(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}