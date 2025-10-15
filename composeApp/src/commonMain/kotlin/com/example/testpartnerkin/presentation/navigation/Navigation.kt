package com.example.testpartnerkin.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.testpartnerkin.presentation.ui.DetailScreenState
import com.example.testpartnerkin.presentation.ui.MainScreenState
import kotlinx.serialization.Serializable

@Serializable
object MainList

@Serializable
data class Details(val id: Int)

@Composable
fun MyNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainList) {
        composable<MainList> {
            MainScreenState(
                onClick = {
                    navController.navigate(Details(it))
                }
            )
        }
        composable<Details> { backStackEntry ->
            val details: Details = backStackEntry.toRoute()
            DetailScreenState(details.id)
        }
    }
}