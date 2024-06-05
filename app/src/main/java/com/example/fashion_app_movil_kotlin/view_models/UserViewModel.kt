package com.example.fashion_app_movil_kotlin.view_models

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
    private val userDao: UserDAO
) : ViewModel() {

    private val _users = userDao.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(UserState())
    val state = combine(_state, _users) { state, users ->
        state.copy(
            users = users
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.SaveUser -> {
                val name = _state.value.name
                val email = _state.value.email
                val phoneNumber = _state.value.phoneNumber
                val password = _state.value.password

                val user = User(
                    name = name,
                    email = email,
                    phoneNumber = phoneNumber,
                    password = password
                )
                viewModelScope.launch {
                    userDao.upsertUser(user)
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

            is UserEvent.DeleteContact -> {
                viewModelScope.launch {
                    userDao.deleteUser(event.user)
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
                        email = event.email,
                        isEmailValid = true, // Reset email validation state
                        showUserNotFoundError = false, // Reset user not found error
                    )
                }
            }

            is UserEvent.SetPhoneNumber -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
            }

            is UserEvent.SetPassword -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        isPasswordValid = true, // Reset password validation state
                        showPasswordError = false // Reset password error
                    )
                }
            }

            is UserEvent.ValidateUser -> {
                val email = state.value.email
                val password = state.value.password

                viewModelScope.launch {
                    // Check if user exists with email
                    val user = userDao.getUserByEmail(email)
                    if (user != null) {
                        // User exists, check password
                        if (user.password == password) {
                            // Login successful
                            _state.update { it.copy(isLoggedIn = true) }
                        } else {
                            // Update UI with invalid password error
                            _state.update {
                                it.copy(
                                    isPasswordValid = false,
                                    showPasswordError = true // Show password error
                                )
                            }
                        }
                    } else {
                        // User doesn't exist, update UI with invalid email error (optional)
                        // You might choose a different message depending on your app's logic
                        _state.update {
                            it.copy(
                                isEmailValid = false,
                                showUserNotFoundError = true // Show user not found error
                            )
                        }
                    }
                }
            }

            is UserEvent.SetConfirmPassword -> {
                _state.update {
                    it.copy(
                        confirmPassword = event.confirmPassword
                    )
                }
            }
        }
    }
}


