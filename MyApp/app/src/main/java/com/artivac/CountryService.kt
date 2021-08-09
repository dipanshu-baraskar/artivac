package com.artivac

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryService {
    val countryInstance: CountryInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        countryInstance = retrofit.create(CountryInterface::class.java)
    }
}