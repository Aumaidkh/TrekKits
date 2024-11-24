package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

import com.hopcape.common.domain.base.presentation.BaseState

data class LoginScreenState(
    val formState: FormState = FormState(),
    val displayState: DisplayState? = null
): BaseState

data class FormState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null
)

sealed interface DisplayState{
    data object Loading: DisplayState
    data class Error(val message: String): DisplayState
    data object Success: DisplayState
}
