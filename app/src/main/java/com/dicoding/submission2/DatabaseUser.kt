package com.dicoding.submission2

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteData::class],
    version = 1
)

abstract class DatabaseUser :RoomDatabase(){
    companion object{
        var Instance : DatabaseUser? = null

        fun getDatabase(context: Context):DatabaseUser?{
            if (Instance==null){
                synchronized(DatabaseUser::class){
                    Instance=Room.databaseBuilder(context.applicationContext,DatabaseUser::class.java,"databse user").build()
                }
            }
            return Instance
        }
    }

abstract fun favoriteDao(): FavoriteDao
}