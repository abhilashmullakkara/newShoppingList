package com.abhilash.newshopping.ui.theme.testing


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DrawerWithScaffold() {
//
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val bottomSheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = true // Optional: Skips partially expanded state
//    )
//
//    // val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
//    val coroutineScope = rememberCoroutineScope()
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//
//    // Bottom Sheet Content
//    fun showBottomDrawer() {
//        coroutineScope.launch {
//            bottomSheetState.show()
//        }
//    }
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            ModalDrawerSheet(
//                modifier = Modifier
//                    .width(screenWidth * 0.70f)
//                    .padding(16.dp)  // Optional padding for the drawer content
//            ) {
//                Surface(color = Color(0xFF6C76BB)) {
//                    LazyColumn(
//                        modifier = Modifier.fillMaxWidth() // Ensure it takes up the full width
//                    ) {
//                        item {
//                            Card(
//                                colors = CardDefaults.cardColors(
//                                    containerColor = Color(0xFF2E077E),
//                                ),
//                                shape = RectangleShape,
//                                border = BorderStroke(2.dp, color = Color.LightGray),
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    //.width(screenWidth * 0.90f) // Width of the card
//                                    .height(400.dp)  // Adjust height for better visibility
//                                    .padding(3.dp)  // Padding around the card content
//                            ) {
//                                Column(
//                                    modifier = Modifier
//                                        .fillMaxWidth() // Ensure the content inside the card takes the full width
//                                        .height(150.dp)  // Adjust height for better visibility .padding(8.dp)  // Padding for the content inside the column
//                                ) {
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                    Row{
//                                        IconButton(onClick = {
//                                            coroutineScope.launch {
//                                                bottomSheetState.show()
//                                              //  drawerState.close() // Close the drawer when clicked
//                                            }
//                                        },modifier = Modifier.align(Alignment.CenterVertically)) {
//
//                                            Icon(
//                                                imageVector = Icons.AutoMirrored.Filled.ViewList,
//                                                contentDescription = "New List Icon",
//                                                tint = Color(0xFFC9CBE9),
//                                               // modifier = Modifier.padding(end = 8.dp)
//                                            )
//                                        }
//                                        Text(
//                                            text = "NEW LIST",
//                                            style = MaterialTheme.typography.titleMedium,
//                                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                                            color = Color(0xFFC9CBE9),  // Text color
//                                            modifier = Modifier
//                                                .clickable {
//                                                    // Handle click, e.g., navigate to a new screen
//                                                    coroutineScope.launch {
//                                                        bottomSheetState.show()
//                                                       // drawerState.close()  // Close the drawer on click
//                                                    }
//                                                }
//                                                .padding(top = 12.dp)
//                                        )
//                                    }
//                                }
//                            }
//                        }
//
//                        item {
//                            Surface(
//                                color = Color(0xFF2E077E),
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//                                Row {
//                                    IconButton(onClick = {
//                                        coroutineScope.launch {
//                                            drawerState.close() // Close the drawer when clicked
//                                        }
//                                    },modifier = Modifier.align(Alignment.CenterVertically)) {
//
//                                        Icon(
//                                            imageVector = Icons.Outlined.AccountBalanceWallet,
//                                            contentDescription = "Budgeting Icon",
//                                            tint = Color.White
//                                        )
//
//                                    }
//
//                                Text(
//                                    "BUDGET", modifier = Modifier
//                                        .padding(vertical = 8.dp)
//                                        .clickable {
//                                            coroutineScope.launch {
//                                                drawerState.close()
//                                            }
//                                        }, fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
//                                    color = Color(0xFFC9CBE9)
//                                )
//                            }
//                        }
//
//
//                        }
//                        item {
//                            Surface(
//                                color = Color(0xFF2E077E),
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//                                Row {
//                                    IconButton(onClick = {
//                                        coroutineScope.launch {
//                                            drawerState.close() // Close the drawer when clicked
//                                        }
//                                    },modifier = Modifier.align(Alignment.CenterVertically)) {
//
//                                        Icon(
//                                            imageVector = Icons.Filled.ShoppingCart,
//                                            contentDescription = "Expenses Icon",
//                                            tint = Color.White
//                                        )
//
//                                    }
//
//                                    Text(
//                                        "EXPENSES", modifier = Modifier
//                                            .padding(vertical = 8.dp)
//                                            .clickable {
//                                                coroutineScope.launch {
//                                                    drawerState.close()
//                                                }
//                                            }, fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
//                                        color = Color(0xFFC9CBE9)
//                                    )
//                                }
//                            }
//                        }
//                        item {
//                            Surface(
//                                color = Color(0xFF2E077E),
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//                                Row {
//                                    IconButton(onClick = {
//                                        coroutineScope.launch {
//                                            drawerState.close() // Close the drawer when clicked
//                                        }
//                                    },modifier = Modifier.align(Alignment.CenterVertically)) {
//
//                                        Icon(
//                                            imageVector = Icons.Filled.Settings,
//                                            contentDescription = "Settings Icon",
//                                            tint = Color.White
//                                        )
//
//                                    }
//
//                                    Text(
//                                        "SETTINGS", modifier = Modifier
//                                            .padding(vertical = 8.dp)
//                                            .clickable {
//                                                coroutineScope.launch {
//                                                    drawerState.close()
//                                                }
//                                            }, fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
//                                        color = Color(0xFFC9CBE9)
//                                    )
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
//    ) {
//        Surface(color =  Color(0xFF3A468F), modifier = Modifier.fillMaxSize()) {
//            Scaffold(
//                topBar = {
//                    TopAppBar(
//
//                        title = { Text("Shopping List") },
//                        navigationIcon = {
//                            IconButton(onClick = {
//                                coroutineScope.launch {
//                                    if (drawerState.isClosed) {
//                                        drawerState.open()
//                                    } else {
//                                        drawerState.close()
//                                    }
//                                }
//                            }) {
//                                Icon(
//                                    Icons.Filled.Menu,
//                                    contentDescription = "Menu")
//                            }
//                        },
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = Color(0xFF3A468F), // Set your desired background color
//                            titleContentColor = Color.White, // Text color
//                            navigationIconContentColor = Color.White // Icon color
//                        )
//                    )
//                },
//                content = {paddingValues ->
//                    Surface(
//                        color = Color(0xFF977AD6),
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(paddingValues)  // Apply paddingValues
//                    ) {
//                        Column {
//                            BannerAdView(true, AdSize.FULL_BANNER)
//                            Text("Main Content")
//                            Text("Main Content")
//
//                            // MyFloatingActionButton()
//                        }
//
//                        // }
//                    }
//                }
//            )
//        }
//    }
//
//}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DrawerWithScaffold() {
//
//
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val bottomSheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = true // Skips partially expanded state
//    )
//    val coroutineScope = rememberCoroutineScope()
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//
//    // Bottom Sheet Content
//    fun showBottomDrawer() {
//        coroutineScope.launch {
//            bottomSheetState.show()
//        }
//    }
//    // Main Screen with ModalBottomSheet
//
//    ModalBottomSheet(
//        sheetState = bottomSheetState,
//        onDismissRequest = {
//            coroutineScope.launch { bottomSheetState.hide()
//
//            } // Dismiss when tapping outside
//        },
//        content = {
//            // Bottom drawer content
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//                Text(
//                    text = "This is the Bottom Drawer",
//                    style = MaterialTheme.typography.titleMedium,
//                    color = Color.Black
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(onClick = {
//                    coroutineScope.launch { bottomSheetState.hide() }
//                }) {
//                    Text("Close Drawer")
//                }
//            }
//        }
//    )
//
//        // Main Drawer
//        ModalNavigationDrawer(
//            drawerState = drawerState,
//            drawerContent = {
//                ModalDrawerSheet(
//                    modifier = Modifier
//                        .width(screenWidth * 0.70f)
//                        .padding(16.dp)
//                ) {
//                    Surface(color = Color(0xFF6C76BB)) {
//                        LazyColumn(
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            item {
//                                Card(
//                                    colors = CardDefaults.cardColors(
//                                        containerColor = Color(0xFF2E077E)
//                                    ),
//                                    shape = RectangleShape,
//                                    border = BorderStroke(2.dp, color = Color.LightGray),
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .height(400.dp)
//                                        .padding(3.dp)
//                                ) {
//                                    Column(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(150.dp)
//                                    ) {
//                                        Spacer(modifier = Modifier.height(10.dp))
//                                        Row {
//                                            IconButton(onClick = {
//                                                coroutineScope.launch {
//                                                    bottomSheetState.show() // Show bottom drawer
//                                                }
//                                            }, modifier = Modifier.align(Alignment.CenterVertically)) {
//                                                Icon(
//                                                    imageVector = Icons.AutoMirrored.Filled.ViewList,
//                                                    contentDescription = "New List Icon",
//                                                    tint = Color(0xFFC9CBE9)
//                                                )
//                                            }
//                                            Text(
//                                                text = "NEW LIST",
//                                                style = MaterialTheme.typography.titleMedium,
//                                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
//                                                color = Color(0xFFC9CBE9),
//                                                modifier = Modifier
//                                                    .clickable {
//                                                        coroutineScope.launch {
//                                                            bottomSheetState.show() // Show bottom drawer
//                                                        }
//                                                    }
//                                                    .padding(top = 12.dp)
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        ) {
//            // Main screen content
//            Surface(
//                color = Color(0xFF3A468F),
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Scaffold(
//                    topBar = {
//                        TopAppBar(
//                            title = { Text("Shopping List") },
//                            navigationIcon = {
//                                IconButton(onClick = {
//                                    coroutineScope.launch {
//                                        if (drawerState.isClosed) {
//                                            drawerState.open()
//                                        } else {
//                                            drawerState.close()
//                                        }
//                                    }
//                                }) {
//                                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
//                                }
//                            },
//                            colors = TopAppBarDefaults.topAppBarColors(
//                                containerColor = Color(0xFF3A468F),
//                                titleContentColor = Color.White,
//                                navigationIconContentColor = Color.White
//                            )
//                        )
//                    },
//                    content = { paddingValues ->
//                        Surface(
//                            color = Color(0xFF977AD6),
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(paddingValues)
//                        ) {
//                            Column {
//                                Text("Main Content")
//                            }
//                        }
//                    }
//                )
//            }
//        }
//    }




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerWithScaffold() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    // Drawer state for the ModalNavigationDrawer
   // val drawerState = androidx.compose.material.rememberDrawerState(initialValue = androidx.compose.material.DrawerValue.Closed)

    // val drawerState = rememberDrawerState(DrawerValue.Closed)
    // Bottom sheet state for the ModalBottomSheet
     val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
//    val bottomSheetState = rememberModalBottomSheetState(
//        skipPartiallyExpanded = true // Skips partially expanded state
//    )
    val coroutineScope = rememberCoroutineScope()

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    // Function to show the bottom sheet
    fun showBottomDrawer() {
        coroutineScope.launch {
            bottomSheetState.show()
        }
    }

    // Main content layout wrapped in ModalBottomSheetLayout
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            // Bottom drawer content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "This is the Bottom Drawer",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    coroutineScope.launch { bottomSheetState.hide() }
                }) {
                    Text("Close Drawer")
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
                        .padding(16.dp)
                ) {
                    Surface(color = Color(0xFF6C76BB)) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            item {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF2E077E)
                                    ),
                                    shape = RectangleShape,
                                    border = BorderStroke(2.dp, color = Color.LightGray),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(400.dp)
                                        .padding(3.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                    ) {
                                        Spacer(modifier = Modifier.height(10.dp))
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
                                    }
                                }
                            }
                        }
                    }
                }
            }
        ) {
            // Main screen content inside the ModalNavigationDrawer layout
            Surface(
                color = Color(0xFF3A468F),
                modifier = Modifier.fillMaxSize()
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Shopping List") },
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
                                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                                }
                            },
//                           contentColor = TopAppBarDefaults.topAppBarColors(
//                                containerColor = Color(0xFF3A468F),
//                                titleContentColor = Color.White,
//                                navigationIconContentColor = Color.White
//                            )
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            color = Color(0xFF977AD6),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            Column {
                                Text("Main Content")
                            }
                        }
                    }
                )
            }
        }
    }
}


