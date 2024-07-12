package com.example.projectstock.view.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectstock.StockApplication
import com.example.projectstock.view.historyscreen.HistoryScreen
import com.example.projectstock.view.stockscreen.StockScreen
import com.example.projectstock.view.stockscreen.viewmodel.StockViewModel

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}

@Composable
fun MainScreen(
    viewModel: StockViewModel = viewModel(factory = StockViewModel.factory)
) {
    val nameRoute = remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            when (nameRoute.value) {
                "stock" -> {
                    IconItemScreen(
                        onClick = {
                            viewModel.updateIsOpenAdd()
                        },
                        icon = Icons.Default.Add,
                        content = "Add item"
                    )
                }

                "history" -> {

                }
            }

        }
    ) {
        NavigationHost(viewModel, modifier = Modifier.padding(it), nameRoute)
    }
}

@Composable
fun NavigationHost(
    viewModel: StockViewModel,
    modifier: Modifier = Modifier,
    routeName: MutableState<String>
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "stock"
    ) {
        composable(
            route = "stock"
        ) {
            routeName.value = "stock"
            StockScreen(viewModel, navController = navController)
        }
        composable(
            route = "history"
        ) {
            routeName.value = "history"
            HistoryScreen(navController)
        }
    }
}

@Composable
fun IconItemScreen(
    onClick: () -> Unit,
    icon: ImageVector,
    content: String = ""
) {
    IconButton(
        onClick = {
            onClick()
        }
    ) {
        Icon(
            tint = Color.Green,
            modifier = Modifier
                .background(Color.White)
                .size(45.dp),
            imageVector = icon,
            contentDescription = content
        )
    }
}