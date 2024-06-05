package com.example.fashion_app_movil_kotlin.database.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "item",
    indices = [Index(value = ["imagePath"], unique = true)]
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    val itemId: Int = 0,
    @ColumnInfo(name = "imagePath")
    val imagePath: String = "",
    @ColumnInfo(name = "clothingType")
    val clothingType: String,
    @ColumnInfo(name = "color")
    val color: String
)