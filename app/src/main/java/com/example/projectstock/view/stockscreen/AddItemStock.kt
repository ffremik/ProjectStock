package com.example.projectstock.view.stockscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectstock.R
import com.example.projectstock.view.stockscreen.viewmodel.StockViewModel

@Preview(showBackground = true)
@Composable
fun PreviewScreenAddItem() {
    // ScreenAddItem()
}

@Composable
//viewModel: StockViewModel
fun ScreenAddItem(viewModel: StockViewModel) {
    val isErrorInputName by viewModel.isErrorInputName.collectAsState()
    val isErrorInputVendorCode by viewModel.isErrorInputVendorCode.collectAsState()
    val isErrorInputCode by viewModel.isErrorInputCode.collectAsState()
    val isErrorInputQuantity by viewModel.isErrorInputQuantity.collectAsState()
    val isErrorInputPlace by viewModel.isErrorInputPlace.collectAsState()

    val itemName by viewModel.addItemName.collectAsState()
    val itemVendorCode by viewModel.addItemVendorCode.collectAsState()
    val itemCode by viewModel.addItemCode.collectAsState()
    val itemQuantity by viewModel.addItemQuantity.collectAsState()
    val itemPlace by viewModel.addItemPlace.collectAsState()

    AlertDialog(
        //viewModel.updateIsOpenAdd()
        modifier = Modifier,
        onDismissRequest = {
            viewModel.updateIsOpenAdd()

        },
        confirmButton = { /*TODO*/ },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemTextField(
                    stringResource(id = R.string.vendor_code),
                    itemVendorCode,
                    isErrorInputVendorCode
                ) { it -> viewModel.updateVendorCode(it) }
                ItemTextField(
                    stringResource(id = R.string.code),
                    itemCode,
                    isErrorInputCode
                ) { it -> viewModel.updateCode(it) }
                ItemTextField(
                    stringResource(id = R.string.name_item),
                    itemName,
                    isErrorInputName
                ) { it -> viewModel.updateName(it) }
                ItemTextField(
                    stringResource(id = R.string.quantity),
                    itemQuantity,
                    isErrorInputQuantity
                ) { it -> viewModel.updateQuantity(it) }
                ItemTextField(
                    stringResource(id = R.string.place),
                    itemPlace,
                    isErrorInputPlace
                ) { viewModel.updatePlace(it) }

                if (viewModel.items !== null) {
                    OutlinedButton(
                        onClick = { viewModel.isAdd() }
                    ) {
                        Text(
                            text = stringResource(id = R.string.save_changes)
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            viewModel.updateIsOpenDelete()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete_item)
                        )
                    }
                } else {
                    OutlinedButton(
                        onClick = { viewModel.isAdd() }
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_item)
                        )
                    }
                }
            }

        }
    )
}

@Composable
fun ItemTextField(
    name: String,
    input: String,
    isError: Boolean,
    updateValue: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .padding(top = 6.dp),
        value = input,
        onValueChange = {
            updateValue(it)
        },
        label = {
            Text(text = name)
        },
        colors = TextFieldDefaults.colors(
            errorCursorColor = Color.Red
        ),
        leadingIcon = {
            if (isError) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
            }

        },
        isError = isError
    )
}