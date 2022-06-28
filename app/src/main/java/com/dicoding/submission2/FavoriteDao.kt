package com.dicoding.submission2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoriteDao {
    @Insert
    suspend fun addToFavorite(favorite: FavoriteData)

    @Query("SELECT * FROM fav_user")
    fun getFavorite():LiveData<List<FavoriteData>>

    @Query("SELECT count(*) FROM fav_user WHERE fav_user.id = :id")
    suspend fun checkUser(id:Int):Int

    @Query ("DELETE FROM fav_user WHERE fav_user.id =:id")
    suspend fun removeFavorite(id:Int):Int
}