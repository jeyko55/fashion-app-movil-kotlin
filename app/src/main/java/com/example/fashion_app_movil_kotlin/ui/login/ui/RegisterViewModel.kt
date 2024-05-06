package com.example.fashion_app_movil_kotlin.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class RegisterViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _phone = MutableLiveData<String>()
    val phone : LiveData<String> = _phone

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _confirmationPassword = MutableLiveData<String>()
    val confirmationPassword : LiveData<String> = _confirmationPassword

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable : LiveData<Boolean> = _registerEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onRegisterChanged(
        name: String,
        email: String,
        phone: String,
        password: String,
        confirmationPassword: String,
        ) {
        _name.value = name
        _email.value = email
        _phone.value = phone
        _password.value = password
        _confirmationPassword.value = confirmationPassword
        _registerEnable.value =
            isValidEmail(email) &&
                    isValidName(name)
                    isValidConfirmationPassword(confirmationPassword, password)
      _registerEnable.value =
            isValidEmail(email) &&
                    isValidPassword(password) &&
                    isValidName(name) &&
                    isValidConfirmationPassword(confirmationPassword, password) &&
                    isValidPhone(phone)

    }

    private fun isValidPhone(phone: String): Boolean = phone.length >= 10

    private fun isValidConfirmationPassword(confirmationPassword: String, password: String): Boolean =
        isValidPassword(password) && password == confirmationPassword

    private fun isValidName(name: String): Boolean = name.isNotEmpty()

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String): Boolean = password.length > 7
    suspend fun onRegisterSelected() {

        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

}