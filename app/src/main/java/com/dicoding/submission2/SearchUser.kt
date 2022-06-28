package com.dicoding.submission2

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUser() : ViewModel() {

    val myPackage = MutableLiveData<ArrayList<PostResponse>>()
    val loading = MutableLiveData<Boolean>()



    fun setUser(query: String) {
        loading.value =true

        RetrofitClient.instance.getSearchUser(query).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    myPackage.value = response.body()?.items
                    loading.value = false
                }

            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.message?.let { Log.d("Failure", it) }

            }
        })
    }

    fun getSearchUser():LiveData<ArrayList<PostResponse>>{
        return myPackage
    }

}


