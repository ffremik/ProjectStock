package com.example.projectstock.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow


@Entity
data class StorageItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val vendorCode: String,
    val code: String,
    val place: String
)


@Entity
data class HistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemsName: String,
    val itemsQuantity: Int,
    val itemRemainderQuantity: Int,
    val date: String
)

