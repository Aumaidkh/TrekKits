package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hopcape.common.domain.base.presentation.BaseState

data class LoginScreenState(
    val formState: FormState = FormState(),
    val displayState: DisplayState? = null
): BaseState

fun LoginScreenState.toPasswordIconResId(): Int {
    return if (formState.showPassword){
        com.hopcape.designsystem.R.drawable.ic_eye
    } else {
        com.hopcape.designsystem.R.drawable.ic_eye_slash
    }
}

fun LoginScreenState.toVisualTransformation(): VisualTransformation {
    return if (formState.showPassword){
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
}

data class FormState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val showPassword: Boolean = false
)

sealed interface DisplayState{
    data object Loading: DisplayState
    data class Error(val message: String): DisplayState
    data object Success: DisplayState
}
