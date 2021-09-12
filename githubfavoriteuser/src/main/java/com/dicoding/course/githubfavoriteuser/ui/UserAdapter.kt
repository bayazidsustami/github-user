package com.dicoding.course.githubfavoriteuser.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.course.githubfavoriteuser.R
import com.dicoding.course.githubfavoriteuser.data.UserModel
import com.dicoding.course.githubfavoriteuser.databinding.ItemListUserBinding

class UserAdapter: ListAdapter<UserModel, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    private companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserModel>(){
            override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bindData(user)
    }

    class ViewHolder(
        private val binding: ItemListUserBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: UserModel){
            binding.tvType.text = itemView.context.getString(R.string.user)
            binding.tvUsername.text = itemView.context.getString(R.string.username, data.login)
            Glide.with(binding.root)
                .load(data.avatarUrl)
                .into(binding.ivProfile)
        }
    }
}