package ar.com.wolox.android.example.network

import retrofit2.Call
import retrofit2.http.GET

interface NewsServices {
    @GET("/news?_sort=createdAt&_order=desc")
    fun getAllNews(): Call<List<News>>

    @GET("/news?_sort=createdAt&_order=desc")
    fun getOlderNews(): Call<List<News>>
}