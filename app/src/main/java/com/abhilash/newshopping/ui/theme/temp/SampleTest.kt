package com.abhilash.newshopping.ui.theme.temp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.abhilash.newshopping.ui.theme.objectclass.ListName
import com.abhilash.newshopping.ui.theme.objectclass.playUltrasonicSound

@Composable
fun SampleTest(customList: String="") {
    // Create an instance of the derived class
    val listName = ListName(
        itemName = "Milk",
        itemPrice = "20",
        itemQuantity = "2"
    )

    // Access base class property
    listName.name = customList
    playUltrasonicSound()

    // Display the data
        Text(text = "List Name: ${listName.name}",color= Color.White, fontSize = 14.sp)
        Text(text = "Item: ${listName.itemName}, Price: ${listName.itemPrice}, Quantity: ${listName.itemQuantity}",color= Color.White, fontSize = 14.sp)
}
