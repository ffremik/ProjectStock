package com.example.projectstock.view.stockscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectstock.view.stockscreen.viewmodel.StockViewModel
import com.example.projectstock.database.StorageItem


@Preview(showBackground = true)
@Composable
fun dsaw() {
    StockScreen()
}


@Composable
fun StockScreen(viewModel: StockViewModel = viewModel(factory = StockViewModel.factory)) {
    val isOpen by viewModel.isOpenAdd.collectAsState()
    val listStorageItems by viewModel.listStorageItems.collectAsState(emptyList())
    val searchStorageItem by viewModel.searchListStorageItem.collectAsState()
    val isSearch by viewModel.isSearch.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        if (isOpen) {
            ScreenAddItem(viewModel)
        }
        SearchEditField(viewModel)
        Button(
            onClick = {
                viewModel.updateIsOpenAdd()
                viewModel.items = null
            }
        ) {
            Text(text = "Добавить")
        }
        CardStorageItem()
        if (isSearch) {
            LazyColumn {
                items(listStorageItems) {
                    ItemStock(item = it) { viewModel.updateStorageItem(it) }
                }
            }
        }else{
            LazyColumn {
                items(searchStorageItem){
                    ItemStock(item = it) {viewModel.updateStorageItem(it)}
                }
            }
        }
    }
}

@Composable
fun ItemStock(
    item: StorageItem,
    onClick: (StorageItem) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    onClick(item)
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.4f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.vendorCode,
                    fontSize = 17.sp
                )
                Text(
                    text = item.code,
                    fontSize = 17.sp
                )
            }

            Text(
                modifier = Modifier
                    .weight(1.4f),
                text = item.name,
                fontSize = 17.sp,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.3f)
            ) {
                Text(
                    text = "${item.quantity}",
                    fontSize = 17.sp
                )
                Text(
                    text = item.place,
                    fontSize = 17.sp
                )
            }

        }
    }
}

@Composable
fun CardStorageItem() {
    Card(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    modifier = Modifier.weight(0.4f),
                    text = "Артикл\nКод",
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .weight(1.4f),
                    text = "Название",
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.weight(0.4f),
                    text = "Кол-во\nМесто",
                    fontSize = 17.sp,
                )
            }
        }
    }
}

@Composable
fun SearchEditField(viewModel: StockViewModel) {
    val userInputCode by viewModel.userInputCode.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = userInputCode,
            onValueChange = {
                viewModel.updateUserInputCode(it)
            },
            label = {
                Text(text = "Введите артикл/код")
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        viewModel.search(userInputCode)
                    },
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            }
        )
    }

}