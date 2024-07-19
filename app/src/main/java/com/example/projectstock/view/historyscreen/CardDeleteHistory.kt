package com.example.projectstock.view.historyscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectstock.R
import com.example.projectstock.view.historyscreen.viewmodel.HistoryViewModel

@Preview
@Composable
fun DeleteHistoryPreview() {
    ItemDeleteHistory()
}


@Composable
fun ItemDeleteHistory() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
    }
}

@Composable
fun DeleteHistory(historyViewModel: HistoryViewModel) {
    val isOpenDialogDelete by historyViewModel.isOpenDialogDelete.collectAsState()
    val isOpenSelectPeriod by historyViewModel.isOpenSelectPeriod.collectAsState()
    val isErrorPeriod by historyViewModel.isErrorPeriod.collectAsState()
    val isAutoDelete by historyViewModel.isAutoDelete.collectAsState()

    val context = LocalContext.current
    val items = remember { context.resources.getStringArray(R.array.my_array) }

    val selectPeriod by historyViewModel.selectPeriod.collectAsState(items[0])

    if (isOpenDialogDelete) {
        AlertDialog(
            modifier = Modifier.clickable {
                if (isOpenSelectPeriod) {
                    historyViewModel.updateIsOpenSelectPeriod()
                }
            },
            onDismissRequest = { historyViewModel.updateIsOpenDialogDelete() },
            confirmButton = { /*TODO*/ },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Удаление истории",
                        fontSize = 26.sp
                    )
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Удаление раз 3 мес?",
                            fontSize = 21.sp
                        )
                        Checkbox(
                            modifier = Modifier.size(60.dp),
                            checked = isAutoDelete,
                            onCheckedChange = {historyViewModel.updateIsAutoDelete()}
                        )
                    }

                    Row(
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    ) {
                        if (isErrorPeriod){
                            Text(
                                text = "Диапазон: ",
                                fontSize = 21.sp,
                                color = Color.Red
                            )
                        } else {
                            Text(
                                text = "Диапазон: ",
                                fontSize = 21.sp
                            )
                        }

                        Column(

                        ) {
                            if (isOpenSelectPeriod) {
                                for (item in items) {
                                    ItemMenu(text = item) {
                                        historyViewModel.updateSelectPeriod(it)
                                        historyViewModel.updateIsOpenSelectPeriod()
                                    }
                                }

                            } else {
                                MenuDeleteHistory(period = selectPeriod)
                                {
                                    historyViewModel.updateIsOpenSelectPeriod()
                                }
                            }

                        }

                    }
                    OutlinedButton(
                        modifier = Modifier.padding(16.dp),
                        onClick = { historyViewModel.deleteHistory() }
                    ) {
                        Text(
                            text = "Удалить",
                            fontSize = 21.sp
                        )
                    }

                }

            }
        )
    }

}


@Composable
fun MenuDeleteHistory(period: String, onClick: () -> Unit) {
    OutlinedButton(
        shape = ShapeDefaults.Medium,
        modifier = Modifier.size(width = 120.dp, height = 38.dp),
        onClick = { onClick() }
    ) {
        Row(

        ) {
            Text(
                text = period,
                fontSize = 18.sp
            )
            IconButton(
                modifier = Modifier,
                onClick = { onClick() }
            ) {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Open List"
                )
            }

        }
    }

}

@Composable
fun ItemMenu(text: String, onClick: (String) -> Unit) {
        Text(
            modifier = Modifier
                .sizeIn(minWidth = 160.dp)
                .border(2.dp, Color.Black)
                .clickable { onClick(text) },
            text = text,
            fontSize = 18.sp,
        )
}
