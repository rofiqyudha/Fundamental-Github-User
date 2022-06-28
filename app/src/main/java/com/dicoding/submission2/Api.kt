package com.dicoding.submission2.api

import com.dicoding.submission2.DetailResponse
import com.dicoding.submission2.PostResponse
import com.dicoding.submission2.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @Headers("Authorization: token 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 ")
    @GET ("search/users")
    fun getSearchUser(
        @Query ("q") query :String
    ): Call <UserResponse>


    @Headers("Authorization: token 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"  )
    @GET ("users/{rofiqyudha}")
    fun getUserDetail(
        @Path("rofiqyudha") rofiqyudha:String
):Call<DetailResponse>

    @Headers("Authorization: token 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"  )
    @GET("users/{rofiqyudha}/followers")
    fun getFollowers(
        @Path("rofiqyudha") rofiqyudha: String
    ):Call<ArrayList<PostResponse>>

    @Headers("Authorization: token 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"  )
    @GET("users/{rofiqyudha}/following")
    fun getFollowing(
        @Path("rofiqyudha") rofiqyudha: String
    ):Call<ArrayList<PostResponse>>


}
