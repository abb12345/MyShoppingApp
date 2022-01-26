package com.example.myshoppinapp

import com.google.gson.JsonElement
import myData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface apiInterface {

    @GET("latest")
    fun getData() : Call<myData>

    @GET("latest?")
    //Single<JsonElement> getMovieList(@Query("movie_lang") String userLanguage);
    open fun getDatabyamount(@Query("amount") amount: Double?,
                             @Query("base") base: String?
    ): Call<myData>
   // fun getDatabyamount(@Path("amount") amount :Double ) : Call<myData>
}