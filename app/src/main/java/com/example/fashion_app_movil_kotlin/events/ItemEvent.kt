package com.example.fashion_app_movil_kotlin.events

import android.media.Image
import com.example.fashion_app_movil_kotlin.database.item.Item

sealed interface ItemEvent {
    object SaveItem : ItemEvent
    data class SetImagePath(val imagePath: String) : ItemEvent
    data class SetClothingType(val clothingType: String) : ItemEvent
    data class SetColor(val color: String) : ItemEvent
    data class DeteleItem(val item: Item) : ItemEvent
}