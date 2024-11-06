package com.kotlin.noctumeapp.utils

import com.kotlin.noctumeapp.services.AnimeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.jikan.moe/v4/"
    val animeapi: AnimeService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeService::class.java)
    }
}