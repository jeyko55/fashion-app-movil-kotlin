package com.example.fashion_app_movil_kotlin.ui.home

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.navigation.NavController
import com.example.fashion_app_movil_kotlin.R
import com.example.fashion_app_movil_kotlin.states.UserState
import com.example.fashion_app_movil_kotlin.ui.Routes
import com.example.fashion_app_movil_kotlin.ui.login_register.BackgroundImage
import com.example.fashion_app_movil_kotlin.ui.login_register.LoginRegisterPortrait
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel

@Composable
fun HomeScreen(
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

    Box(
        Modifier
            .fillMaxSize()

    ) {
        Box {
            HomePortrait(
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
}

@Composable
fun HomePortrait(
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
                    text = "Aquí va el cuerpo de cada vista",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun TopAppBarImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.top_bar_header),
        contentDescription = "Header",
        modifier = modifier
            .fillMaxWidth()
            .height(133.dp)
    )
}

@Composable
fun FashionAppBottomBar(
    modifier: Modifier = Modifier,
    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    onAddClothingSelected: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton( // Closet Button
            onClick = {
                onClosetSelected()
            }
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = "Closet button"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        IconButton( // Combinations Button
            onClick = {
                onCombinationsSelected()
            }
        ) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "Combinations button"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        IconButton( // Calendar Button
            onClick = {
                onCalendarSelected()
            }
        ) {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Calendar button"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        IconButton( // Archived Button
            onClick = {
                onArchivedSelected()
            }
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Archived button"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        IconButton( // Profile Button
            onClick = {
                onProfileSelected()
            }
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Profile button"
            )
        }
    }

}