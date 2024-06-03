package com.example.fashion_app_movil_kotlin.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.fashion_app_movil_kotlin.states.UserState
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel
import com.example.fashion_app_movil_kotlin.ui.components.*

@Composable
fun ClosetScreen(
    userViewModel: UserViewModel,

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

    Box (
        Modifier.fillMaxSize()){
        ClosetPortrait(
            modifier = Modifier,
            userState,
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
    userState: UserState,
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
            Column {
                // Center content - App interface
                // Replace with your actual app interface composable
                Text(
                    text = "AQUÍ VA EL CUERPO DE CADA VISTA",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
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



