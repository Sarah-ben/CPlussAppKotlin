package com.example.macpro.myapp

import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("index/")
    fun getdata() : Call<ArrayList<data>>
}