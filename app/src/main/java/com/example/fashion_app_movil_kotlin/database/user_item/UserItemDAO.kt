package com.example.fashion_app_movil_kotlin.database.user_item

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserItemDAO {
    @Upsert
    suspend fun  upsertUserItem(userItem: UserItem)
    @Delete
    suspend fun deleteUserItem(userItem: UserItem)
    @Query("SELECT * FROM user_item")
    fun getAllUserItem(): Flow<List<UserItem>>
    @Query("SELECT * FROM user_item WHERE user_id = :userId")
    fun getUserItems (userId: Int): Flow<List<UserItem>>

    @Query("SELECT * FROM user_item WHERE item_id = :itemId")
    fun getItemUsers(itemId: Int): Flow<List<UserItem>>
}