package com.example.fashion_app_movil_kotlin.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fashion_app_movil_kotlin.R
import com.example.fashion_app_movil_kotlin.events.ItemEvent
import com.example.fashion_app_movil_kotlin.ui.home.AddClothingScreen
import com.example.fashion_app_movil_kotlin.ui.home.ClosetScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.LoginRegisterScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.LoginScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.RegisterScreen
import com.example.fashion_app_movil_kotlin.view_models.ItemViewModel
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel


@Composable
fun FashionAppBody(
    modifier: Modifier = Modifier
) {
}

@Composable
fun FashionAppBottonBar() {

}

@Composable
fun FashionApp(
    userViewModel: UserViewModel = viewModel(),
    itemViewModel: ItemViewModel = viewModel(),

    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN_REGISTER_SCREEN,
        builder = {
            composable(route = Routes.LOGIN_REGISTER_SCREEN) {
                LoginRegisterScreen(
                    userViewModel = userViewModel,
                    onLoginSelected = {
                        navController.navigate(Routes.LOGIN_SCREEN)
                    },
                    onRegisterSelected = {
                        navController.navigate(Routes.REGISTER_SCREEN)
                    },
                )
            }
            composable(route = Routes.REGISTER_SCREEN) {
                RegisterScreen(
                    userViewModel = userViewModel,
                    onEvent = userViewModel::onEvent,
                    onUserCreatedNav = {
                        navController.navigate((Routes.LOGIN_REGISTER_SCREEN))
                    })
            }
            composable(route = Routes.LOGIN_SCREEN) {
                LoginScreen(
                    userViewModel = userViewModel,
                    onEvent = userViewModel::onEvent,
                    onUserValidNav = {
                        navController.navigate(Routes.CLOSET_SCREEN)
                    }
                )
            }
            composable(route = Routes.CLOSET_SCREEN) {
                ClosetScreen(
                    userViewModel = userViewModel,
                    // Toca mirar cómo manejar los events y states para las imágenes

                    // BottomBar
                    onClosetSelected = {
                        navController.navigate((Routes.CLOSET_SCREEN))
                    },
                    onCombinationsSelected = {
                        navController.navigate((Routes.COMBINATIONS_SCREEN))
                    },
                    onCalendarSelected = {
                        navController.navigate((Routes.CALENDAR_SCREEN))
                    },
                    onArchivedSelected = {
                        navController.navigate((Routes.ARCHIVED_SCREEN))
                    },
                    onProfileSelected = {
                        navController.navigate((Routes.PROFILE_SCREEN))
                    },
                    // Add button
                    onAddClothingSelected = {
                        navController.navigate((Routes.ADD_CLOTHING_SCREEN))
                    },
                )
            }
            composable(route = Routes.ADD_CLOTHING_SCREEN) {
                AddClothingScreen(
                    itemViewModel = itemViewModel,
                    // Toca mirar cómo manejar los events y states para las imágenes

                    // BottomBar
                    onClosetSelected = {
                        navController.navigate((Routes.CLOSET_SCREEN))
                    },
                    onCombinationsSelected = {
                        navController.navigate((Routes.COMBINATIONS_SCREEN))
                    },
                    onCalendarSelected = {
                        navController.navigate((Routes.CALENDAR_SCREEN))
                    },
                    onArchivedSelected = {
                        navController.navigate((Routes.ARCHIVED_SCREEN))
                    },
                    onProfileSelected = {
                        navController.navigate((Routes.PROFILE_SCREEN))
                    },
                    // Add button
                    onAddClothingSelected = {
                        navController.navigate((Routes.ADD_CLOTHING_SCREEN))
                    },

                    onAddItemSelected = {
                        navController.navigate((Routes.CLOSET_SCREEN))
                    },

                    onEvent = {
                        // FALTA HACER
                    },
                    onItemCreatedNav = {
                        // FALTA HACER
                    }
                )
            }
            composable(route = Routes.CLOSET_SCREEN) {
                ClosetScreen(
                    userViewModel = userViewModel,
                    // Toca mirar cómo manejar los events y states para las imágenes

                    // BottomBar
                    onClosetSelected = {
                        navController.navigate((Routes.CLOSET_SCREEN))
                    },
                    onCombinationsSelected = {
                        navController.navigate((Routes.COMBINATIONS_SCREEN))
                    },
                    onCalendarSelected = {
                        navController.navigate((Routes.CALENDAR_SCREEN))
                    },
                    onArchivedSelected = {
                        navController.navigate((Routes.ARCHIVED_SCREEN))
                    },
                    onProfileSelected = {
                        navController.navigate((Routes.PROFILE_SCREEN))
                    },
                    // Add button
                    onAddClothingSelected = {
                        navController.navigate((Routes.ADD_CLOTHING_SCREEN))
                    },
                )
            }
            composable(route = Routes.CLOSET_SCREEN) {
                ClosetScreen(
                    userViewModel = userViewModel,
                    // Toca mirar cómo manejar los events y states para las imágenes

                    // BottomBar
                    onClosetSelected = {
                        navController.navigate((Routes.CLOSET_SCREEN))
                    },
                    onCombinationsSelected = {
                        navController.navigate((Routes.COMBINATIONS_SCREEN))
                    },
                    onCalendarSelected = {
                        navController.navigate((Routes.CALENDAR_SCREEN))
                    },
                    onArchivedSelected = {
                        navController.navigate((Routes.ARCHIVED_SCREEN))
                    },
                    onProfileSelected = {
                        navController.navigate((Routes.PROFILE_SCREEN))
                    },
                    // Add button
                    onAddClothingSelected = {
                        navController.navigate((Routes.ADD_CLOTHING_SCREEN))
                    },
                )
            }
            composable(route = Routes.CLOSET_SCREEN) {
                ClosetScreen(
                    userViewModel = userViewModel,
                    // Toca mirar cómo manejar los events y states para las imágenes

                    // BottomBar
                    onClosetSelected = {
                        navController.navigate((Routes.CLOSET_SCREEN))
                    },
                    onCombinationsSelected = {
                        navController.navigate((Routes.COMBINATIONS_SCREEN))
                    },
                    onCalendarSelected = {
                        navController.navigate((Routes.CALENDAR_SCREEN))
                    },
                    onArchivedSelected = {
                        navController.navigate((Routes.ARCHIVED_SCREEN))
                    },
                    onProfileSelected = {
                        navController.navigate((Routes.PROFILE_SCREEN))
                    },
                    // Add button
                    onAddClothingSelected = {
                        navController.navigate((Routes.ADD_CLOTHING_SCREEN))
                    },
                )
            }
            composable(route = Routes.CLOSET_SCREEN) {
                ClosetScreen(
                    userViewModel = userViewModel,
                    // Toca mirar cómo manejar los events y states para las imágenes

                    // BottomBar
                    onClosetSelected = {
                        navController.navigate((Routes.CLOSET_SCREEN))
                    },
                    onCombinationsSelected = {
                        navController.navigate((Routes.COMBINATIONS_SCREEN))
                    },
                    onCalendarSelected = {
                        navController.navigate((Routes.CALENDAR_SCREEN))
                    },
                    onArchivedSelected = {
                        navController.navigate((Routes.ARCHIVED_SCREEN))
                    },
                    onProfileSelected = {
                        navController.navigate((Routes.PROFILE_SCREEN))
                    },
                    // Add button
                    onAddClothingSelected = {
                        navController.navigate((Routes.ADD_CLOTHING_SCREEN))
                    },
                )
            }
        }
    )
}

@Composable
fun BackgroundImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.background_image), // Replace with your image resource
        contentDescription = "Background",
        modifier = modifier
            .fillMaxSize() // Cover the entire screen
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_fashionapp),
        contentDescription = "Header",
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "¿Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF000000)
    )
}

@Composable
fun TopAppBarImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.top_bar_header),
        contentDescription = "Header",
        modifier = modifier
            .fillMaxWidth()
            .height(133.dp)
    )
}

@Composable
fun FashionAppBottomBar(
    modifier: Modifier = Modifier,
    onClosetSelected: () -> Unit,
    onCombinationsSelected: () -> Unit,
    onCalendarSelected: () -> Unit,
    onArchivedSelected: () -> Unit,
    onProfileSelected: () -> Unit,
    onAddClothingSelected: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton( // Closet Button
            onClick = {
                onClosetSelected()
            }
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = "Closet button"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        IconButton( // Combinations Button
            onClick = {
                onCombinationsSelected()
            }
        ) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "Combinations button"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        IconButton( // Calendar Button
            onClick = {
                onCalendarSelected()
            }
        ) {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Calendar button"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        IconButton( // Archived Button
            onClick = {
                onArchivedSelected()
            }
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Archived button"
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        IconButton( // Profile Button
            onClick = {
                onProfileSelected()
            }
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Profile button"
            )
        }
    }

}