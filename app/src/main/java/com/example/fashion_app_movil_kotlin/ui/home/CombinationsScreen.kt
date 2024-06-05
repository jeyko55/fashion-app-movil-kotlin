package com.example.fashion_app_movil_kotlin.ui.home

import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
        BackgroundImage(modifier = Modifier, resourceId = R.drawable.background_home_image)

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
                    .fillMaxWidth()
                    .padding(32.dp),
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

                // Obtener las prendas superiores, inferiores y calzado
                val topItems = itemState.items.filter { it.clothingType == "Prenda superior" }
                val bottomItems = itemState.items.filter { it.clothingType == "Prenda inferior" }
                val shoes = itemState.items.filter { it.clothingType == "Calzado" }

                // Generar combinaciones aleatorias
                val combinations = generateRandomCombinations(topItems, bottomItems, shoes)
                // Mostrar combinaciones
                if (combinations.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(combinations) { combination ->
                            CombinationItem(combination)
                        }
                    }
                } else {
                    // Display the default placeholder image if no combinations can be made
                    Image(
                        painter = painterResource(id = R.drawable.null_pointer_symbol),
                        contentDescription = "Add Clothing Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }


                Spacer(modifier = Modifier.padding(20.dp))

                Button(
                    // 'Agendar' Button
                    modifier = Modifier
                        .height(50.dp)
                        .width(250.dp),
                    // Color similar a las vistas de Figma
                    colors = ButtonDefaults.buttonColors(
                        disabledContentColor = Color.White,
                        contentColor = Color.White,
                        containerColor = Color(0xFF03A9F4),
                    ),
                    onClick = {
                        onItemEvent(ItemEvent.SaveItem) // Guarda el item en la tabla Item
                        restartItemState(onItemEvent)// Pone los campos en blanco
                        // onAgendarNav()
                    },
                    enabled = isItemValid(itemState), // FALTA HACER
                ) {
                    Text(text = "Agendar")
                } // End 'Agendar' Button


            } // End Column
        } // End Box
    } // End Scaffold
} // End CombinationsPortrait
// Función para generar combinaciones aleatorias
fun generateRandomCombinations(
    topItems: List<Item>,
    bottomItems: List<Item>,
    shoes: List<Item>
): List<Combination> {
    val combinations = mutableListOf<Combination>()
    if (topItems.isNotEmpty() && bottomItems.isNotEmpty() && shoes.isNotEmpty()) {
        // Limitar el número de combinaciones a la cantidad mínima de items en cada categoría
        val limit = minOf(topItems.size, bottomItems.size, shoes.size)
        repeat(limit) {
            val topItem = topItems.random()
            val bottomItem = bottomItems.random()
            val shoe = shoes.random()
            combinations.add(Combination(topItem, bottomItem, shoe))
        }
    }
    return combinations
}

// Clase de datos para una combinación
data class Combination(
    val topItem: Item,
    val bottomItem: Item,
    val shoe: Item
)

@Composable
fun CombinationItem(combination: Combination) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DisplayClothingItem(combination.topItem)
        DisplayClothingItem(combination.bottomItem)
        DisplayClothingItem(combination.shoe)
    }
}

@Composable
fun DisplayClothingItem(item: Item) {
    // Muestra una prenda de vestir individual
    Image(
        painter = rememberImagePainter(data = Uri.parse(item.imagePath)),
        contentDescription = "descipción cualquiera",
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
    )
}
