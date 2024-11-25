package com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel

import com.hopcape.common.domain.base.presentation.BaseState
import com.hopcape.trekkits.auth.presentation.SheetContent

data class FormState(
    val email: String = "",
    val emailError: String? = null
)

data class ForgotPasswordScreenState(
    val formState: FormState = FormState(),
    val displayState: DisplayState = DisplayState.Initial
): BaseState

sealed interface DisplayState {
    data object Initial : DisplayState
    data object Loading : DisplayState
    data class Success(
        val sheetContent: SheetContent
    ) : DisplayState
    data class Error(val message: String) : DisplayState

    fun isLoading() =
        this is Loading
}