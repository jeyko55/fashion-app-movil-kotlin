package com.example.fashion_app_movil_kotlin.ui.home

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.fashion_app_movil_kotlin.R
import com.example.fashion_app_movil_kotlin.database.item.Item
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.states.ItemState
import com.example.fashion_app_movil_kotlin.ui.components.*
import com.example.fashion_app_movil_kotlin.view_models.ItemViewModel

@Composable
fun ClosetScreen(
    itemViewModel: ItemViewModel,
    // BottomBar
    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,

    // Add button
    onAddClothingSelected: () -> Unit,

    onEvent: (ItemEvent) -> Unit,
) {
    // Importante ver si se necesita userState o itemState en los otros Screens
    val itemState by itemViewModel.state.collectAsState()

    Box(
        Modifier.fillMaxSize()
    ) {

        ClosetPortrait(
            modifier = Modifier,
            itemViewModel,
            itemState,
            onClosetSelected,
            onCombinationsSelected,
            onCalendarSelected,
            onArchivedSelected,
            onProfileSelected,
            onAddClothingSelected,
            onEvent,
        )
    }
}

@Composable
fun ClosetPortrait(
    modifier: Modifier,
    itemViewModel: ItemViewModel,
    itemState: ItemState,
    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    onAddClothingSelected: () -> Unit,

    onEvent: (ItemEvent) -> Unit,
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
                    text = "Prendas superiores",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
                // Display top items
                if (itemState.items.isNotEmpty()) {
                    Log.d("ImagePrinted", "Printed image path: ${Uri.parse(itemState.items[0].imagePath)}")
                    DisplayCarouselTop(itemState.items)
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
                // Título Prendas inferiores
                Text(
                    text = "Prendas inferiores",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
                if (itemState.items.isNotEmpty()) {
                    DisplayCarouselBottom(itemState.items)
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
                // Display shoes items
                Text(
                    text = "Calzado",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
                if (itemState.items.isNotEmpty()) {
                    DisplayCarouselShoes(itemState.items)
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
            // Reset Button: NO HACE NADA AUN
            FloatingActionButton(
                onClick = {

                },
                modifier = Modifier
                    .padding(16.dp) // Add padding around FAB
                    .align(Alignment.TopEnd) // Position FAB bottom-right
            ) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Add Clothing")
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


@Composable
fun DisplayCarouselTop(items: List<Item>) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            Image(
                painter = rememberImagePainter(Uri.parse(item.imagePath)),
                contentDescription = item.clothingType,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }
    }
}

@Composable
fun DisplayCarouselBottom(items: List<Item>) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            if (item.clothingType == "Prenda inferior") {
                Image(
                    painter = rememberImagePainter(Uri.parse(item.imagePath)),
                    contentDescription = item.clothingType,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
            }

        }
    }
}


@Composable
fun DisplayCarouselShoes(items: List<Item>) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            if (item.clothingType == "Calzado") {
                Image(
                    painter = rememberImagePainter(Uri.parse(item.imagePath)),
                    contentDescription = item.clothingType,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )
            }

        }
    }
}

@Composable
fun DisplayItems(items: List<Item>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            Image(
                painter = rememberImagePainter(Uri.parse(item.imagePath)),
                contentDescription = item.clothingType,
                modifier = Modifier
                    .fillMaxSize()
            )
            Log.d("ImagePrinted", "Printed image path: ${item.imagePath}")
        }
    }
}

