package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

sealed interface LoginScreenAction {
    data class EmailChanged(val email: String) : LoginScreenAction
    data class PasswordChanged(val password: String) : LoginScreenAction
    data object Login : LoginScreenAction
    data object Register: LoginScreenAction
    data object ForgotPassword: LoginScreenAction
    data object SignInWithGoogle: LoginScreenAction
    data object SignInWithFacebook: LoginScreenAction
}