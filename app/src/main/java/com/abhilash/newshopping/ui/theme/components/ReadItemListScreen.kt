package com.abhilash.newshopping.ui.theme.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

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
                        navController.popBackStack("DrawerWithScaffold", inclusive = false)
                       // Toast.makeText(context, "PopUpBackStack Invoked", Toast.LENGTH_SHORT).show()
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
               // .padding(1.dp)
        ) {

       Surface(color = Color(0xFF6D7086),modifier = Modifier.fillMaxSize()) {
     //Text(text = "This is the dynamic screen for $userInput", color = Color.White)

           ListNameAndQuantityInput { listName, quantity ->
               // Handle the saved list name and quantity here
               Log.d("ListData", "Saved List: $listName, Quantity: $quantity")
           }

}

        }
    }
}

@Composable
fun ListNameAndQuantityInput(
    onSave: (String, Int) -> Unit
) {
    var listName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var unitPrice by remember { mutableStateOf("") }

    val nameFocusRequester = remember { FocusRequester() }
    val quantityFocusRequester = remember { FocusRequester() }

    val context = LocalContext.current

    val price = remember(quantity, unitPrice) {
        val qty = quantity.toIntOrNull() ?: 0
        val pricePerUnit = unitPrice.toIntOrNull() ?: 0
        qty * pricePerUnit
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Input for List Name
        TextField(
            value = listName,
            onValueChange = { listName = it },
            label = { Text("Enter Item Name") },
            placeholder = { Text("Type your item name here") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { quantityFocusRequester.requestFocus() }
            ),
            colors = TextFieldDefaults.textFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(nameFocusRequester)
        )

        // Input for Quantity
        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Enter Quantity") },
            placeholder = { Text("Type quantity here") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = { /* Clear focus */ }
            ),
            colors = TextFieldDefaults.textFieldColors(),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(quantityFocusRequester)
        )

        // Input for Unit Price
        Text("Optional ...", fontSize = 14.sp, color = Color.LightGray)
        TextField(
            value = unitPrice,
            onValueChange = { unitPrice = it },
            label = { Text("Enter Unit Price (Optional)") },
            placeholder = { Text("Type unit price here") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onNext = { nameFocusRequester.requestFocus() }
            ),
            colors = TextFieldDefaults.textFieldColors(),
            modifier = Modifier.fillMaxWidth()
        )

        // Display Price
        Text(
            text = "Price: $price",
            fontSize = 16.sp,
            color = Color.Black
        )

        // Save Button
        Button(
            onClick = {
                if (listName.isNotEmpty() && quantity.toIntOrNull() != null) {
                    onSave(listName, quantity.toInt())
                    listName = ""
                    quantity = ""
                    unitPrice = ""
                    // Request focus for the listName TextField
                    nameFocusRequester.requestFocus()
                } else {
                    Toast.makeText(context, "Please enter valid details", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF050A27),
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}



