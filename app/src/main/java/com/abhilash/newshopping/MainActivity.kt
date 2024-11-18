package com.abhilash.newshopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.abhilash.newshopping.ui.theme.NewShoppingTheme
import com.abhilash.newshopping.ui.theme.testing.BannerAdView
import com.abhilash.newshopping.ui.theme.testing.DrawerWithScaffold
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MobileAds.initialize(this) {}
        setContent {
            NewShoppingTheme {
               // BannerAdView()
                DrawerWithScaffold()
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    topBar = {
//                        TopAppBar(
//                            title = { Text(text = "Shopping List", fontSize = 16.sp, color = Color.White) },
//                            colors = TopAppBarDefaults.topAppBarColors(  MaterialTheme.colorScheme.primary)
//                        )
//                    }
//                ) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewShoppingTheme {
        Greeting("Android")
    }
}




