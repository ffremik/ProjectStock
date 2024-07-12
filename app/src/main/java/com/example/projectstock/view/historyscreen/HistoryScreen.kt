package com.example.projectstock.view.historyscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projectstock.R
import com.example.projectstock.view.historyscreen.viewmodel.HistoryViewModel

@Composable
@Preview
fun PreviewHistoryScreen() {
    //HistoryScreen()
}

@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: HistoryViewModel = viewModel(factory = HistoryViewModel.factory)
) {

    val listHistoryItems by viewModel.listHistoryItems.collectAsState(initial = emptyList())

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
        DeleteHistory(viewModel)
        TitleCardHistoryItem()
        Box(
            modifier = Modifier.weight(1.0f)
        ) {
            LazyColumn(
            ) {
                items(listHistoryItems.reversed()) {
                    ItemCardHistory(it)
                }
            }
            if (listHistoryItems.isNotEmpty()) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 16.dp),
                    onClick = { viewModel.updateIsOpenDialogDelete() }
                ) {
                    Icon(
                        tint = Color.Green,
                        modifier = Modifier
                            .background(Color.White)
                            .size(45.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete history"
                    )

                }

            }
        }


    }
}

@Composable
fun TitleCardHistoryItem() {
    Card(
        modifier = Modifier
            .padding(4.dp)
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
                text = stringResource(id = R.string.name_item),
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                maxLines = 2,
            )
            Text(
                modifier = Modifier
                    .weight(0.7f),
                text =
                stringResource(id = R.string.operation) + "\n" +
                        stringResource(id = R.string.remainder),
                fontSize = 17.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.date),
                fontSize = 17.sp
            )
        }
    }
}
