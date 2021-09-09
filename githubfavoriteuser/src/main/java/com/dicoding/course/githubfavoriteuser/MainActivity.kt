package com.dicoding.course.githubfavoriteuser

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.course.githubfavoriteuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object{
        private const val AUTHORITY = "com.dicoding.academy.githubuser"
        private val CONTENT_URI: Uri = Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath("detail_user_entity")
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val providerClient = contentResolver?.acquireContentProviderClient(Uri.parse("content://com.dicoding.academy.githubuser/user"))
            val data = providerClient?.query(CONTENT_URI, null, null, null, null)
            Log.d("DATAS", data?.count.toString())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}