package ar.com.wolox.android.example.network

import retrofit2.Call
import retrofit2.http.GET

interface NewsServices {
    @GET("/news")
    fun getAllNews(): Call<ArrayList<News>>
}