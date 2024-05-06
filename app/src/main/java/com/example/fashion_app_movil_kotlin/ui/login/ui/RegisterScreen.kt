package com.example.fashion_app_movil_kotlin.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
fun RegisterScreen(viewModel: RegisterViewModel){
    Box(
        Modifier
            .fillMaxSize()

    ) {
        BackgroundImage(Modifier.fillMaxSize())
        Box {
            Register(Modifier.align(Alignment.Center), viewModel)
        }

    }
}


@Composable
fun Register(modifier: Modifier, viewModel: RegisterViewModel) {

    val name: String by viewModel.name.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val phone: String by viewModel.phone.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val confirmationPassword: String by viewModel.confirmationPassword.observeAsState(initial = "")
    val registerEnable: Boolean by viewModel.registerEnable.observeAsState(initial = false)
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
                text = "Registro",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black // Adjust color as needed
            )
            Spacer(modifier = Modifier.padding(4.dp))
            NameField(name) { viewModel.onRegisterChanged(it, email, phone, password, confirmationPassword) }
            Spacer(modifier = Modifier.padding(4.dp))
            EmailField(email) { viewModel.onRegisterChanged(name, it, phone, password, confirmationPassword) }
            Spacer(modifier = Modifier.padding(4.dp))
            PhoneField(phone) { viewModel.onRegisterChanged(name, email, it, password, confirmationPassword) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password) { viewModel.onRegisterChanged(name, email, phone, it, confirmationPassword) }
            Spacer(modifier = Modifier.padding(4.dp))
            ConfirmationPasswordField(confirmationPassword) { viewModel.onRegisterChanged(name, email, phone, password, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            
            Spacer(modifier = Modifier.padding(16.dp))
            RegisterButton(registerEnable) {
                coroutineScope.launch {
                    viewModel.onRegisterSelected()
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun NameField(name: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = name,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Nombre completo") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}
@Composable
fun PhoneField(phone: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = phone,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Celular") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun ConfirmationPasswordField(cp: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = cp,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Confirmar contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordField(password: String, onTextFieldChanged: (String) -> Unit) {
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
fun RegisterButton(registerEnable: Boolean, onRegisterSelected: () -> Unit) {
    Button(
        onClick = { onRegisterSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), // Color similar a las vistas de Figma
        colors = ButtonDefaults.buttonColors(
            disabledContentColor = Color.White,
            contentColor = Color.White,
            containerColor = Color(0xFF03A9F4),
        ), enabled = registerEnable
    ) {
        Text(text = "Registrarse")
    }
}

