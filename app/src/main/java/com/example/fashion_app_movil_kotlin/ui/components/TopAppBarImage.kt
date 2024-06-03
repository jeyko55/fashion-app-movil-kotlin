package com.example.fashion_app_movil_kotlin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fashion_app_movil_kotlin.R

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