package com.dicoding.academy.githubuser.ui.baseUI

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B: ViewBinding>(val bindingFactory: (LayoutInflater) -> B): AppCompatActivity() {
    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: B
        get() = _binding as B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState : Bundle?)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}