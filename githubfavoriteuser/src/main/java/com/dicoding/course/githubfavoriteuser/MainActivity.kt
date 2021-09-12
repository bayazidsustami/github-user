package com.dicoding.course.githubfavoriteuser

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.course.githubfavoriteuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object{
        private const val AUTHORITY = "com.dicoding.academy.githubuser.provider"
        private val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/detail_user_entity")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val providerClient = contentResolver?.acquireContentProviderClient(CONTENT_URI)
        try {
            val cursor = providerClient?.query(CONTENT_URI,
                null,
                null,
                null,
                null)
            val name = cursor?.count
            Log.d("DATAS", name.toString())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}