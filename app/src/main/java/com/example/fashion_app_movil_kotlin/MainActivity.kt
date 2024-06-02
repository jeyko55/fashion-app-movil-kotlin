package com.example.fashion_app_movil_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.fashion_app_movil_kotlin.database.FashionAppDatabase
import com.example.fashion_app_movil_kotlin.ui.FashionApp
import com.example.fashion_app_movil_kotlin.ui.theme.FashionappmovilkotlinTheme
import com.example.fashion_app_movil_kotlin.view_models.ItemViewModel
import com.example.fashion_app_movil_kotlin.view_models.UserViewModel
import kotlinx.coroutines.*



class MainActivity : ComponentActivity() {

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            FashionAppDatabase::class.java,
            "FashionApp_Database"
        ).build()
    }

    val userViewModel by viewModels<UserViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserViewModel(db.userDao) as T
                }
            }
        }
    )
    val itemViewModel by viewModels<ItemViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ItemViewModel(db.itemDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FashionappmovilkotlinTheme {
                FashionApp(
                    userViewModel = userViewModel,
                    itemViewModel = itemViewModel
                )
            }
        }
    }
}

