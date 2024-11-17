package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

sealed interface LoginScreenEvents {
    data object NavigateToRegister : LoginScreenEvents
}