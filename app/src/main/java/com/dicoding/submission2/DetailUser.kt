package com.dicoding.submission2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUser(application: Application): AndroidViewModel(application) {
    val detail = MutableLiveData<DetailResponse>()

    private var userDao:FavoriteDao?
    private var UserDB:DatabaseUser?

    init {
        UserDB = DatabaseUser.getDatabase(application)
        userDao = UserDB?.favoriteDao()
    }

    fun setUserDetail(rofiqyudha:String){
        RetrofitClient.instance.getUserDetail(rofiqyudha).enqueue(object :Callback<DetailResponse>{
                override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                    if (response.isSuccessful){
                        detail.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }
    fun getUserDetail(): LiveData<DetailResponse>{
        return detail
    }
    fun addToFavorite(login:String,id:Int,avatar_url:String){
      CoroutineScope(Dispatchers.IO).launch {
          var user = FavoriteData (login, id,avatar_url)
          userDao?.addToFavorite(user)
      }
    }
    suspend fun check(id: Int)=userDao?.checkUser(id)

    fun removeFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFavorite(id)
        }
    }
}