package com.dicoding.academy.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.core.data.dataSource.remote.response.UserItem
import com.dicoding.academy.githubuser.databinding.ItemListUserBinding

class UserAdapter: PagingDataAdapter<UserItem, UserAdapter.ViewHolder>(DIFF_UTIL) {

    internal var onItemClick: ((UserItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = getItem(position)
        if (users != null){
            holder.bindData(users)
        }
    }

    inner class ViewHolder(private val binding: ItemListUserBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindData(data: UserItem){
            binding.tvUsername.text = itemView.context.resources.getString(R.string.username, data.login)
            binding.tvType.text = data.type

            Glide.with(itemView.context)
                .load(data.avatarUrl)
                .centerCrop()
                .into(binding.ivProfile)

            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    companion object{
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<UserItem>(){
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}