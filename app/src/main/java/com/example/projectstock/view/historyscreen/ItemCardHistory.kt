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
import com.example.projectstock.database.HistoryItem
import com.example.projectstock.database.StorageItem

@Composable
@Preview
fun PreviewItemHistory() {

}

@Composable
fun ItemCardHistory(historyItem: HistoryItem) {
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
                text = historyItem.itemsName,
                fontSize = 16.sp,
                maxLines = 2,
                textAlign = TextAlign.Center
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.7f)
            ) {
                if (historyItem.itemsQuantity > 0){
                    Text(
                        text = "Поступило: ${historyItem.itemsQuantity}",
                        fontSize = 16.sp
                    )
                } else {
                    Text(
                        text = "Списано: ${historyItem.itemsQuantity}",
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = "Остаток: ${historyItem.itemRemainderQuantity}",
                    fontSize = 16.sp,
                )
            }


            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = historyItem.date,
                fontSize = 16.sp
            )

        }
    }
}