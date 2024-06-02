package com.example.fashion_app_movil_kotlin.database.item

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {
    @Upsert //mix between @Insert and @Update
    suspend fun upsertItem(item: Item)
    @Delete
    suspend fun deleteItem(item: Item)
    @Query("SELECT * FROM item")
    fun getAllItems(): Flow<List<Item>>
    @Query("SELECT * FROM item WHERE color = :color")
    suspend fun getItemByColor(color: String): List<Item>
}