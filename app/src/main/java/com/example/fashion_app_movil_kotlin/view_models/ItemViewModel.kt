package com.example.fashion_app_movil_kotlin.view_models


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fashion_app_movil_kotlin.database.item.Item
import com.example.fashion_app_movil_kotlin.database.item.ItemDAO
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.states.ItemState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemViewModel(
    private val dao: ItemDAO
) : ViewModel(){

    private val _items = dao.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ItemState())

    val state = combine(_state, _items) { state, items ->
        state.copy(
            items = items
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ItemState())

    fun onEvent(event: ItemEvent) {
        when (event) {
            is ItemEvent.DeteleItem -> {
                viewModelScope.launch {
                    dao.deleteItem(event.item)
                }
            }

            is ItemEvent.SaveItem -> {
                val imagePath = _state.value.imagePath
                val clothingType = _state.value.clothingType
                val color = _state.value.color
                val item = Item(
                    imagePath = imagePath,
                    clothingType = clothingType,
                    color = color
                )
                viewModelScope.launch {
                    dao.upsertItem(item)
                }
                _state.update {
                    it.copy(
                        imagePath = "",
                        clothingType = "",
                        color = "",
                    )
                }
            }

            is ItemEvent.SetImagePath -> {
                _state.update {
                    it.copy(
                        imagePath = event.imagePath
                    )
                }
            }

            is ItemEvent.SetClothingType -> {
                _state.update {
                    it.copy(
                        clothingType = event.clothingType
                    )
                }
            }

            is ItemEvent.SetColor -> {
                _state.update {
                    it.copy(
                        color = event.color
                    )
                }
            }
        }
    }
}


