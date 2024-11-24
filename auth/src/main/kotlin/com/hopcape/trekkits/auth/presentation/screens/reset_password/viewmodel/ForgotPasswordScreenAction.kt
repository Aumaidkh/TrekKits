package com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel

import com.hopcape.common.domain.base.presentation.BaseAction

sealed interface ForgotPasswordScreenAction: BaseAction {
    data class EmailChanged(val email: String) : ForgotPasswordScreenAction
    data object Submit : ForgotPasswordScreenAction
    data object OnBackClick : ForgotPasswordScreenAction
    data object OnBottomSheetButtonClick: ForgotPasswordScreenAction
}