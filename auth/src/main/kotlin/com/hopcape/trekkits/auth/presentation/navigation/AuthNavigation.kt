package com.hopcape.trekkits.auth.presentation.navigation

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hopcape.trekkits.auth.presentation.screens.LoginScreen
import com.hopcape.trekkits.auth.presentation.viewmodel.LoginScreenViewModel

fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
){
    navigation<Auth>( startDestination = Login ){
        composable<Login> {
            val scrollState = rememberScrollState()
            val viewModel = hiltViewModel<LoginScreenViewModel>()
            val state by viewModel.state.collectAsState()
            LoginScreen(
                scrollState = scrollState,
                screenState = state,
                onAction = viewModel::onAction
            )
        }

        composable<Register> {

        }

        composable<ForgotPassword> {

        }
    }
}