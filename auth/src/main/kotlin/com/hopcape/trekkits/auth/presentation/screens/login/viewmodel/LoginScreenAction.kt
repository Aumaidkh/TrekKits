package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

import com.hopcape.common.domain.base.presentation.BaseAction

sealed interface LoginScreenAction: BaseAction {
    data class EmailChanged(val email: String) : LoginScreenAction
    data class PasswordChanged(val password: String) : LoginScreenAction
    data object Login : LoginScreenAction
    data object Register: LoginScreenAction
    data object ForgotPassword: LoginScreenAction
    data object SignInWithGoogle: LoginScreenAction
    data object SignInWithFacebook: LoginScreenAction
    data object OnBottomSheetButtonClick: LoginScreenAction
    data object ShowHidePassword: LoginScreenAction
}