package com.example.fashion_app_movil_kotlin.states

import com.example.fashion_app_movil_kotlin.database.item.Item

data class ItemState(
    val items: List<Item> = emptyList(),
    val imagePath: String = "",
    val clothingType: String = "",
    val color: String = "",
)