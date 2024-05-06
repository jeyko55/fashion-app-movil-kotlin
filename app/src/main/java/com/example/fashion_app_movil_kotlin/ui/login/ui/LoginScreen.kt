package com.example.fashion_app_movil_kotlin.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fashion_app_movil_kotlin.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()

    ) {
        BackgroundImage(Modifier.fillMaxSize())
        Box {
            Login(Modifier.align(Alignment.Center), viewModel)
        }

    }
}

@Composable
fun BackgroundImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.image_6), // Replace with your image resource
        contentDescription = "Background",
        modifier = modifier
            .fillMaxSize() // Cover the entire screen
    )
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
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
                .padding(32.dp)
        ) {// Add padding around the content)
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            Text( // Welcome Text
                text = "¡Bienvenido a Fashion App!",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black // Adjust color as needed
            )
            Spacer(modifier = Modifier.padding(4.dp))
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnable) {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), // Color similar a las vistas de Figma
        colors = ButtonDefaults.buttonColors(
            disabledContentColor = Color.White,
            contentColor = Color.White,
            containerColor = Color(0xFF03A9F4),
        ), enabled = loginEnable
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "¿Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF000000)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_fashionapp),
        contentDescription = "Header",
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}
