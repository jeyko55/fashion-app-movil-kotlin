package com.example.fashion_app_movil_kotlin.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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