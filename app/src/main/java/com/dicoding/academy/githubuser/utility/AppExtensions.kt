package com.dicoding.academy.githubuser.utility

import android.content.Context
import android.view.View
import kotlin.math.abs

fun Context.getAvatarId(imageName: String): Int{
    return this.resources.getIdentifier(imageName, "drawable", this.packageName)
}

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

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}