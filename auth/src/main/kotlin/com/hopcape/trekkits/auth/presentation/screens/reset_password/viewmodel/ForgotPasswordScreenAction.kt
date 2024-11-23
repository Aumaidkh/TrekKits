package com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel

sealed interface ForgotPasswordScreenAction {
    data class EmailChanged(val email: String) : ForgotPasswordScreenAction
    data object Submit : ForgotPasswordScreenAction
    data object OnBackClick : ForgotPasswordScreenAction
    data object OnBottomSheetButtonClick: ForgotPasswordScreenAction
}