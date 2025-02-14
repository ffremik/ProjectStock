package com.example.projectstock.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoStock {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: StorageItem)

    @Query("SELECT * FROM StorageItem")
    fun itemList(): Flow<List<StorageItem>>

    @Query("SELECT * FROM StorageItem WHERE name LIKE :itemSearch||'%'OR vendorCode LIKE :itemSearch||'%' OR code LIKE :itemSearch||'%' ")
    suspend fun searchItem(itemSearch: String): List<StorageItem>
    //SELECT * FROM StorageItem
    //WHERE name LIKE "%" OR vendorCode LIKE "%" OR code  LIKE "%"

    @Delete
    suspend fun deleteItem (item: StorageItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistoryItem(historyItem: HistoryItem)

    @Query("SELECT * FROM historyitem")
    fun historyList(): Flow<List<HistoryItem>>

    @Query("DELETE FROM historyitem WHERE substr(date, 4) =:date")
    suspend fun deleteHistory(date: String)

    @Query("DELETE FROM HistoryItem")
    suspend fun deleteHistory()
}