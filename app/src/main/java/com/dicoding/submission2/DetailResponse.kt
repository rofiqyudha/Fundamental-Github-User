package com.dicoding.submission2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailResponse (
    val login: String,
    val avatar_url: String,
    val followers_url:String,
    val following_url: String,
    val name:String,
    val company:String,
    val blog: String,
    val location:String,
    val public_repos:String,
    val public_geits:String,
    val followers:String,
    val following:String,

    ): Parcelable