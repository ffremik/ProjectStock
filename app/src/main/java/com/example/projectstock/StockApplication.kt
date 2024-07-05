package com.example.projectstock

import android.app.Application
import com.example.projectstock.database.StockBase

class StockApplication(): Application(){
    val stockBase: StockBase by lazy {
        StockBase.createStockBase(this)
    }
}