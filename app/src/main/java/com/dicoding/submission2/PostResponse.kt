package com.dicoding.submission2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostResponse (val login :String, val id: Int, val avatar_url : String


):Parcelable