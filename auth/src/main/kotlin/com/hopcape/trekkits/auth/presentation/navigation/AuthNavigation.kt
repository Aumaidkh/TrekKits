package com.hopcape.trekkits.auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hopcape.trekkits.auth.presentation.screens.LoginScreen

fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
){
    navigation<Auth>( startDestination = Login ){
        composable<Login> {
            LoginScreen()
        }

        composable<Register> {

        }

        composable<ForgotPassword> {

        }
    }
}