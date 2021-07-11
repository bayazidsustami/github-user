package com.dicoding.academy.githubuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.academy.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val jsonHelper by lazy { JsonHelper(this) }
    private val viewModel by lazy { MainViewModel(jsonHelper) }

    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding.rvListUser){
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        }

        userAdapter.users = viewModel.getListUser()

        userAdapter.onItemClick = { userItem ->
            val intent = Intent(this, DetailUserActivity::class.java).apply {
                putExtra(DetailUserActivity.DETAIL_EXTRA, userItem)
            }
            startActivity(intent)
        }
    }
}