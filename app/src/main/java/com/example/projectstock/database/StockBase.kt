package com.example.projectstock.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [StorageItem::class, HistoryItem::class],
    version = 2,
    exportSchema = false
)
abstract class StockBase(): RoomDatabase(){
    abstract fun daoBase(): DaoStock

//    companion object{
//        @Volatile
//        var Instance: StockBase? = null
//
////        fun createStockBase(context: Context): StockBase{
////            return Instance ?: synchronized(this ){
////                Room.databaseBuilder(
////                    context,
////                    StockBase::class.java,
////                    "stockBase"
////                ).build().also { Instance = it }
////            }
////        }
//  }

}