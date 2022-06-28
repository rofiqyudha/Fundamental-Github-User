package com.dicoding.submission2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FavoriteUser (application: Application): AndroidViewModel(application) {
    private var userDao:FavoriteDao?
    private var UserDB:DatabaseUser?

    init {
        UserDB = DatabaseUser.getDatabase(application)
        userDao = UserDB?.favoriteDao()
    }
    fun getFavorite(): LiveData<List<FavoriteData>>? {
        return userDao?.getFavorite()
    }

}