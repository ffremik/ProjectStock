package com.example.projectstock.view.stockscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projectstock.StockApplication
import com.example.projectstock.database.DaoStock
import com.example.projectstock.database.HistoryItem
import com.example.projectstock.database.StorageItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StockViewModel(private val daoStock: DaoStock) : ViewModel() {
    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY]) as StockApplication
                StockViewModel(application.getDaoStock())
            }
        }
    }

    private val _userInputCode = MutableStateFlow("")
    val userInputCode = _userInputCode.asStateFlow()

    fun updateUserInputCode(input: String) {
        _userInputCode.value = input
    }

    val isOpenAdd = MutableStateFlow(false)
    val isOpenDelete = MutableStateFlow(false)

    val isErrorInputName = MutableStateFlow(false)
    val isErrorInputVendorCode = MutableStateFlow(false)
    val isErrorInputCode = MutableStateFlow(false)
    val isErrorInputQuantity = MutableStateFlow(false)
    val isErrorInputPlace = MutableStateFlow(false)

    val addItemVendorCode = MutableStateFlow("")
    val addItemCode = MutableStateFlow("")
    val addItemName = MutableStateFlow("")
    val addItemQuantity = MutableStateFlow("")
    val addItemPlace = MutableStateFlow("")

    val textIsAdd = MutableStateFlow(false)

    var items: StorageItem? = null
    val listStorageItems = daoStock.itemList()

    var isSearch = MutableStateFlow(userInputCode.value.isEmpty())
    val searchListStorageItem = MutableStateFlow(emptyList<StorageItem>())

    fun search() = viewModelScope.launch {
        searchListStorageItem.value = emptyList()
        isSearch.value = userInputCode.value.isEmpty()
        searchListStorageItem.value = daoStock.searchItem(userInputCode.value)
    }

    fun delete() = viewModelScope.launch {
        if (items !== null) {
            daoStock.deleteItem(items!!)
            resetInput()
            updateIsOpenDelete()
        }
    }


    fun updateIsOpenAdd() {
        isOpenAdd.value = !isOpenAdd.value
    }

    fun updateIsOpenDelete() {
        isOpenDelete.value = !isOpenDelete.value
    }

    fun updateVendorCode(vendorCode: String) {
        addItemVendorCode.value = vendorCode
    }

    fun updateCode(code: String) {
        addItemCode.value = code
    }

    fun updateName(name: String) {
        addItemName.value = name
    }

    fun updateQuantity(quantity: String) {
        addItemQuantity.value = quantity
    }

    fun updatePlace(place: String) {
        addItemPlace.value = place
    }

    fun updateStorageItem(item: StorageItem) {
        items = item
        updateIsOpenAdd()
        addItemName.value = item.name
        addItemQuantity.value = item.quantity.toString()
        addItemCode.value = item.code
        addItemVendorCode.value = item.vendorCode
        addItemPlace.value = item.place

    }

    fun addItem() = viewModelScope.launch {
        val item = items?.copy(
            name = addItemName.value,
            quantity = addItemQuantity.value.toInt(),
            vendorCode = addItemVendorCode.value,
            code = addItemCode.value
        ) ?: StorageItem(
            name = addItemName.value,
            quantity = addItemQuantity.value.toInt(),
            vendorCode = addItemVendorCode.value,
            code = addItemCode.value,
            place = addItemPlace.value
        )
        val previousQuantity = items?.quantity ?: 0

        daoStock.addItem(item)
        items = null

        if (item.quantity - previousQuantity != 0){
            daoStock.addHistoryItem(
                HistoryItem(
                    itemsName = item.name,
                    itemsQuantity = (item.quantity.minus(previousQuantity)),
                    itemRemainderQuantity = item.quantity,
                    date = getCurrentDate()
                )
            )
            search()
        }


    }

    fun resetInput() {
        addItemName.value = ""
        addItemCode.value = ""
        addItemQuantity.value = ""
        addItemVendorCode.value = ""
        addItemPlace.value = ""
        items = null
    }

    fun isAdd() {
        isErrorInputName.value = addItemName.value.isEmpty()
        isErrorInputVendorCode.value = addItemVendorCode.value.isEmpty()
        isErrorInputCode.value = addItemCode.value.isEmpty()
        isErrorInputQuantity.value = !addItemQuantity.value.matches("""\d+""".toRegex())
        isErrorInputPlace.value = addItemPlace.value.isEmpty()

        if (
            !isErrorInputName.value &&
            !isErrorInputVendorCode.value &&
            !isErrorInputCode.value &&
            !isErrorInputQuantity.value &&
            !isErrorInputPlace.value
        ) {
            addItem()
            resetInput()
            updateIsOpenAdd()

        }
    }

}

fun getCurrentDate(): String{
    val dateFormat = SimpleDateFormat("dd.MM", Locale.getDefault())
    return dateFormat.format(Date())
}