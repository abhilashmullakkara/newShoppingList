package com.abhilash.newshopping.ui.theme.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhilash.newshopping.ui.theme.testing.DrawerWithScaffold

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "DrawerWithScaffold") {
        composable("DrawerWithScaffold")
        {
            DrawerWithScaffold(navController = navController)
        }
        composable("DynamicScreen/{userInput}") { backStackEntry ->
            val userInput = backStackEntry.arguments?.getString("userInput") ?: "Default"
            DynamicScreen(userInput = userInput,navController)
        }

    }
}