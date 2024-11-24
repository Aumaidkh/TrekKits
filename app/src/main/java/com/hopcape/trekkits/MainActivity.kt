package com.hopcape.trekkits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hopcape.trekkits.auth.data.api.GoogleSignInService
import com.hopcape.trekkits.auth.presentation.navigation.Auth
import com.hopcape.trekkits.presentation.navigation.TrekKitsApp
import com.hopcape.trekkits.ui.theme.TrekKitsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var googleSignInService: GoogleSignInService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        googleSignInService.register(this@MainActivity)
        setContent {
            TrekKitsTheme {
                val navHostController = rememberNavController()
                TrekKitsApp(
                    modifier = Modifier
                        .fillMaxSize(),
                    navController = navHostController,
                    startDestination = Auth
                )
            }
        }
    }
}