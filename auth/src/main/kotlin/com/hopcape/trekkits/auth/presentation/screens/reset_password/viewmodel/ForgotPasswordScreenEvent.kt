package com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel

sealed interface ForgotPasswordScreenEvent {
    data object NavigateToLogin : ForgotPasswordScreenEvent
    data object DismissBottomSheet : ForgotPasswordScreenEvent
}