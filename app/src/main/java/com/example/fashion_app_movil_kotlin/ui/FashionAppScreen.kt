package com.example.fashion_app_movil_kotlin.ui

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fashion_app_movil_kotlin.view_models.LoginViewModel
import com.example.fashion_app_movil_kotlin.view_models.RegisterViewModel
import androidx.navigation.compose.rememberNavController
import com.example.fashion_app_movil_kotlin.ui.login_register.Routes
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
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = Routes.LOGINREGISTER_SCREEN

    NavHost(
        navController = navController,
        startDestination = Routes.LOGINREGISTER_SCREEN,
        builder = {
            composable(route = Routes.LOGINREGISTER_SCREEN) {
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
                    onEvent = userViewModel::onEvent,
                    onUserValidNav = { // FALTA IMPLEMENTAR ESTA FUNCIÓN PARA CUANDO EL USUARIO SE VALIDE CORRECTAMENTE EN LOGINSCREEN
                        navController.navigate(Routes.HOME_SCREEN)
                    }
                )
            }
            composable(route = Routes.REGISTER_SCREEN) {
                RegisterScreen(
                    userViewModel = userViewModel,
                    onEvent = userViewModel::onEvent,
                    onUserCreatedNav = {
                        navController.navigate((Routes.LOGINREGISTER_SCREEN))
                    })
            }
        }
    )
}