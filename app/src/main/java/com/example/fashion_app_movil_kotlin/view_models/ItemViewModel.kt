package com.example.fashion_app_movil_kotlin.view_models


import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fashion_app_movil_kotlin.database.item.Item
import com.example.fashion_app_movil_kotlin.database.item.ItemDAO
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.states.ItemState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ItemViewModel(
    private val dao: ItemDAO,
    private val context: Context
) : ViewModel() {

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
            is ItemEvent.SaveItem -> {
                viewModelScope.launch {
                    val originalUri = Uri.parse(_state.value.imagePath)
                    val copiedUri = copyImageToAppStorage(context, originalUri)

                    val clothingType = _state.value.clothingType
                    val color = _state.value.color

                    val item = Item(
                        imagePath = copiedUri.toString(),
                        clothingType = clothingType,
                        color = color
                    )

                    dao.upsertItem(item)

                    _state.update {
                        it.copy(
                            imagePath = "",
                            clothingType = "",
                            color = "",
                        )
                    }
                }

                /* SaveItem viejo

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
                */
            }

            is ItemEvent.DeteleItem -> {
                viewModelScope.launch {
                    dao.deleteItem(event.item)
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

            is ItemEvent.GetItemsByClothingType -> {
                viewModelScope.launch {
                    dao.getItemsByClothingType(event.clothingType).collect() { items ->
                        // Update state with the filtered items
                        _state.update {
                            it.copy(
                                items = items
                            )
                        } // Assuming usage of collect() to retrieve the actual list
                    }
                }
            }
        }

    }
}

fun copyImageToAppStorage(context: Context, sourceUri: Uri): Uri? {
    val contentResolver = context.contentResolver

    // Obtener el nombre del archivo desde el URI original
    val fileName = contentResolver.query(sourceUri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        cursor.getString(nameIndex)
    } ?: return null

    // Crear un nuevo archivo en el almacenamiento específico de la aplicación
    val appSpecificStorage = File(context.filesDir, fileName)

    // Copiar el contenido del archivo original al nuevo archivo
    contentResolver.openInputStream(sourceUri)?.use { inputStream ->
        FileOutputStream(appSpecificStorage).use { outputStream ->
            copyStream(inputStream, outputStream)
        }
    }

    // Devolver la URI del nuevo archivo
    return Uri.fromFile(appSpecificStorage)
}

fun copyStream(input: InputStream, output: FileOutputStream) {
    val buffer = ByteArray(1024)
    var length: Int
    while (input.read(buffer).also { length = it } > 0) {
        output.write(buffer, 0, length)
    }
}


