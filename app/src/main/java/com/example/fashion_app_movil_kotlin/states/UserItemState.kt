package com.example.fashion_app_movil_kotlin.states

import com.example.fashion_app_movil_kotlin.database.user_item.UserItem

data class UserItemState(
    val userItem: List<UserItem> = emptyList(),
    val userId: Int = 0,
    val itemId: Int = 0,
)