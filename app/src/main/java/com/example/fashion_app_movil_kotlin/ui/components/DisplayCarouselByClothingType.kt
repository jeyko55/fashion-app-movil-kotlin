package com.example.fashion_app_movil_kotlin.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.fashion_app_movil_kotlin.database.item.Item


@Composable
fun DisplayCarouselByClothingType(items: List<Item>, clothingType: String) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            if (item.clothingType == clothingType) {
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