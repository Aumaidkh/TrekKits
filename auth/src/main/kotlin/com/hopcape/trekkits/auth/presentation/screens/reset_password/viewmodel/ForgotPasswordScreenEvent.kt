package com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel

import com.hopcape.common.domain.base.presentation.BaseEvent
import com.hopcape.common.domain.wrappers.UiText

sealed interface ForgotPasswordScreenEvent: BaseEvent {
    data object NavigateToLogin : ForgotPasswordScreenEvent
    data class Error(val error: UiText): ForgotPasswordScreenEvent
}