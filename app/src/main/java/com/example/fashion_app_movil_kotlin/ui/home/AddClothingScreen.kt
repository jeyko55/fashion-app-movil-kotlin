package com.example.fashion_app_movil_kotlin.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fashion_app_movil_kotlin.R
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.states.ItemState
import com.example.fashion_app_movil_kotlin.ui.FashionAppBottomBar
import com.example.fashion_app_movil_kotlin.ui.TopAppBarImage
import com.example.fashion_app_movil_kotlin.view_models.ItemViewModel

@Composable
fun AddClothingScreen(
    itemViewModel: ItemViewModel,

    // BottomBar Icons Selections
    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    onAddClothingSelected: () -> Unit,

    onAddItemSelected: () -> Unit,

    // Eventos locales
    onEvent: (ItemEvent) -> Unit,
    onItemCreatedNav: () -> Unit
) {
    val itemState by itemViewModel.state.collectAsState()

    Box(
        Modifier.fillMaxSize()
    ) {
        AddClothingPortrait(
            modifier = Modifier,
            itemState,
            onClosetSelected,
            onCombinationsSelected,
            onCalendarSelected,
            onArchivedSelected,
            onProfileSelected,
            onAddClothingSelected,

            onAddItemSelected,

            onEvent,
            onItemCreatedNav
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClothingPortrait(
    modifier: Modifier,
    itemState: ItemState,
    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    onAddClothingSelected: () -> Unit,

    onAddItemSelected: () -> Unit,

    // Eventos locales
    onEvent: (ItemEvent) -> Unit,
    onItemCreatedNav: () -> Unit
) {
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
        Box( // Box for getting the image path
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
                        .height(100.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gallery_image),
                        contentDescription = "Add Clothing Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.2f)) // Añade un tinte oscuro a la imagen
                    )
                } // End of Box 1

                Spacer(modifier = Modifier.padding(16.dp))

                Box( // Bos for getting the clothing type
                    modifier = Modifier
                        .height(100.dp)
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
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                } // End of Box 2

                Spacer(modifier = Modifier.padding(16.dp))

                Box( // Box for getting the color
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {

                } // End Box 3

                Spacer(modifier = Modifier.padding(16.dp))

                Button( // 'Agregar prenda' Button
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp), // Color similar a las vistas de Figma
                    colors = ButtonDefaults.buttonColors(
                        disabledContentColor = Color.White,
                        contentColor = Color.White,
                        containerColor = Color(0xFF03A9F4),
                    ),
                    onClick = {
                        onEvent(ItemEvent.SaveItem) // FALTA HACER
                        onItemCreatedNav()
                    },
                    enabled = isItemValid(itemState), // FALTA HACER
                ) {
                    Text(text = "Registrarse")
                } // End 'Agregar prenda' Button
            }
        }
    }
}

fun isItemValid(state: ItemState): Boolean {
    return state.imagePath.isNotBlank()
            && state.clothingType.isNotBlank()
            && state.color.isNotBlank()
}



