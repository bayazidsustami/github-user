package com.dicoding.academy.githubuser.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.academy.githubuser.R
import com.dicoding.academy.githubuser.data.UsersItem
import com.dicoding.academy.githubuser.databinding.ActivityDetailUserBinding
import com.dicoding.academy.githubuser.utility.getAvatarId
import com.dicoding.academy.githubuser.utility.reformatNumber

class DetailUserActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailUserBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val data = intent.getParcelableExtra<UsersItem>(DETAIL_EXTRA)

        if (data != null){
            supportActionBar?.run {
                setDisplayHomeAsUpEnabled(true)
                title = data.name
            }

            Glide.with(this)
                .load(this.getAvatarId(data.avatar))
                .into(binding.ivProfile)

            binding.tvFollowers.text = resources.getString(R.string.followers, data.follower.reformatNumber())
            binding.tvFollowing.text = resources.getString(R.string.following, data.following.reformatNumber())
            binding.tvRepositoryCount.text = resources.getString(R.string.repository, data.repository)
            binding.tvCompany.text = data.company
            binding.tvAddress.text = data.location
        }

    }

    companion object{
        const val DETAIL_EXTRA = "detail_user"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}