package com.abhilash.newshopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abhilash.newshopping.ui.theme.NewShoppingTheme
import com.abhilash.newshopping.ui.theme.temp.IndependentDrawers
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
               // IndependentDrawers()
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




