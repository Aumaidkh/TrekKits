package com.hopcape.trekkits.auth.presentation.screens.register.viewmodel

import com.hopcape.common.domain.base.presentation.BaseEvent
import com.hopcape.common.domain.wrappers.UiText

interface RegisterScreenEvents: BaseEvent {
    data object NavigateBack: RegisterScreenEvents
    data object DismissBottomSheet: RegisterScreenEvents
    data class Error(val error: UiText): RegisterScreenEvents
}