package com.example.fashion_app_movil_kotlin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun BackgroundImage(modifier: Modifier, resourceId: Int) {
    Image(
        painter = painterResource(id = resourceId), // Replace with your image resource
        contentDescription = "Background",
        modifier = modifier
            .fillMaxSize() // Cover the entire screen
    )
}