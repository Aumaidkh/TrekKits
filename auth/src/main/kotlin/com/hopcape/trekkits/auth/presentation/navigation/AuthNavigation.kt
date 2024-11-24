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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
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
import com.hopcape.trekkits.auth.presentation.screens.register.viewmodel.RegisterScreenEvents
import com.hopcape.trekkits.auth.presentation.screens.register.viewmodel.RegisterScreenState
import com.hopcape.trekkits.auth.presentation.screens.register.viewmodel.RegisterScreenViewModel
import com.hopcape.trekkits.auth.presentation.screens.reset_password.ForgotPasswordScreen
import com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel.DisplayState
import com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel.ForgotPasswordScreenEvent
import com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel.ForgotPasswordScreenViewModel

@OptIn(ExperimentalMaterial3Api::class) fun NavGraphBuilder.authNavigation(
    navController: NavHostController,
    onAuthenticationSuccess: () -> Unit
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
            val viewModel = hiltViewModel<LoginScreenViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val event by viewModel.events.collectAsStateWithLifecycle(null)

            val scrollState = rememberScrollState()
            val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = SheetState(
                    skipPartiallyExpanded = false,
                    density = LocalDensity.current,
                    initialValue = SheetValue.Hidden
                )
            )
            LaunchedEffect(state.displayState){
                when(state.displayState){
                    is com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.DisplayState.Success -> {
                        onAuthenticationSuccess()
                    }
                    else -> Unit
                }
            }
            LaunchedEffect(event){
                when(event){
                    is LoginScreenEvents.NavigateToRegister -> navController.navigate(Register)
                    is LoginScreenEvents.NavigateToForgotPassword -> navController.navigate(ForgotPassword)
                    is LoginScreenEvents.ShowBottomSheet -> {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                    is LoginScreenEvents.DismissBottomSheet -> {
                        bottomSheetScaffoldState.bottomSheetState.hide()
                    }
                    else -> Unit
                }
            }
            LoginScreen(
                scrollState = scrollState,
                bottomSheetScaffoldState = bottomSheetScaffoldState,
                screenState = state,
                onAction = viewModel::onAction,
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
            val events by viewModel.events.collectAsStateWithLifecycle(null)

            val scrollState = rememberScrollState()
            val bottomSheetState = rememberBottomSheetScaffoldState(
                bottomSheetState = SheetState(
                    skipPartiallyExpanded = false,
                    density = LocalDensity.current,
                    initialValue = SheetValue.Hidden
                )
            )

            LaunchedEffect(state.displayState){
                when(state.displayState){
                    is RegisterScreenState.DisplayState.Success -> {
                        bottomSheetState.bottomSheetState.expand()
                    }
                    else -> Unit
                }
            }

            LaunchedEffect(events){
                when(events){
                    is RegisterScreenEvents.DismissBottomSheet -> bottomSheetState.bottomSheetState.hide()
                    else -> Unit
                }
            }

            RegisterScreen(
                modifier = Modifier
                    .fillMaxSize(),
                scrollState = scrollState,
                bottomSheetScaffoldState = bottomSheetState,
                screenState = state,
                onAction = viewModel::onAction
            )
        }

        composable<ForgotPassword> {
            val viewModel = hiltViewModel<ForgotPasswordScreenViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val event by viewModel.event.collectAsStateWithLifecycle(null)
            val scrollState = rememberScrollState()
            val bottomSheetState = rememberBottomSheetScaffoldState(
                bottomSheetState = SheetState(
                    skipPartiallyExpanded = false,
                    density = LocalDensity.current,
                    initialValue = SheetValue.Hidden
                )
            )
            LaunchedEffect(event){
                when(event){
                    is ForgotPasswordScreenEvent.NavigateToLogin -> {
                        if (bottomSheetState.bottomSheetState.hasExpandedState) {
                            bottomSheetState.bottomSheetState.hide()
                        }
                        navController.popBackStack()
                    }
                    else -> Unit
                }
            }

            LaunchedEffect(state.displayState){
                when(state.displayState){
                    is DisplayState.Success -> {
                        bottomSheetState.bottomSheetState.expand()
                    }
                    else -> Unit
                }
            }

            ForgotPasswordScreen(
                modifier = Modifier
                    .fillMaxSize(),
                scrollState = scrollState,
                state = state,
                onAction = viewModel::onAction,
                bottomSheetScaffoldState = bottomSheetState
            )
        }
    }
}