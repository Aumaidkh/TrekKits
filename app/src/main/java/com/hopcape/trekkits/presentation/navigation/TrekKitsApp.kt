package com.hopcape.trekkits.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hopcape.trekkits.auth.presentation.navigation.Auth
import com.hopcape.trekkits.auth.presentation.navigation.authNavigation

@Composable
fun TrekKitsApp(modifier: Modifier = Modifier,navController: NavHostController = rememberNavController(),startDestination: Auth = Auth) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable<Routes.HomeScreen>{
            Text(modifier = Modifier
                .padding(top = 100.dp)
                .clickable { navController.navigate(Auth) },
                text = "Home Screen")
        }
        authNavigation(
            navController = navController
        )
    }
}