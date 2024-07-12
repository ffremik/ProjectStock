package com.example.projectstock.view.stockscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.projectstock.R
import com.example.projectstock.view.stockscreen.viewmodel.StockViewModel

@Composable
@Preview
fun PreviewScreenDeleteItem() {
    //ScreenDeleteItem()
}

@Composable
fun ScreenDeleteItem(viewModel: StockViewModel) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
        },
        text = {
            Column(

            ) {
                Text(text = stringResource(id = R.string.delete_message))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            viewModel.delete()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.yes))
                    }
                    OutlinedButton(
                        modifier = Modifier.weight(0.5f),
                        onClick = {
                            viewModel.updateIsOpenDelete()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.no))
                    }
                }
            }
        }
    )
}

