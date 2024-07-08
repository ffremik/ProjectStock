package com.example.projectstock.view.historyscreen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectstock.database.StorageItem

@Composable
@Preview
fun PreviewItemHistory() {
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

@Composable
fun ItemCardHistory(item: StorageItem) {
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
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp),
                text = item.name,
                fontSize = 17.sp,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(0.7f),
                text = "Списание: 2",
                fontSize = 17.sp
            )
            Text(
                text = "07.07",
                fontSize = 17.sp
            )

        }
    }
}