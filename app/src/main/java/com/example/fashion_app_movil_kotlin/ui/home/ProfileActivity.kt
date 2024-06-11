package com.example.fashion_app_movil_kotlin.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.fashion_app_movil_kotlin.R
import com.example.fashion_app_movil_kotlin.database.item.Item
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.events.UserEvent
import com.example.fashion_app_movil_kotlin.events.UserItemEvent
import com.example.fashion_app_movil_kotlin.states.ItemState
import com.example.fashion_app_movil_kotlin.states.UserItemState
import com.example.fashion_app_movil_kotlin.states.UserState
import com.example.fashion_app_movil_kotlin.ui.components.*
import com.example.fashion_app_movil_kotlin.view_models.ItemViewModel
import com.example.fashion_app_movil_kotlin.view_models.UserItemViewModel
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel
@Composable
fun ProfileScreen (
    // View Models
    userViewModel: UserViewModel,
    itemViewModel: ItemViewModel,
    userItemViewModel: UserItemViewModel,

    // Eventos locales
    onUserEvent: (UserEvent) -> Unit,
    onItemEvent: (ItemEvent) -> Unit,
    onUserItemEvent: (UserItemEvent) -> Unit,

    // BottomBar
    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,

    // Add button
    onAddClothingSelected: () -> Unit,
) {
    val userState by userViewModel.state.collectAsState()
    val itemState by itemViewModel.state.collectAsState()
    val userItemState by userItemViewModel.state.collectAsState()

    Box(
        Modifier.fillMaxSize()
    ) {
        ProfilePortrait(
            modifier = Modifier,
            // States from view models
            userState,
            itemState,
            userItemState,

            // Eventos locales
            onUserEvent,
            onItemEvent,
            onUserItemEvent,

            onClosetSelected,
            onCombinationsSelected,
            onCalendarSelected,
            onArchivedSelected,
            onProfileSelected,
            onAddClothingSelected,
        )
    }
}

@Composable
fun ProfilePortrait(
    modifier: Modifier,

    // States from view models
    userState: UserState,
    itemState: ItemState,
    userItemState: UserItemState,

    // Eventos locales
    onUserEvent: (UserEvent) -> Unit,
    onItemEvent: (ItemEvent) -> Unit,
    onUserItemEvent: (UserItemEvent) -> Unit,

    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    onAddClothingSelected: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBarImage(modifier = Modifier)
        },
        bottomBar = {
            FashionAppBottomBar(
                modifier = Modifier,
                onClosetSelected,
                onCombinationsSelected,
                onCalendarSelected,
                onArchivedSelected,
                onProfileSelected,
                onAddClothingSelected,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BackgroundImage(modifier = Modifier, resourceId = R.drawable.background_home_image)

            Column(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title "Prendas superiores"

                val userActual = userState.users.find { it.email == userState.email }
                Spacer(modifier = Modifier.height(30.dp))
                userActual?.let { user ->
                    Text(
                        text = "Nombre: ${user.name}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Email: ${user.email}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Teléfono: ${user.phoneNumber}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                } ?: run {
                    Text(
                        text = "No se encontró el usuario.",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

            } // End Column
        } // End Box
    } // End Scaffold
} // End CombinationsPortrait
