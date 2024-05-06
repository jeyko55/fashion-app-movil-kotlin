package com.example.fashion_app_movil_kotlin.ui.login.ui

import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginRegisterViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun onLoginButtonSelected() {
        _isLoading.value = true
        delay(2000)
        _isLoading.value = false

        // Falta implementar el click que navegue a Login

    }

    suspend fun onRegisterButtonSelected() {
        _isLoading.value = true
        delay(2000)
        _isLoading.value = false

        // Falta implementar el click que navegue a Registrarse

    }

}
