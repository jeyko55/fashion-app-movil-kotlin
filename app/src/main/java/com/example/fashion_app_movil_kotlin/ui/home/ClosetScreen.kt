package com.example.fashion_app_movil_kotlin.ui.home

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.states.ItemState
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel
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

    ) {
    // Importante ver si se necesita userState o itemState en los otros Screens
    val itemState by itemViewModel.state.collectAsState()

    Box(
        Modifier.fillMaxSize()
    ) {
        ClosetPortrait(
            modifier = Modifier,
            itemState,
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
    itemState: ItemState,
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
            ){
                Box {
                    Text(
                        text = "Prendas superiores",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                    // For printing the 'Prendas superiores' image list
                    DisplayItemsByClothingType(
                        itemState,
                        "Prenda superior"
                    )
                }
                Box {
                    Text(
                        text = "Prendas inferiores",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                    // For printing the 'Prendas superiores' image list
                    DisplayItemsByClothingType(
                        itemState,
                        "Prenda inferior"
                    )
                }

                Box {
                    Text(
                        text = "Calzado",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                    // For printing the 'Prendas superiores' image list
                    DisplayItemsByClothingType(
                        itemState,
                        "Calzado"
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


@Composable
fun DisplayItemsByClothingType(itemState: ItemState, clotingType: String) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var itemsFiltered = itemState.items.filter { it.clothingType == clotingType }
        items(itemsFiltered) { itemFiltered ->
            Image(
                painter = rememberImagePainter(itemFiltered.imagePath),
                contentDescription = itemFiltered.clothingType,
                modifier = Modifier
                    .fillMaxSize()
            )
            Log.d("ImagePrinted", "Printed image path: ${itemFiltered.imagePath}")
        }
    }
}

