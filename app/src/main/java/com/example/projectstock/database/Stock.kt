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


data class Shelf(
    val numberShelf: Int,
    val itemsList: List<StorageItem>
)

