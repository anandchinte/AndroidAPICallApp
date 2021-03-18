package com.example.chinte_downloadimage

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(value = "posts")
    fun getDate(): Call<List<MyDataItem>>
}