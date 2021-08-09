package com.artivac

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://run.mocky.io/"


interface CountryInterface {
    @GET("v3/c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    fun getCountryData(@Query("page") page: Int): Call<Title>
}

