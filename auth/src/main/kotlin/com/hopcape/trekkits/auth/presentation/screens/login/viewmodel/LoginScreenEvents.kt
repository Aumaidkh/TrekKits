package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

import com.hopcape.common.domain.base.presentation.BaseEvent
import com.hopcape.common.domain.wrappers.UiText
import com.hopcape.trekkits.auth.presentation.SheetContent

sealed interface LoginScreenEvents: BaseEvent {
    data object NavigateToRegister : LoginScreenEvents
    data class ShowBottomSheet(val content: SheetContent): LoginScreenEvents
    data object NavigateToForgotPassword: LoginScreenEvents
    data object DismissBottomSheet: LoginScreenEvents
    data class Error(val error: UiText): LoginScreenEvents
}