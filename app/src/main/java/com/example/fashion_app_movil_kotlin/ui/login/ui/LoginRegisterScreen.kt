package com.example.fashion_app_movil_kotlin.ui.login.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun LoginRegisterScreen(viewModel: LoginRegisterViewModel) {
    Box(
        Modifier
            .fillMaxSize()

    ) {
        BackgroundImage(Modifier.fillMaxSize())
        Box {
            LoginRegister(Modifier.align(Alignment.Center), viewModel)
        }

    }
}

@Composable
fun LoginRegister(modifier: Modifier, viewModel: LoginRegisterViewModel) {

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxWidth() // Occupy full width
                .padding(32.dp),
            verticalArrangement = Arrangement.Bottom
        ) {// Add padding around the content)
            Box(
                modifier = Modifier
                    .weight(1f) // Toma tanto espacio como sea posible, pero deja espacio para los botones
                    .align(Alignment.CenterHorizontally) // Centra la imagen horizontalmente
            ) {
                HeaderImage(Modifier.align(Alignment.Center))
            }
            RedirectedToLoginButton() {
                coroutineScope.launch {
                    viewModel.onLoginButtonSelected()
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            RedirectedToRegisterButton() {
                coroutineScope.launch {
                    viewModel.onRegisterButtonSelected()
                }
            }
        }
    }
}

@Composable
fun RedirectedToRegisterButton(onClickSelected: () -> Unit) {
    Button(
        onClick = { onClickSelected },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFF1A1A1A),

            ),
        border = BorderStroke(width = 2.dp, color = Color.Black), // Delineado redondeado

    ) {
        Text(text = "Registrarse")
    }
}

@Composable
fun RedirectedToLoginButton(onClickSelected: () -> Unit) {
    Button(
        onClick = { onClickSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), // Color similar a las vistas de Figma
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF03A9F4),
        )
    ) {
        Text(text = "Iniciar sesión")
    }
}


