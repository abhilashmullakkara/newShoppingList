package com.abhilash.newshopping.ui.theme.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

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
           ListNameAndQuantityInput(
               userText = userInput
           )
           { _, _ ->
               // Handle the saved list name and quantity here

           }

}

        }
    }
}
@Composable
fun ListNameAndQuantityInput(
    userText: String,
    onSave: (String, Int) -> Unit
) {
    val context = LocalContext.current
    val shopDao = AppDatabase.getDatabase(context).shopDao()
    val itemDao = AppDatabase.getDatabase(context).itemDao()
    val checkedStates = remember { mutableStateMapOf<String, Boolean>() }
    val repository = ShopRepository(shopDao, itemDao)
    val shopViewModel: ShopViewModel = viewModel(
        factory = ShopViewModelFactory(repository)
    )

    var listName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var unitPrice by remember { mutableStateOf("") }
    val nameFocusRequester = remember { FocusRequester() }
    val quantityFocusRequester = remember { FocusRequester() }

    // Use a MutableState to hold the list of items
    val itemsState = remember { mutableStateOf<List<ItemEntity>>(emptyList()) }

    // Observe the LiveData and update the MutableState when it changes
    val items by shopViewModel.getItemsByShopName(userText).observeAsState(emptyList())
    LaunchedEffect(items) {
        // Update itemsState directly with ItemEntity
        itemsState.value = items
    }

    val price = remember(quantity, unitPrice) {
        val qty = try {
            quantity.toInt()
        } catch (e: NumberFormatException) {
            0
        }
        val pricePerUnit = try {
            unitPrice.toInt()
        } catch (e: NumberFormatException) {
            0
        }
        qty * pricePerUnit
    }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Input for Item Name
            TextField(
                value = listName,
                onValueChange = { listName = it },
                maxLines = 1,
                label = { Text("Enter Item Name", color = Color(0xFFFB8C00), fontSize = 14.sp) },
                placeholder = { Text("Type here", color = Color.Gray, fontSize = 14.sp) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { quantityFocusRequester.requestFocus() }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    textColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.Gray,
                    errorIndicatorColor = Color.Red,
                    backgroundColor = Color(0xFF040A11)
                ),
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(nameFocusRequester)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Input for Quantity
            TextField(
                value = quantity,
                onValueChange = { quantity = it },
                maxLines = 1,
                label = { Text("Enter Quantity", color = Color(0xFFFB8C00), fontSize = 14.sp) },
                placeholder = { Text("Type here", color = Color.Gray, fontSize = 14.sp) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    textColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.Gray,
                    errorIndicatorColor = Color.Red,
                    backgroundColor = Color(0xFF040A11)
                ),
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(quantityFocusRequester)
            )
        }

        TextField(
            value = unitPrice,
            onValueChange = { unitPrice = it },
            maxLines = 1,
            label = {
                Text(
                    "Enter Unit Price (Optional)",
                    color = Color(0xFFFB8C00),
                    fontSize = 14.sp
                )
            },
            placeholder = { Text("Type here", color = Color.Gray, fontSize = 14.sp) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onNext = { nameFocusRequester.requestFocus() }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                textColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                disabledIndicatorColor = Color.Gray,
                errorIndicatorColor = Color.Red,
                backgroundColor = Color(0xFF040A11)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Price: $price",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    if (listName.isNotEmpty()) {
                        val qty = try {
                            quantity.toInt()
                        } catch (e: NumberFormatException) {
                            0
                        }
                        shopViewModel.addItemToShopByName(
                            userText,
                            listName,
                            qty.toString(),
                            price = price.toDouble()
                        )
                        onSave(userText, qty)
                        listName = ""
                        quantity = ""
                        unitPrice = ""
                        nameFocusRequester.requestFocus()
                    } else {
                        Toast.makeText(context, "Please enter valid details", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF050A27),
                    contentColor = Color.White
                )
            ) {
                Text("Save")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(400.dp),
            elevation = 4.dp,
            backgroundColor = Color(0xFF0E071D)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 5.dp, top = 5.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                LazyColumn {
                    items(itemsState.value) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${item.itemName} - ${item.itemQuantity} ${item.itemPrice}",
                                fontSize = 15.sp,
                                color = Color.White,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.weight(1f)
                            )
                            Checkbox(
                                checked = checkedStates[item.itemName] ?: false,
                                onCheckedChange = { isChecked ->
                                    checkedStates[item.itemName] = isChecked
                                },
                                modifier = Modifier.padding(start = 5.dp, top = 0.dp),
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.Blue,
                                    uncheckedColor = Color.White,
                                    checkmarkColor = Color.White
                                )
                            )
                            TextButton(
                                onClick = {
                                    if (checkedStates[item.itemName] == true) {
                                        // Delete the item from the database
                                        shopViewModel.removeItemByName(item.itemName)
                                        // Update the MutableState to reflect the change immediately
                                        itemsState.value = itemsState.value.filter { it.itemName != item.itemName }
                                        checkedStates[item.itemName] = false
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please check the box to delete ${item.itemName}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                colors = ButtonDefaults.textButtonColors(
                                    containerColor = Color(0xFF0A134D),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(
                                    "Delete",
                                    color = Color.White,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

//@Composable
//fun ListNameAndQuantityInput(
//    userText: String,
//    onSave: (String, Int) -> Unit
//) {
//    val context = LocalContext.current
//    val shopDao = AppDatabase.getDatabase(context).shopDao()
//    val itemDao = AppDatabase.getDatabase(context).itemDao()
//    val checkedStates = remember { mutableStateMapOf<String, Boolean>() }
//    val repository = ShopRepository(shopDao, itemDao)
//    val shopViewModel: ShopViewModel = viewModel(
//        factory = ShopViewModelFactory(repository)
//    )
//    var listName by remember { mutableStateOf("") }
//    var quantity by remember { mutableStateOf("") }
//    var unitPrice by remember { mutableStateOf("") }
//    val nameFocusRequester = remember { FocusRequester() }
//    val quantityFocusRequester = remember { FocusRequester() }
//    val items by shopViewModel.getItemsByShopName(userText).observeAsState(emptyList())
//
//    val price = remember(quantity, unitPrice) {
//        val qty = try {
//            quantity.toInt()
//        } catch (e: NumberFormatException) {
//            0
//        }
//        val pricePerUnit = try {
//            unitPrice.toInt()
//        } catch (e: NumberFormatException) {
//            0
//        }
//        qty * pricePerUnit
//    }
//    val focusManager = LocalFocusManager.current
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(5.dp),
//       // verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Row(modifier = Modifier.fillMaxWidth()) {
//            // Input for Item Name
//            TextField(
//                value = listName,
//                onValueChange = { listName = it },
//                maxLines = 1,
//                label = { Text("Enter Item Name", color = Color(0xFFFB8C00), fontSize = 14.sp) },
//                placeholder = { Text("Type here", color = Color.Gray, fontSize = 14.sp) },
//                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
//                keyboardActions = KeyboardActions(
//                    onNext = { quantityFocusRequester.requestFocus() }
//                ),
//                colors = TextFieldDefaults.textFieldColors(
//                    cursorColor = Color.White,
//                    textColor = Color.White,
//                    focusedIndicatorColor = Color.White,
//                    unfocusedIndicatorColor = Color.White,
//                    disabledIndicatorColor = Color.Gray,
//                    errorIndicatorColor = Color.Red,
//                    backgroundColor = Color(0xFF040A11)
//                ),
//                modifier = Modifier
//                    .weight(1f)  // Makes it take equal space in the Row
//                    .focusRequester(nameFocusRequester)
//            )
//
//            Spacer(modifier = Modifier.width(16.dp)) // Increase the space between the fields
//
//            // Input for Quantity
//            TextField(
//                value = quantity,
//                onValueChange = { quantity = it },
//                maxLines = 1,
//                label = { Text("Enter Quantity", color = Color(0xFFFB8C00), fontSize = 14.sp) },
//                placeholder = { Text("Type here", color = Color.Gray, fontSize = 14.sp) },
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                       // focusManager.clearFocus()
//                    /* Clear focus */ }
//                ),
//                colors = TextFieldDefaults.textFieldColors(
//                    cursorColor = Color.White,
//                    textColor = Color.White,
//                    focusedIndicatorColor = Color.White,
//                    unfocusedIndicatorColor = Color.White,
//                    disabledIndicatorColor = Color.Gray,
//                    errorIndicatorColor = Color.Red,
//                    backgroundColor = Color(0xFF040A11)
//                ),
//                modifier = Modifier
//                    .weight(1f)  // Makes it take equal space in the Row
//                    .focusRequester(quantityFocusRequester)
//            )
//        }
//
//        TextField(
//            value = unitPrice,
//            onValueChange = { unitPrice = it },
//            maxLines = 1,
//            label = {
//                Text(
//                    "Enter Unit Price (Optional)",
//                    color = Color(0xFFFB8C00),
//                    fontSize = 14.sp
//                )
//            },
//            placeholder = { Text("Type here", color = Color.Gray, fontSize = 14.sp) },
//            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//            keyboardActions = KeyboardActions(
//                onNext = { nameFocusRequester.requestFocus() }
//            ),
//            colors = TextFieldDefaults.textFieldColors(
//                cursorColor = Color.White,
//                focusedIndicatorColor = Color.White,
//                unfocusedIndicatorColor = Color.White,
//                disabledIndicatorColor = Color.Gray,
//                errorIndicatorColor = Color.Red,
//                backgroundColor = Color.Transparent
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Row {
//        Text(
//            text = "Price: $price",
//            fontSize = 16.sp,
//            color = Color.White
//        )
//            Spacer(modifier = Modifier.width(8.dp))
//        Button(
//            onClick = {
//                if (listName.isNotEmpty()) {
//                    shopViewModel.addItemToShopByName(
//                        userText,
//                        listName,
//                        quantity,
//                        price = price.toDouble()
//                    )
//                    onSave(
//                        userText, try {
//                            quantity.toInt()
//                        } catch (e: NumberFormatException) {
//                            0
//                        }
//                    )
//                    listName = ""
//                    quantity = ""
//                    unitPrice = ""
//                    nameFocusRequester.requestFocus()
//                } else {
//                    Toast.makeText(context, "Please enter valid details", Toast.LENGTH_SHORT).show()
//                }
//            },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color(0xFF050A27),
//                contentColor = Color.White
//            ),
//           // modifier = Modifier.align(Alignment.End)
//        ) {
//            Text("Save")
//        }
//    }
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(5.dp).height(400.dp),
//            elevation = 4.dp,
//            backgroundColor = Color(0xFF0E071D)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(start=5.dp,top=5.dp),
//                verticalArrangement = Arrangement.spacedBy(2.dp)
//            ) {
//                LazyColumn {
//                    items(items) { item ->
//                       // if(tempItemPrice.isNotEmpty())
//                        Row {
//                            Text(
//                                text = "${item.itemName}-   ${ item.itemQuantity}     ${item.itemPrice}",
//                               // style = MaterialTheme.typography.displaySmall,
//                                fontSize = 15.sp,
//                                color = Color.White,
//                                fontStyle = FontStyle.Italic,
//                                fontWeight = FontWeight.SemiBold,
//                            )
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            // Checkbox for the current item
//                            Checkbox(
//                                checked = checkedStates[item.itemName]
//                                    ?: false, // Default to unchecked
//                                onCheckedChange = { isChecked ->
//                                    checkedStates[item.itemName] = isChecked
//                                },
//                                modifier=Modifier.padding(start=5.dp,top=0.dp),
//                                colors = CheckboxDefaults.colors(
//                                    checkedColor = Color.Blue,
//                                    uncheckedColor = Color.White,
//                                    checkmarkColor = Color.White
//                                )
//                            )
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            // Delete Button
//                            TextButton(
//                                onClick = {
//                                    if (checkedStates[item.itemName] == true) {
//                                        shopViewModel.removeItemByName(item.itemName)
//                                        checkedStates[item.itemName] = false // Reset checkbox state
//                                    } else {
//                                        Toast.makeText(
//                                            context,
//                                            "Please check the box to delete ${item.itemName}",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
//                                    }
//                                },
//                                colors = ButtonDefaults.textButtonColors(
//                                    containerColor = Color(0xFF0A134D),
//                                    contentColor = Color.White,
//                                )
//                            ) {
//                                Text(
//                                    "Delete",
//                                    color = Color.White,
//                                    fontSize = MaterialTheme.typography.titleMedium.fontSize
//                                )
//                            }
//                        }
//                        focusManager.clearFocus()
//
//                        Spacer(modifier = Modifier.height(8.dp))
//                    }
//                }
//            }
//
//
//        }
//    }
//    }






