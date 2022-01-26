package com.example.myshoppinapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesService{
   // @GET("/rest/v2/all")
    @GET("latest?amount=500")
    fun listRates(): Call<RatesList>



}
