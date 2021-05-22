package com.example.week15a.api

import com.example.week15a.model.Post
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface APIendPoint {

    @GET("person")
    fun getAllPost(): Call<ListResponse<Post>>

    @GET("person/{id}")
    fun getPostById(@Path("id")id: Int): Call<SingleResponse<Post>>

    @DELETE("person/{id}")
    fun delete(@Path("id")id: Int): Call<Void>
}

data class ListResponse<T>(
    var message: String,
    var status: Int,
    var data: List<T>
)

data class SingleResponse<T>(
    var message: String,
    var status: Int,
    var data: T
)