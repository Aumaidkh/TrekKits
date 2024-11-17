package com.hopcape.trekkits.auth.presentation.screens.register.viewmodel

data class RegisterScreenState(
    val formState: FormState = FormState(),
    val displayState: DisplayState = DisplayState.Initial
){
    data class FormState(
        val firstName: String = "",
        val firstNameError: String? = null,
        val email: String = "",
        val emailError: String? = null,
        val password: String = "",
        val passwordError: String? = null,
        val confirmPassword: String = "",
        val confirmPasswordError: String? = null
    )

    sealed interface DisplayState{
        data object Initial: DisplayState
        data object Loading: DisplayState
        data object Success: DisplayState
        data class Error(val message: String): DisplayState
    }
}
