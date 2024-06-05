package com.example.fashion_app_movil_kotlin.events

import com.example.fashion_app_movil_kotlin.database.item.Item

sealed interface ItemEvent {
    object SaveItem : ItemEvent
    data class DeteleItem(val item: Item) : ItemEvent
    data class SetImagePath(val imagePath: String) : ItemEvent
    data class SetClothingType(val clothingType: String) : ItemEvent
    data class SetColor(val color: String) : ItemEvent
}