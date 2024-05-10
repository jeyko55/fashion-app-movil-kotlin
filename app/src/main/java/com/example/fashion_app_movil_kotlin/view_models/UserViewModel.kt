package com.example.fashion_app_movil_kotlin.view_models

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fashion_app_movil_kotlin.database.user.User
import com.example.fashion_app_movil_kotlin.database.user.UserDAO
import com.example.fashion_app_movil_kotlin.events.UserEvent
import com.example.fashion_app_movil_kotlin.states.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val dao: UserDAO
) : ViewModel() {

    private val _users = dao.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(UserState())
    val state = combine(_state, _users) { state, users ->
        state.copy(
            users = users
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())


    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deleteUser(event.user)
                }
            }

            UserEvent.SaveUser -> {
                val name = state.value.name
                val email = state.value.email
                val phoneNumber = state.value.phoneNumber
                val password = state.value.password

                val user = User(
                    name = name,
                    email = email,
                    phoneNumber = phoneNumber,
                    password = password
                )
                viewModelScope.launch {
                    dao.upserttUser(user)
                }
                _state.update {
                    it.copy(
                        name = "",
                        email = "",
                        phoneNumber = "",
                        password = "",
                        isAddingUser = false
                    )
                }
            }

            is UserEvent.SetName -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }

            is UserEvent.SetEmail -> {
                _state.update {
                    it.copy(
                        name = event.email
                    )
                }
            }

            is UserEvent.SetPhoneNumber -> {
                _state.update {
                    it.copy(
                        name = event.phoneNumber
                    )
                }
            }

            is UserEvent.SetPassword -> {
                _state.update {
                    it.copy(
                        name = event.password
                    )
                }
            }
        }
    }
}

