package com.kotlin.noctumeapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kotlin.noctumeapp.R
import com.kotlin.noctumeapp.Screen
import com.kotlin.noctumeapp.models.anime.Data
import com.kotlin.noctumeapp.ui.components.CardView
import com.kotlin.noctumeapp.viewmodels.AnimeViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val animeViewModel: AnimeViewModel = viewModel()
    val animeList: List<Data> by animeViewModel.getAnime().observeAsState(listOf())
    LaunchedEffect(Unit) {
        animeViewModel.getAnime()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = Padding.huge),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(Spacing.large),
            horizontalAlignment = Alignment.Start,
            contentPadding = PaddingValues(vertical = Padding.huge)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        modifier = Modifier.size(30.dp).clip(RoundedCornerShape(Radius.tiny)),
                        painter = painterResource(id = R.drawable.noctume_logo),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(Spacing.large))
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        text = "Noctume",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            item {
                var query = remember { mutableStateOf("") }
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    shape = RoundedCornerShape(Radius.small),
                    value = query.value,
                    onValueChange = { query.value = it },
                    placeholder = { Text("Search") },
                    singleLine = true,
                    leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = null) },
                    trailingIcon = {
                        if (query.value.isNotEmpty()) {
                            IconButton(onClick = { query.value = "" }) {
                                Icon(Icons.Rounded.Clear, contentDescription = null)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            item {
                Text(
                    modifier = Modifier.wrapContentSize(),
                    text = "Anime",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            items(animeList) { anime ->
                CardView(
                    image = anime.images.jpg.image_url,
                    title = anime.title,
                    description = anime.synopsis,
                    score = anime.score,
                    aired = anime.aired.string,
                    onClick = {
                        navController.navigate(Screen.Detail + "/${anime.mal_id}")
                    }
                )
            }
        }
    }
}
