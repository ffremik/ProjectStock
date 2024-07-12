package com.example.projectstock

import android.app.Application
import androidx.room.Room
import com.example.projectstock.database.DaoStock
import com.example.projectstock.database.StockBase

class StockApplication: Application(){
    private lateinit var stockBase: StockBase

    override fun onCreate() {
        super.onCreate()
        stockBase = Room.databaseBuilder(this, StockBase::class.java, "stock_base").build()
    }
    fun getDaoStock(): DaoStock{
        return stockBase.daoBase()
    }

}