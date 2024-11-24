package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

import com.hopcape.trekkits.auth.presentation.SheetContent

sealed interface LoginScreenEvents {
    data object NavigateToRegister : LoginScreenEvents
    data class ShowBottomSheet(val content: SheetContent): LoginScreenEvents
    data object NavigateToForgotPassword: LoginScreenEvents
}