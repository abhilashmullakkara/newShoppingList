package com.abhilash.newshopping.ui.theme.testing


import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhilash.newshopping.ui.theme.components.Navigation
import com.abhilash.newshopping.ui.theme.components.saveToFile
import kotlinx.coroutines.launch

@Composable
fun MyApp(){
    Navigation()
   // DrawerWithScaffold(navController = NavController(LocalContext.current))
}

@Composable
fun DrawerWithScaffold(navController: NavController) {
    var inputText by remember { mutableStateOf("") }
    var fileContent by remember { mutableStateOf("") }
    var fileUri by remember { mutableStateOf<Uri?>(null) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var newtext by remember { mutableStateOf("") }
     val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
val context = LocalContext.current
    fileUri = saveToFile(context, "data.txt")
    // Function to show the bottom sheet
//    fun showBottomDrawer() {
//        coroutineScope.launch {
//            bottomSheetState.show()
//        }
//    }

    // Main content layout wrapped in ModalBottomSheetLayout
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            // Bottom drawer content0xFF0E0F14
            Surface(modifier = Modifier.fillMaxSize(),color=Color(0xFF0A134D)) {
                var text by remember { mutableStateOf("") }

                Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                    Row{
                        Text(
                            text = "Enter a name for the list:      ",
                            color = Color(0xFFB0B2D8),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Button(onClick = {
                            newtext = text
                            if(newtext.isNotEmpty())
                            coroutineScope.launch { bottomSheetState.hide()

                                text = ""
                            }
                            else
                                Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
                        }) {
                            Text("Save List ")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = text, // Bind the state to the text field
                        onValueChange = { newText -> text = newText }, // Update state on text change
                        label = { Text("List Name ",color = Color(0xFF5C5D74)) },
                        placeholder = { Text("Type here",color = Color(0xFF5C5D74)) },

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color(0xFFFFB300), // Text color when typing
                            focusedBorderColor = Color(0xFF00897B), // Border color when focused
                            unfocusedBorderColor = Color(0xFF5C5D74), // Border color when not focused
                            cursorColor = Color(0xFF00897B), // Cursor color
                            focusedLabelColor = Color(0xFF00897B), // Label color when focused
                            unfocusedLabelColor = Color(0xFF5C5D74), // Label color when not focused
                            placeholderColor = Color(0xFF5C5D74) // Placeholder text color
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
            }
        }
        }
    )
    {
        // ModalNavigationDrawer outside the ModalBottomSheetLayout
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .width(screenWidth * 0.70f)
                        .padding(1.dp)
                        .background(Color(0xFF848FD3))
                        .fillMaxHeight(),
                    drawerContainerColor = Color(0xFF848FD3),
                ) {
                    Surface(color = Color(0xFF6C76BB), modifier = Modifier.fillMaxHeight()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            item {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF0A134D)
                                    ),
                                    shape = RectangleShape,
                                   // border = BorderStroke(2.dp, color = Color.LightGray),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        //.padding(3.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                            .padding(start = 10.dp)
                                    ) {


                                        Row {
                                            IconButton(
                                                onClick = {
                                                    coroutineScope.launch {
                                                        bottomSheetState.show() // Show bottom drawer
                                                    }
                                                },
                                                modifier = Modifier.align(Alignment.CenterVertically)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ViewList,
                                                    contentDescription = "New List Icon",
                                                    tint = Color(0xFFC9CBE9)
                                                )
                                            }
                                            Text(
                                                text = "NEW LIST",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                color = Color(0xFFC9CBE9),
                                                modifier = Modifier
                                                    .clickable {
                                                        coroutineScope.launch {
                                                            bottomSheetState.show() // Show bottom drawer
                                                        }
                                                    }
                                                    .padding(top = 12.dp)
                                            )
                                        }
                                        HorizontalDivider()
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text("   $newtext", fontSize = 16.sp, color = Color.White)
                                    }
                                }
                            }
                            item {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF0E0F14)
                                    ),
                                    shape = RectangleShape,
                                    // border = BorderStroke(1.dp, color = Color.LightGray),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(400.dp)
                                    //.padding(1.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                    ) {
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(newtext,fontSize = 20.sp,color = Color.White,
                                            modifier = Modifier
                                                .padding(start = 10.dp)
                                                .clickable {
                                                    Toast
                                                        .makeText(
                                                            context,
                                                            "List Name",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                        .show()
                                                })
                                    }
                                }
                            }

                            //
                            item {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF0A134D)
                                    ),
                                    shape = RectangleShape,
                                   // border = BorderStroke(1.dp, color = Color.LightGray),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        //.padding(1.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                    ) {
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Row {
                                            IconButton(
                                                onClick = {
                                                    coroutineScope.launch {
                                                        //bottomSheetState.show() // Show bottom drawer
                                                    }
                                                },
                                                modifier = Modifier.align(Alignment.CenterVertically)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Outlined.AccountBalanceWallet,
                                                    contentDescription = "Budget Icon",
                                                    tint = Color(0xFFC9CBE9)
                                                )
                                            }
                                            Text(
                                                text = "BUDGET",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                color = Color(0xFFC9CBE9),
                                                modifier = Modifier
                                                    .clickable {
                                                        coroutineScope.launch {
                                                            //  bottomSheetState.show() // Show bottom drawer
                                                        }
                                                    }
                                                    .padding(top = 12.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            item {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF0A134D)
                                    ),
                                    shape = RectangleShape,
                                   // border = BorderStroke(2.dp, color = Color.LightGray),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        //.padding(1.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                    ) {
                                        Spacer(modifier = Modifier.height(1.dp))
                                        Row {
                                            IconButton(
                                                onClick = {
                                                    coroutineScope.launch {
                                                      //  bottomSheetState.show() // Show bottom drawer
                                                    }
                                                },
                                                modifier = Modifier.align(Alignment.CenterVertically)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.AccountBalance,
                                                    contentDescription = "Expenses Icon",
                                                    tint = Color(0xFFC9CBE9)
                                                )
                                            }
                                            Text(
                                                text = "EXPENSES",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                color = Color(0xFFC9CBE9),
                                                modifier = Modifier
                                                    .clickable {
                                                        coroutineScope.launch {
                                                            // bottomSheetState.show() // Show bottom drawer
                                                        }
                                                    }
                                                    .padding(top = 12.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            item {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF0A134D)
                                    ),
                                    shape = RectangleShape,
                                   // border = BorderStroke(2.dp, color = Color.LightGray),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        //.padding(1.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                    ) {
                                        Spacer(modifier = Modifier.height(1.dp))
                                        Row {
                                            IconButton(
                                                onClick = {
                                                    coroutineScope.launch {
                                                        //bottomSheetState.show() // Show bottom drawer
                                                    }
                                                },
                                                modifier = Modifier.align(Alignment.CenterVertically)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Outlined.Settings,
                                                    contentDescription = "Settings Icon",
                                                    tint = Color(0xFFC9CBE9)
                                                )
                                            }
                                            Text(
                                                text = "SETTINGS",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                color = Color(0xFFC9CBE9),
                                                modifier = Modifier
                                                    .clickable {
                                                        coroutineScope.launch {
                                                            //bottomSheetState.show() // Show bottom drawer
                                                        }
                                                    }
                                                    .padding(top = 12.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }, scrimColor = Color(0x80000000)
        ) {
            // Main screen content inside the ModalNavigationDrawer layout
            Surface(
                color = Color(0xFF172374),
                modifier = Modifier
                    .fillMaxSize()
                    .height(120.dp)
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Shopping List",color = Color.White, style = MaterialTheme.typography.titleLarge) },
                            navigationIcon = {
                                IconButton(onClick = {
                                    coroutineScope.launch {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                }) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Menu",tint = Color.White)
                                }
                            },
                            modifier = Modifier.height(100.dp),
                            backgroundColor = Color(0xFF0A134D),
                            contentColor = Color.White,
                            elevation = 8.dp
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            color = Color(0xFF0E0F14),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            Column {
                                Text(newtext,fontSize = 20.sp,color = Color.White,
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .clickable {
                                            navigateToDynamicScreen(navController, newtext)
                                            Toast
                                                .makeText(context, "List Name", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                )





                                //last content to be placed
                                BannerAdView()
                            }
                        }
                    }
                )
            }
        }
    }
}


fun navigateToDynamicScreen(navController: NavController, userInput: String) {
    navController.navigate("DynamicScreen/$userInput")
}


