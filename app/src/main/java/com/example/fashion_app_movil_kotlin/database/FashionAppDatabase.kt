package com.example.fashion_app_movil_kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fashion_app_movil_kotlin.database.item.Item
import com.example.fashion_app_movil_kotlin.database.item.ItemDAO
import com.example.fashion_app_movil_kotlin.database.user.User
import com.example.fashion_app_movil_kotlin.database.user.UserDAO
import com.example.fashion_app_movil_kotlin.database.user_item.UserItem
import com.example.fashion_app_movil_kotlin.database.useritem.UserItemDAO

@Database(
    entities = [User::class, Item::class, UserItem::class],
    version = 3
)

abstract class FashionAppDatabase: RoomDatabase() {
    abstract val userDao: UserDAO
    abstract val itemDao: ItemDAO
    abstract fun userItemDao(): UserItemDAO
}