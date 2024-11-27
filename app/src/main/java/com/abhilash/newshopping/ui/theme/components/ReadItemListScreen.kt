package com.abhilash.newshopping.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ReadItemListScreen(navController: NavController) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Text(
            text = "Read Item List Screen",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun DynamicScreen(userInput: String) {
        Column(modifier = Modifier.padding(16.dp))
        {
            Text(" $userInput", style = MaterialTheme.typography.titleLarge)
            // Add other UI elements as needed
        }
    }

