package com.abhilash.newshopping.ui.theme.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DynamicScreen(userInput: String,navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(userInput, color = Color.White,fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier.clickable {
                        Toast.makeText(context, userInput, Toast.LENGTH_SHORT).show()

                    }) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("DrawerWithScaffold")
                       // Toast.makeText(context, userInput, Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Outlined.ArrowUpward, contentDescription = "Menu", tint = Color.White)
                    }
                },
                modifier = Modifier.height(100.dp),
                backgroundColor = Color(0xFF0A134D),
                contentColor = Color.White,
                elevation = 8.dp
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {



//            Text("User Input: $userInput", style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier.clickable {
//                    Toast.makeText(context, userInput, Toast.LENGTH_SHORT).show()
//                })
            // Add other UI elements as needed
        }
    }
}

