package com.example.fashion_app_movil_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.fashion_app_movil_kotlin.ui.login.ui.LoginRegisterScreen
import com.example.fashion_app_movil_kotlin.ui.login.ui.LoginRegisterViewModel
import com.example.fashion_app_movil_kotlin.ui.login.ui.LoginScreen
import com.example.fashion_app_movil_kotlin.ui.login.ui.LoginViewModel
import com.example.fashion_app_movil_kotlin.ui.theme.FashionappmovilkotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FashionappmovilkotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(LoginViewModel())
                    // LoginRegisterScreen(LoginRegisterViewModel())
                }
            }
        }
    }
}

