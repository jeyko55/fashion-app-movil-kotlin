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
fun CombinationsScreen (
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
        CombinationsPortrait(
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
fun CombinationsPortrait(
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
    var currentCombination by remember { mutableStateOf<Combination?>(null) }

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
                Text(
                    text = "Recomendaciones",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(modifier = Modifier.padding(4.dp))

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
                val shoes = items.filter { it.clothingType == "Calzado" }

                // Generar combinaciones aleatorias
                val combinations = generateRandomCombination(topItems, bottomItems, shoes)

                if (currentCombination == null) {
                    currentCombination = generateRandomCombination(topItems, bottomItems, shoes)
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(listOfNotNull(currentCombination)) { combination ->
                        CombinationItem(combination)
                    }
                }
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(250.dp),
                    colors = ButtonDefaults.buttonColors(
                        disabledContentColor = Color.White,
                        contentColor = Color.White,
                        containerColor = Color(0xFF03A9F4),
                    ),
                    onClick = {
                        currentCombination = generateRandomCombination(topItems, bottomItems, shoes)
                    },
                    enabled = true,
                ) {
                    Text(text = "Combinar prendas")
                }
            } // End Column
        } // End Box
    } // End Scaffold
} // End CombinationsPortrait
// Función para generar combinaciones aleatorias

fun generateRandomCombination(topItems: List<Item>, bottomItems: List<Item>, shoes: List<Item>): Combination? {
    if (topItems.isNotEmpty() && bottomItems.isNotEmpty() && shoes.isNotEmpty()) {
        val topItem = topItems.random()
        val bottomItem = bottomItems.random()
        val shoe = shoes.random()
        return Combination(topItem, bottomItem, shoe)
    }
    return null
}

@Composable
fun DisplayCombination(combination: Combination) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Prenda superior:")
        Image(
            painter = rememberImagePainter(data = combination.topItem.imagePath),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Prenda inferior:")
        Image(
            painter = rememberImagePainter(data = combination.bottomItem.imagePath),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Calzado:")
        Image(
            painter = rememberImagePainter(data = combination.shoe.imagePath),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
    }
}

data class Combination(
    val topItem: Item,
    val bottomItem: Item,
    val shoe: Item
)

@Composable
fun CombinationItem(combination: Combination) {
    DisplayCombination(combination)
}