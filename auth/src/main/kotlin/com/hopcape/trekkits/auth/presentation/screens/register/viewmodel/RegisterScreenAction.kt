package com.hopcape.trekkits.auth.presentation.screens.register.viewmodel

import com.hopcape.common.domain.base.presentation.BaseAction

sealed class RegisterScreenAction: BaseAction {
    data class FirstNameChanged(val value: String) : RegisterScreenAction()
    data class EmailChanged(val value: String) : RegisterScreenAction()
    data class PasswordChanged(val value: String) : RegisterScreenAction()
    data class ConfirmPasswordChanged(val value: String) : RegisterScreenAction()
    data object Register : RegisterScreenAction()
    data object Login : RegisterScreenAction()
    data object SignInWithGoogle : RegisterScreenAction()
    data object SignInWithFacebook : RegisterScreenAction()
    data object OnBottomSheetButtonClick: RegisterScreenAction()
}