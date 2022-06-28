package com.dicoding.submission2

import com.dicoding.submission2.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitClient {

    private const val BASE_URL = "https://api.github.com/ "
    val item_retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    val instance = item_retrofit.create(Api::class.java)
}