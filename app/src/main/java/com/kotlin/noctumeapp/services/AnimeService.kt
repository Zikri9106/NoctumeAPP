package com.kotlin.noctumeapp.services

import com.kotlin.noctumeapp.models.anime.AllAnime
import com.kotlin.noctumeapp.models.anime.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {
    @GET("anime")
    suspend fun getAllAnime(): Response<AllAnime>

    @GET("anime/{anime_id}")
    suspend fun getDetailsById(
        @Path("anime_id") id: Int
    ) : Response<Data>
}