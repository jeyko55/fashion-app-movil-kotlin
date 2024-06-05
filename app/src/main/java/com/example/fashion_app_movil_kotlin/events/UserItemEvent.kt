package com.example.fashion_app_movil_kotlin.events

import com.example.fashion_app_movil_kotlin.database.user_item.UserItem

sealed interface UserItemEvent {
    object SaveUserItem : UserItemEvent
    data class DeleteUserItem(val userItem: UserItem) : UserItemEvent
    data class SetUserId(val userId: Int) : UserItemEvent
    data class SetItemId(val itemId: Int) : UserItemEvent
}