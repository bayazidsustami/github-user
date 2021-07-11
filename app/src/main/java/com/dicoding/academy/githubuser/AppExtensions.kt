package com.dicoding.academy.githubuser

import android.content.Context
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