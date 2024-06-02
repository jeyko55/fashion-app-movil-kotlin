package com.example.fashion_app_movil_kotlin.database.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    val itemId: Int = 0,
    @ColumnInfo(name = "image")
    val imagePath: String,
    @ColumnInfo(name = "clothingType")
    val clothingType: String,
    @ColumnInfo(name = "color")
    val color: String
)