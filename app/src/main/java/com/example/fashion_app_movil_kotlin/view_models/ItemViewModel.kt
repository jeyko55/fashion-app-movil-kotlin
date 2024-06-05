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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ItemViewModel(
    private val itemDao: ItemDAO,
    private val context: Context
) : ViewModel() {

    private val _items = itemDao.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ItemState())

    val state = combine(_state, _items) { state, items ->
        state.copy(
            items = items
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ItemState())

    fun onItemEvent(event: ItemEvent) {
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

                    itemDao.upsertItem(item)

                    _state.update {
                        it.copy(
                            imagePath = copiedUri.toString(),
                            clothingType = clothingType,
                            color = color,
                        )
                    }
                }
            }

            is ItemEvent.DeteleItem -> {
                viewModelScope.launch {
                    itemDao.deleteItem(event.item)
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
}




