package com.example.fashion_app_movil_kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fashion_app_movil_kotlin.database.user.User
import com.example.fashion_app_movil_kotlin.database.user.UserDAO

@Database(
    entities = [User::class],
    version = 1
)

abstract class FashionAppDatabase: RoomDatabase() {
    abstract val userDao: UserDAO
}