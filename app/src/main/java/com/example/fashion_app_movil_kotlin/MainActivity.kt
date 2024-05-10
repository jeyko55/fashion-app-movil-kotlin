package com.example.fashion_app_movil_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fashion_app_movil_kotlin.ui.theme.FashionappmovilkotlinTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FashionappmovilkotlinTheme {
                /*
                FashionApp(

                )
                */

            }
        }
    }
}

