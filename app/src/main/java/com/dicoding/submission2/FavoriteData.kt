package com.dicoding.submission2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "fav_user")
data class FavoriteData(val login:String,@PrimaryKey val id:Int, val avatar_url:String):Serializable
