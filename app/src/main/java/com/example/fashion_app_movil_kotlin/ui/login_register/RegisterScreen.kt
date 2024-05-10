package com.example.fashion_app_movil_kotlin.ui.login_register

import android.util.Patterns
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fashion_app_movil_kotlin.events.UserEvent
import com.example.fashion_app_movil_kotlin.states.UserState
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(
    userViewModel: UserViewModel,
    onEvent: (UserEvent) -> Unit,
    onUserCreatedNav: () -> Unit
) {
    val userState by userViewModel.state.collectAsState()

    Box(
        Modifier
            .fillMaxSize()

    ) {
        BackgroundImage(Modifier.fillMaxSize())
        Box {
            RegisterPortrait(
                Modifier.align(Alignment.Center),
                userState,
                onEvent,
                onUserCreatedNav
            )
        }

    }
}

@Composable
fun RegisterPortrait(
    modifier: Modifier,
    state: UserState,
    onEvent: (UserEvent) -> Unit,
    onUserCreatedNav: () -> Unit
) {

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

        TextField(
            // Name TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Nombre completo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = state.name,
            onValueChange = {
                onEvent(UserEvent.SetName(it))
            },
        )

        Spacer(modifier = Modifier.padding(4.dp))

        TextField(
            // Email TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = state.email,
            onValueChange = {
                onEvent(UserEvent.SetEmail(it))
            },
        )

        Spacer(modifier = Modifier.padding(4.dp))

        TextField( // Phone Number TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Celular") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = state.phoneNumber,
            onValueChange = {
                onEvent(UserEvent.SetPhoneNumber(it))
            }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        @OptIn(ExperimentalMaterial3Api::class)
        TextField( // Password TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = state.password,
            onValueChange = {
                onEvent(UserEvent.SetPassword(it))
            }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        TextField(
            // Password Confirmation TextField
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Confirmar contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            value = state.confirmPassword,
            onValueChange = {
                onEvent(UserEvent.SetConfirmPassword(it))
            },
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp), // Color similar a las vistas de Figma
            colors = ButtonDefaults.buttonColors(
                disabledContentColor = Color.White,
                contentColor = Color.White,
                containerColor = Color(0xFF03A9F4),
            ),
            onClick = {
                onEvent(UserEvent.SaveUser)
                onUserCreatedNav()
            },
            enabled = isRegisterValid(state),
        ) {
            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.padding(4.dp))
        ForgotPassword(Modifier.align(Alignment.CenterHorizontally))

    }
}

fun isRegisterValid(state: UserState): Boolean {
    return state.name.isNotBlank()
            && state.email.isNotBlank()
            && Patterns.EMAIL_ADDRESS.matcher(state.email).matches()
            && state.phoneNumber.isNotBlank()
            && state.password.isNotBlank()
            && state.confirmPassword.isNotBlank()
            && state.password == state.confirmPassword
            && state.password.length >= 6
}