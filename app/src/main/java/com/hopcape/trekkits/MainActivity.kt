package com.hopcape.trekkits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hopcape.trekkits.presentation.navigation.Routes
import com.hopcape.trekkits.presentation.navigation.TrekKitsApp
import com.hopcape.trekkits.ui.theme.TrekKitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrekKitsTheme {
                val navHostController = rememberNavController()
                TrekKitsApp(
                    modifier = Modifier
                        .fillMaxSize(),
                    navController = navHostController,
                    startDestination = Routes.HomeScreen
                )
            }
        }
    }
}