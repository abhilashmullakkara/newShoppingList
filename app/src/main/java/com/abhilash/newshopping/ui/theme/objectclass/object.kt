package com.abhilash.newshopping.ui.theme.objectclass

import androidx.compose.runtime.Composable

open class List(
    var name: String = "" // Base class with shared properties
)

class ListName(
    var itemName: String = "",
    var itemPrice: String = "",
    var itemQuantity: String = ""
) : List(name = "") // Inherit from List
