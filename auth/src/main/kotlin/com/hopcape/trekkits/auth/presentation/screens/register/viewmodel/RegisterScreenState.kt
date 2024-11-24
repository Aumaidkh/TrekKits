package com.hopcape.trekkits.auth.presentation.screens.register.viewmodel

import com.hopcape.common.domain.base.presentation.BaseState
import com.hopcape.trekkits.auth.presentation.SheetContent

data class RegisterScreenState(
    val formState: FormState = FormState(),
    val displayState: DisplayState = DisplayState.Initial
): BaseState{
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
        data class Success(val sheetContent: SheetContent): DisplayState
        data class Error(val message: String): DisplayState
    }
}
