package com.example.fashion_app_movil_kotlin.view_models

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay


class LoginViewModel : ViewModel() {



    //ESTA ERA LA VERSIÓN ANTERIOR: NO TIENE UTILIDAD PORQUE HAY ES QUE OBTENER LOS DATOS DE LA BASE DE DATOS PARA LOGUEAR EL USUARIO

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isValidPassword(password: String): Boolean = password.length > 7
    suspend fun onLoginSelected() {

        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

}