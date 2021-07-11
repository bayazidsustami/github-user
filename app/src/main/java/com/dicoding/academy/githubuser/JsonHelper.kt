package com.dicoding.academy.githubuser

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(
    private val context: Context
) {
    private fun getJsonFromFile(fileName: String = "githubuser.json"): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        }catch (e: IOException){
            e.printStackTrace()
            null
        }
    }

    fun getUsers(): List<UsersItem>{
        val list: MutableList<UsersItem> = mutableListOf()

        try {
            val jsonData = JSONObject(getJsonFromFile().toString())
            val listUser = jsonData.getJSONArray("users")

            for (i in 0 until listUser.length()){
                val users = listUser.getJSONObject(i)
                val userName = users.getString("username")
                val name = users.getString("name")
                val avatar = users.getString("avatar")
                val company = users.getString("company")
                val location = users.getString("location")
                val repository = users.getInt("repository")
                val follower = users.getInt("follower")
                val following = users.getInt("following")

                val items = UsersItem(
                    username = userName,
                    name = name,
                    avatar = avatar,
                    company = company,
                    location = location,
                    repository = repository,
                    follower = follower,
                    following = following
                )
                list.add(items)
            }
        }catch (e: JSONException){
            e.printStackTrace()
        }
        return list
    }

}