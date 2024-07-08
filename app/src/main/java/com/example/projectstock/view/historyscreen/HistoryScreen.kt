package com.example.projectstock.view.historyscreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectstock.database.StorageItem

@Composable
@Preview
fun PreviewHistoryScreen() {
    //HistoryScreen()
}

@Composable
fun HistoryScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.Start),
            onClick = {
                navController.navigate("stock")
            }
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "home"
            )
        }
        TitleCardHistoryItem()
        LazyColumn(

        ) {
            item {
                ItemCardHistory(
                    item = StorageItem(
                        name = "Фильтр воздушныйdas wqesa weq",
                        quantity = 23,
                        vendorCode = "ds",
                        code = "2a",
                        place = "2"
                    )
                )
            }
        }
    }
}

@Composable
fun TitleCardHistoryItem() {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "Название",
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                maxLines = 2,
            )
            Text(
                modifier = Modifier
                    .weight(0.7f),
                text = "Операция",
                fontSize = 17.sp
            )
            Text(
                text = "Дата",
                fontSize = 17.sp
            )
        }
    }
}