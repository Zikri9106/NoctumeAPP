package com.kotlin.noctumeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kotlin.noctumeapp.ui.pages.DetailScreen
import com.kotlin.noctumeapp.ui.theme.HomeScreen
import com.kotlin.noctumeapp.viewmodels.AnimeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home) {
        composable(Screen.Home) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Detail + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let {
                DetailScreen(id = it!!)
            }
        }
    }
}