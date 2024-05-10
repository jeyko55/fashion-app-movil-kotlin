package com.example.fashion_app_movil_kotlin.states

import com.example.fashion_app_movil_kotlin.database.user.User

data class UserState (
    val users: List<User> = emptyList(),
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val isAddingUser: Boolean = false
)