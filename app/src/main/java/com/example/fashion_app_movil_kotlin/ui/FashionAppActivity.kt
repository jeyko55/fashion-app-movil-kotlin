package com.example.fashion_app_movil_kotlin.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fashion_app_movil_kotlin.ui.home.AddClothingScreen
import com.example.fashion_app_movil_kotlin.ui.home.ArchivedScreen
import com.example.fashion_app_movil_kotlin.ui.home.CalendarScreen
import com.example.fashion_app_movil_kotlin.ui.home.ClosetScreen
import com.example.fashion_app_movil_kotlin.ui.home.CombinationsScreen
import com.example.fashion_app_movil_kotlin.ui.home.ProfileScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.LoginRegisterScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.LoginScreen
import com.example.fashion_app_movil_kotlin.ui.login_register.RegisterScreen
import com.example.fashion_app_movil_kotlin.view_models.ItemViewModel
import com.example.fashion_app_movil_kotlin.view_models.UserItemViewModel
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel

@Composable
fun FashionApp(
    userViewModel: UserViewModel = viewModel(),
    itemViewModel: ItemViewModel = viewModel(),
    userItemViewModel: UserItemViewModel = viewModel(),

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
            composable(route = Routes.LOGIN_SCREEN) {
                LoginScreen(
                    userViewModel = userViewModel,
                    onUserEvent = userViewModel::onUserEvent,
                    onUserValidNav = {
                        navController.navigate(Routes.CLOSET_SCREEN)
                    }
                )
            }
            composable(route = Routes.REGISTER_SCREEN) {
                RegisterScreen(
                    userViewModel = userViewModel,
                    onUserEvent = userViewModel::onUserEvent,
                    onUserCreatedNav = {
                        navController.navigate((Routes.LOGIN_REGISTER_SCREEN))
                    }
                )
            }
            composable(route = Routes.CLOSET_SCREEN) {
                ClosetScreen(
                    userViewModel = userViewModel,
                    itemViewModel = itemViewModel,
                    userItemViewModel = userItemViewModel,

                    // Events
                    onUserEvent = userViewModel::onUserEvent,
                    onItemEvent = itemViewModel::onItemEvent,
                    onUserItemEvent = userItemViewModel::onUserItemEvent,

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
                    // ViewModels
                    userViewModel = userViewModel,
                    itemViewModel = itemViewModel,
                    userItemViewModel = userItemViewModel,
                    // Events
                    onUserEvent = userViewModel::onUserEvent,
                    onItemEvent = itemViewModel::onItemEvent,
                    onUserItemEvent = userItemViewModel::onUserItemEvent,

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

                    onItemCreatedNav = {
                        navController.navigate(Routes.CLOSET_SCREEN)
                    }
                )
            }
            composable(route = Routes.COMBINATIONS_SCREEN) {
                CombinationsScreen(
                    userViewModel = userViewModel,
                    itemViewModel = itemViewModel,
                    userItemViewModel = userItemViewModel,

                    onItemEvent = itemViewModel::onItemEvent,
                    onUserEvent = userViewModel::onUserEvent,
                    onUserItemEvent = userItemViewModel::onUserItemEvent,

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
            composable(route = Routes.CALENDAR_SCREEN) {
                CalendarScreen(

                )
            }
            composable(route = Routes.ARCHIVED_SCREEN) {
                ArchivedScreen(

                )
            }
            composable(route = Routes.PROFILE_SCREEN) {
                ProfileScreen(
                    // ViewModels
                    userViewModel = userViewModel,
                    itemViewModel = itemViewModel,
                    userItemViewModel = userItemViewModel,
                    // Events
                    onUserEvent = userViewModel::onUserEvent,
                    onItemEvent = itemViewModel::onItemEvent,
                    onUserItemEvent = userItemViewModel::onUserItemEvent,

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





