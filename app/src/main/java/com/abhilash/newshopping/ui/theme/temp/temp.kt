package com.abhilash.newshopping.ui.theme.temp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberModalBottomSheetState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndependentDrawers() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    //  val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    // Bottom Drawer
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF00897B)) {
                var text by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Enter a name for the list:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Blue
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = text,
                        onValueChange = { newText -> text = newText },
                        label = { Text("List Name") },
                        placeholder = { Text("Type here") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                        text = "" // Reset text after saving
                    }) {
                        Text("Save List")
                    }
                }
            }
        }
    ) {
        // Main UI Content including ModalNavigationDrawer
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                    ModalDrawerSheet(
                        modifier = Modifier
                            .width(screenWidth * 0.70f)
                            .padding(3.dp)
                            .background(Color(0xFF6200EE))
                            .fillMaxHeight(),
                        drawerContainerColor = Color(0xFF00897B),
                ) {
                    Column {
                        Text(
                            text = "Drawer Item 1",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Blue
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Drawer Item 2",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Blue
                        )
                    }
                }
            }
        ) {
            // Main screen content
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Independent Drawers") },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Open Drawer")
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show() // Open bottom sheet when FAB is clicked
                        }
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "Show Bottom Drawer")
                    }
                },
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        Text(
                            "Main Content Here",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            )
        }
    }
}
