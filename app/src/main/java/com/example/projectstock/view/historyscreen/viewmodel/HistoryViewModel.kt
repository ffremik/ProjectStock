package com.example.projectstock.view.historyscreen.viewmodel

import androidx.compose.ui.res.stringArrayResource
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.projectstock.R
import com.example.projectstock.StockApplication
import com.example.projectstock.database.DaoStock
import com.example.projectstock.view.data.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryViewModel(
    private val daoStock: DaoStock,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as StockApplication
                HistoryViewModel(application.getDaoStock(), application.userPreferencesRepository)
            }
        }
    }

    val isAutoDelete = userPreferencesRepository.isAutoDelete.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), false
    )

    fun updateIsAutoDelete(){
        viewModelScope.launch {
            userPreferencesRepository.saveIsAutoDelete(!isAutoDelete.value)
        }
    }

    val listHistoryItems = daoStock.historyList()

    val isOpenDialogDelete = MutableStateFlow(false)
    val isOpenSelectPeriod = MutableStateFlow(false)
    val isErrorPeriod = MutableStateFlow(false)

    val selectPeriod = MutableStateFlow("1 Мес")

    fun updateIsOpenDialogDelete() {
        isOpenDialogDelete.value = !isOpenDialogDelete.value
        if (isOpenSelectPeriod.value) {
            updateIsOpenSelectPeriod()
        }
    }

    fun updateIsOpenSelectPeriod() {
        isOpenSelectPeriod.value = !isOpenSelectPeriod.value
    }

    fun updateSelectPeriod(period: String) {
        selectPeriod.value = period
    }

    fun deleteHistory() = viewModelScope.launch {
        val currentDate = getCurrentDate()
        isErrorPeriod.value = isOpenSelectPeriod.value
        if (!isErrorPeriod.value){
            when(selectPeriod.value){
                "1 Мес" -> {daoStock.deleteHistory(currentDate)}
                "За всё время" -> {daoStock.deleteHistory()}
            }
            updateIsOpenDialogDelete()
        }else {
            isErrorPeriod.value = isOpenSelectPeriod.value
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("MM", Locale.getDefault())
        return dateFormat.format(Date())
    }
}