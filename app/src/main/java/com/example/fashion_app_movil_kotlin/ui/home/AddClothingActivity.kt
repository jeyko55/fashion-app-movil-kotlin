package com.example.fashion_app_movil_kotlin.ui.home

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fashion_app_movil_kotlin.R
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.states.ItemState
import com.example.fashion_app_movil_kotlin.view_models.ItemViewModel
import coil.compose.rememberImagePainter
import com.example.fashion_app_movil_kotlin.events.UserEvent
import com.example.fashion_app_movil_kotlin.ui.components.*
import com.example.fashion_app_movil_kotlin.events.UserItemEvent
import com.example.fashion_app_movil_kotlin.states.UserItemState
import com.example.fashion_app_movil_kotlin.states.UserState
import com.example.fashion_app_movil_kotlin.view_models.UserItemViewModel
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel

@Composable
fun AddClothingScreen(
    // View Models
    userViewModel: UserViewModel,
    itemViewModel: ItemViewModel,
    userItemViewModel: UserItemViewModel,

    // Eventos locales
    onUserEvent: (UserEvent) -> Unit,
    onItemEvent: (ItemEvent) -> Unit,
    onUserItemEvent: (UserItemEvent) -> Unit,

    // BottomBar Icons Selections
    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    onAddClothingSelected: () -> Unit,

    onAddItemSelected: () -> Unit,

    // Navegación
    onItemCreatedNav: () -> Unit
) {
    val userState by userViewModel.state.collectAsState()
    val itemState by itemViewModel.state.collectAsState()
    val userItemState by userItemViewModel.state.collectAsState()

    Box(
        Modifier.fillMaxSize()
    ) {
        BackgroundImage(modifier = Modifier, resourceId = R.drawable.background_home_image)

        AddClothingPortrait(
            modifier = Modifier,

            userState,
            itemState,
            userItemState,
            // Eventos locales
            onUserEvent,
            onItemEvent,
            onUserItemEvent,

            onClosetSelected,
            onCombinationsSelected,
            onCalendarSelected,
            onArchivedSelected,
            onProfileSelected,
            onAddClothingSelected,

            onAddItemSelected,

            onItemCreatedNav
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClothingPortrait(
    modifier: Modifier,

    userState: UserState,
    itemState: ItemState,
    userItemState: UserItemState,

    // Eventos locales
    onUserEvent: (UserEvent) -> Unit,
    onItemEvent: (ItemEvent) -> Unit,
    onUserItemEvent: (UserItemEvent) -> Unit,

    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    onAddClothingSelected: () -> Unit,

    onAddItemSelected: () -> Unit,

    onItemCreatedNav: () -> Unit
) {
    val context = LocalContext.current // Get the current context
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts
            .StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                var selectedImageUri = data?.data // Get the selected image URI
                if (selectedImageUri != null) {
                    // OJO: Aquí se convierte a String para poderlo trabajar en la lógica pero hay que convertir a Uri.parse() luego
                    var selectedImage = selectedImageUri.toString()
                    Log.d("ImagePath", "Saved image path: $selectedImage")

                    onItemEvent(ItemEvent.SetImagePath(selectedImage))
                }
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBarImage(modifier = Modifier)
        },
        bottomBar = {
            FashionAppBottomBar(
                modifier = Modifier,
                onClosetSelected,
                onCombinationsSelected,
                onCalendarSelected,
                onArchivedSelected,
                onProfileSelected,
                onAddClothingSelected,
            )
        },
    ) { innerPadding ->
        Box( // Box for getting the item data
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(
                            Color.LightGray,
                            RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            val intent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )
                            intent.setType("image/*")
                            launcher.launch(intent)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // Display the default placeholder image if no image is selected
                    Image(
                        painter = painterResource(id = R.drawable.gallery_image),
                        contentDescription = "Add Clothing Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.2f))
                    )
                } // End of Box 1

                // Display the selected image if it's available
                if (itemState.imagePath.isNotBlank()) {
                    Image(
                        painter = rememberImagePainter(itemState.imagePath),
                        contentDescription = "Selected Clothing Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                } else {
                    // Display the default placeholder image if no image is selected
                    Image(
                        painter = painterResource(id = R.drawable.null_pointer_symbol),
                        contentDescription = "Add Clothing Image",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                }

                Box( // Box for getting the clothing type
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    // Clothing type dropdown menu
                    var expanded by remember { mutableStateOf(false) }
                    var selectedOptionText by remember { mutableStateOf("") }
                    val options = listOf("Prenda superior", "Prenda inferior", "Calzado")

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        OutlinedTextField(
                            value = selectedOptionText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Tipo de prenda") },
                            trailingIcon = {
                                androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.LightGray,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            },
                            modifier = Modifier
                                .background(
                                    Color.LightGray,
                                    shape = RoundedCornerShape(
                                        bottomEnd = 16.dp,
                                        bottomStart = 16.dp
                                    )
                                )
                        ) {
                            options.forEach { selectionOption ->
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        onItemEvent(ItemEvent.SetClothingType(selectionOption))
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                } // End of Box for getting the clothing type

                Spacer(modifier = Modifier.padding(4.dp))

                Box( // Box for getting the color
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    // Color dropdown menu
                    var expanded by remember { mutableStateOf(false) }
                    var selectedColorText by remember { mutableStateOf("") }
                    val colors = listOf(
                        "Rojo",
                        "Azul",
                        "Verde",
                        "Naranja",
                        "Amarillo",
                        "Rosado",
                        "Negro",
                        "Blanco",
                        "Gris"
                    )


                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        OutlinedTextField(
                            value = selectedColorText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Color") },
                            trailingIcon = {
                                androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.LightGray,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            },
                            modifier = Modifier
                                .background(
                                    Color.LightGray,
                                    shape = RoundedCornerShape(
                                        bottomEnd = 16.dp,
                                        bottomStart = 16.dp
                                    )
                                )
                        ) {
                            colors.forEach { color ->
                                androidx.compose.material3.DropdownMenuItem(
                                    text = { Text(color) },
                                    onClick = {
                                        selectedColorText = color
                                        expanded = false
                                        onItemEvent(ItemEvent.SetColor(color)) // Actualiza el color en el estado
                                    }
                                )
                            }
                        }
                    }
                } // End of Box for getting the color

                Spacer(modifier = Modifier.padding(20.dp))

                Button(
                    // 'Agregar prenda' Button
                    modifier = Modifier
                        .height(50.dp)
                        .width(250.dp),
                    // Color similar a las vistas de Figma
                    colors = ButtonDefaults.buttonColors(
                        disabledContentColor = Color.White,
                        contentColor = Color.White,
                        containerColor = Color(0xFF03A9F4),
                    ),
                    onClick = {
                        onItemEvent(ItemEvent.SaveItem) // Guarda el item en la tabla Item
                        restartItemState(onItemEvent)// Pone los campos en blanco
                        onItemCreatedNav()
                    },
                    enabled = isItemValid(itemState), // FALTA HACER
                ) {
                    Text(text = "Agregar prenda")
                } // End 'Agregar prenda' Button
            }
            // Escucha los cambios en el estado del item
            LaunchedEffect(itemState) {
                // IMPORTANTE: Aquí se guardan los datos de UserItem en la tabla intermedia UserItem
                val newItem = itemState.items.lastOrNull { it.imagePath == itemState.imagePath }
                if (newItem != null) {
                    val userActual = userState.users.find { it.email == userState.email }
                    onUserItemEvent(UserItemEvent.SetUserId(userActual!!.userId))
                    onUserItemEvent(UserItemEvent.SetItemId(newItem.itemId))
                    onUserItemEvent(UserItemEvent.SaveUserItem)
                }
            }
        }
    }
}

fun saveUserItem(onUserItemEvent: (UserItemEvent) -> Unit) {

}

fun isItemValid(state: ItemState): Boolean {
    return state.imagePath.isNotBlank()
            && state.clothingType.isNotBlank()
            && state.color.isNotBlank()
}

fun restartItemState(onEvent: (ItemEvent) -> Unit) {
    onEvent(ItemEvent.SetImagePath(""))
    onEvent(ItemEvent.SetClothingType(""))
    onEvent(ItemEvent.SetColor(""))
}