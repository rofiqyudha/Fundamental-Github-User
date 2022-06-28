package com.dicoding.submission2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersUser : ViewModel() {

    val myFollowers = MutableLiveData<ArrayList<PostResponse>>()
    val loading = MutableLiveData<Boolean>()
    fun setFollowers(rofiqyudha: String) {
        loading.value = true
        RetrofitClient.instance.getFollowers(rofiqyudha).enqueue(object : Callback<ArrayList<PostResponse>> {
            override fun onResponse(call: Call<ArrayList<PostResponse>>, response: Response<ArrayList<PostResponse>>) {
                if (response.isSuccessful)
                    loading.value =false
                myFollowers.postValue(response.body())
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                t.message?.let { Log.d("Failure", it) }
                loading.value = false
            }
        }
        )
    }
    fun getFollowers(): LiveData<ArrayList<PostResponse>> {
        return myFollowers
    }

}
