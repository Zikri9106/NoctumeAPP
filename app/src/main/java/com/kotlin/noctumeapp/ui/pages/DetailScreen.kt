package com.kotlin.noctumeapp.ui.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import com.kotlin.noctumeapp.models.anime.Data
import com.kotlin.noctumeapp.viewmodels.AnimeViewModel

@Composable
fun DetailScreen(id: Int) {
    val viewModel: AnimeViewModel = viewModel()
    val anime: Data? by viewModel.getDetailsById(id).observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getDetailsById(id)
    }
    if (anime != null) {
        Text(text = anime!!.synopsis, fontSize = 20.sp)
    }
}