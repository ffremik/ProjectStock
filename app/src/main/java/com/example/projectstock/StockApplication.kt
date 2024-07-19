package com.example.projectstock

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.projectstock.database.DaoStock
import com.example.projectstock.database.StockBase
import com.example.projectstock.view.historyscreen.data.UserPreferencesRepository
import com.example.projectstock.view.historyscreen.workmanager.HistoryWorkerManager
import java.util.concurrent.TimeUnit

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "newStore")
class StockApplication: Application(){
    private lateinit var stockBase: StockBase
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        stockBase = Room.databaseBuilder(this, StockBase::class.java, "stock_base").build()
        userPreferencesRepository = UserPreferencesRepository(dataStore)


    }
    fun getDaoStock(): DaoStock{
        return stockBase.daoBase()
    }
    fun getUserPreferencesRepository(): UserPreferencesRepository {
        return userPreferencesRepository
    }

}