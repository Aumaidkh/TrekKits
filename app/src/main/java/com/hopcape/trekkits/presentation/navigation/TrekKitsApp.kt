package com.hopcape.trekkits.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TrekKitsApp(modifier: Modifier = Modifier,navController: NavHostController = rememberNavController(),startDestination: Routes = Routes.HomeScreen) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable<Routes.HomeScreen>{
            Text("Home Screen")
        }
    }
}