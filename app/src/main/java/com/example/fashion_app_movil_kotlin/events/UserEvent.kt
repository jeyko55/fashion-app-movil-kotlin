package com.example.fashion_app_movil_kotlin.events

import com.example.fashion_app_movil_kotlin.database.user.User

sealed interface UserEvent {
    object SaveUser: UserEvent

    data class SetName(val name: String): UserEvent
    data class SetEmail(val email: String): UserEvent
    data class SetPhoneNumber(val phoneNumber: String): UserEvent
    data class SetPassword(val password: String): UserEvent
    data class DeleteContact(val user: User): UserEvent

}