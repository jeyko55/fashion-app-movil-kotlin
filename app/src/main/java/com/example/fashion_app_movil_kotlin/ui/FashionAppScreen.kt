package com.example.fashion_app_movil_kotlin.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fashion_app_movil_kotlin.ui.home.HomeScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.LoginRegisterScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.LoginScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.RegisterScreen
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
                        navController.navigate(Routes.HOME_SCREEN)
                    }
                )
            }
            composable(route = Routes.HOME_SCREEN) {
                HomeScreen(
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