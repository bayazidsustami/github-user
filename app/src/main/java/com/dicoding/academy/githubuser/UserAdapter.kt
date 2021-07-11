package com.dicoding.academy.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.academy.githubuser.databinding.ItemListUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    internal var users = listOf<UsersItem>()

    internal var onItemClick: ((UsersItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(private val binding: ItemListUserBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindData(data: UsersItem){
            binding.tvName.text = data.name
            binding.tvUsername.text = itemView.context.resources.getString(R.string.username, data.username)
            binding.tvRepositoryCount.text = itemView.context.resources.getString(R.string.repository, data.repository)

            Glide.with(itemView.context)
                .load(itemView.context.getAvatarId(data.avatar))
                .into(binding.ivProfile)

            binding.root.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

}