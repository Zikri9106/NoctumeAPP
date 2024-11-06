package com.kotlin.noctumeapp.repository

import com.kotlin.noctumeapp.models.anime.AllAnime
import com.kotlin.noctumeapp.models.anime.Data
import com.kotlin.noctumeapp.utils.RetrofitInstance
import retrofit2.Response

class AnimeRepository {

    suspend fun getAllAnime(): Response<AllAnime> {
        return RetrofitInstance.animeapi.getAllAnime()
    }

    suspend fun getDetailsById(id: Int): Response<Data> {
        return RetrofitInstance.animeapi.getDetailsById(id = id)
    }
}