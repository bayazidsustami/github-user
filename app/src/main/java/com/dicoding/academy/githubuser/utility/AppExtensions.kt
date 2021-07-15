package com.dicoding.academy.githubuser.utility

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlin.math.abs

fun Int.reformatNumber(): String {
    return when {
        abs(this / 1000000) > 1 -> {
            "${(this / 1000000)}m"
        }
        abs(this / 1000) > 1 -> {
            "${(this / 1000)}k"
        }
        else -> {
            toString()
        }
    }
}

fun ImageView.showImage(url: String?){
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}