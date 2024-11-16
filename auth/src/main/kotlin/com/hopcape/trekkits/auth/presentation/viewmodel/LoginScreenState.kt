package com.hopcape.trekkits.auth.presentation.viewmodel

data class LoginScreenState(
    val formState: FormState = FormState(),
    val displayState: DisplayState? = null
)

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
