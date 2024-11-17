package com.hopcape.trekkits.auth.presentation.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hopcape.trekkits.auth.presentation.screens.login.LoginScreen
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.LoginScreenEvents
import com.hopcape.trekkits.auth.presentation.screens.register.RegisterScreen
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.LoginScreenViewModel
import com.hopcape.trekkits.auth.presentation.screens.register.viewmodel.RegisterScreenViewModel

fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
){
    navigation<Auth>( startDestination = Login ){
        composable<Login>(
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(durationMillis = 500))
            }
        ) {
            val scrollState = rememberScrollState()
            val viewModel = hiltViewModel<LoginScreenViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val event by viewModel.events.collectAsStateWithLifecycle(null)
            LaunchedEffect(event){
                when(event){
                    LoginScreenEvents.NavigateToRegister -> navController.navigate(Register)
                    else -> Unit
                }
            }
            LoginScreen(
                scrollState = scrollState,
                screenState = state,
                onAction = viewModel::onAction
            )
        }

        composable<Register>(
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(durationMillis = 500))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(durationMillis = 500))
            }
        ) {
            val viewModel = hiltViewModel<RegisterScreenViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val scrollState = rememberScrollState()
            RegisterScreen(
                modifier = Modifier
                    .fillMaxSize(),
                scrollState = scrollState,
                screenState = state,
                onAction = viewModel::onAction
            )
        }

        composable<ForgotPassword> {

        }
    }
}