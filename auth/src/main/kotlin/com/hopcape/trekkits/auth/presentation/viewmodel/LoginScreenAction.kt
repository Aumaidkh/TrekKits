package com.hopcape.trekkits.auth.presentation.viewmodel

sealed interface LoginScreenAction {
    data class EmailChanged(val email: String) : LoginScreenAction
    data class PasswordChanged(val password: String) : LoginScreenAction
    data object Login : LoginScreenAction
}