package com.kotlin.noctumeapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.noctumeapp.models.anime.Data
import com.kotlin.noctumeapp.repository.AnimeRepository
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {
    private val repository = AnimeRepository()
    private val animeData = MutableLiveData<List<Data>>()
    private val animeDetails = MutableLiveData<Data>()

    fun getAnime(): LiveData<List<Data>> {
        viewModelScope.launch {
            try {
                val response = repository.getAllAnime()
                if (response.isSuccessful) {
                    animeData.postValue(response.body()?.data)
                    Log.d("AnimeViewModel", "Anime data fetched successfully")
                } else {
                    Log.e("AnimeViewModel", "Error fetching anime data: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("AnimeViewModel", "Error fetching anime data", e)
            }
        }
        return animeData
    }

    fun getDetailsById(id: Int) : LiveData<Data> {
        viewModelScope.launch {
            try {
                val response = repository.getDetailsById(id)
                if (response.isSuccessful) {
                    animeDetails.postValue(response.body())
                    Log.d("AnimeViewModel", "Anime details fetched successfully")
                } else {
                    Log.e("AnimeViewModel", "Error fetching anime details: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("AnimeViewModel", "Error fetching anime details", e)
            }
        }
        return animeDetails
    }
}