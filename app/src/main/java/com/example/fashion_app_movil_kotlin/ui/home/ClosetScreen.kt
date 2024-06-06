package com.example.fashion_app_movil_kotlin.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fashion_app_movil_kotlin.R
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
fun ClosetScreen(
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
        BackgroundImage(modifier = Modifier, resourceId = R.drawable.background_home_image)

        ClosetPortrait(
            modifier = Modifier,
            // States from View Models
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
fun ClosetPortrait(
    modifier: Modifier,

    // States from View Models
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
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                val userActual = userState.users.find { it.email == userState.email }

                // Filtrar los ítems correspondientes al usuario actual
                val userItems = if (userActual != null) {
                    userItemState.userItem.filter { it.userId == userActual.userId }
                } else {
                    emptyList()
                }

                // Obtener los ítems desde itemState.items basados en los itemIds de userItems
                val items = itemState.items.filter { item ->
                    userItems.any { userItem -> userItem.itemId == item.itemId }
                }

                // Obtener las prendas superiores, inferiores y calzado
                val topItems = items.filter { it.clothingType == "Prenda superior" }
                val bottomItems = items.filter { it.clothingType == "Prenda inferior" }
                val shoeItems = items.filter { it.clothingType == "Calzado" }

                // Title "Prendas superiores"
                Text(
                    text = "Prendas superiores",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
                // Display top items
                //var actualUser = userState.users.find { it.email == userState.email }
                if (topItems.isNotEmpty()) {
                    DisplayCarouselByClothingType(topItems)
                } else {
                    // Display the default placeholder image if no image is selected
                    Image(
                        painter = painterResource(id = R.drawable.null_pointer_symbol),
                        contentDescription = "Add Clothing Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }
                // Title "Prendas inferiores"
                Text(
                    text = "Prendas inferiores",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
                // Display bottom items
                if (bottomItems.isNotEmpty()) {
                    DisplayCarouselByClothingType(bottomItems)
                } else {
                    // Display the default placeholder image if no image is selected
                    Image(
                        painter = painterResource(id = R.drawable.null_pointer_symbol),
                        contentDescription = "Add Clothing Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }
                // Title "Calzado"
                Text(
                    text = "Calzado",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
                // Display shoes items
                if (shoeItems.isNotEmpty()) {
                    DisplayCarouselByClothingType(shoeItems)
                } else {
                    // Display the default placeholder image if no image is selected
                    Image(
                        painter = painterResource(id = R.drawable.null_pointer_symbol),
                        contentDescription = "Add Clothing Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }
            }

            FloatingActionButton(
                onClick = onAddClothingSelected,
                modifier = Modifier
                    .padding(16.dp) // Add padding around FAB
                    .align(Alignment.BottomEnd) // Position FAB bottom-right
            ) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Add Clothing")
            }
        }
    }
}

