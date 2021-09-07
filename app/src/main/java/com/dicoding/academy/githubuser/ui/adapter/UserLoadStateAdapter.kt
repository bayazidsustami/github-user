package com.dicoding.academy.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.academy.githubuser.databinding.ItemFooterLoadingOrErrorBinding

class UserLoadStateAdapter(
    private val retry: () -> Unit
): LoadStateAdapter<UserLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFooterLoadingOrErrorBinding.inflate(inflater, parent, false)
        return LoadStateViewHolder(binding, retry)
    }

    class LoadStateViewHolder(
        private val binding: ItemFooterLoadingOrErrorBinding,
        retry: () -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        init {
            binding.btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState){
            if (loadState is LoadState.Error){
                binding.tvError.text = loadState.error.localizedMessage
            }
            binding.pBar.isVisible = loadState is LoadState.Loading
            binding.btnRetry.isVisible = loadState is LoadState.Error
            binding.tvError.isVisible = loadState is LoadState.Error
        }
    }

}