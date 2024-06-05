package com.example.fashion_app_movil_kotlin.database.user_item;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

import com.example.fashion_app_movil_kotlin.database.item.Item;
import com.example.fashion_app_movil_kotlin.database.user.User;

@Entity(
    tableName = "user_item",
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["user_id"]),
        ForeignKey(entity =Item::class, parentColumns = ["item_id"], childColumns = ["item_id"])
    ]
)
data class UserItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_item_id")
    val userItemId: Int = 0,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "item_id")
    val itemId: Int
)