package com.hopcape.trekkits.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginScreenViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun onAction(action: LoginScreenAction) {
        when (action) {
            is LoginScreenAction.EmailChanged -> {
                _state.value = _state.value.copy(
                    formState = _state.value.formState.copy(
                        email = action.email
                    )
                )
            }
            is LoginScreenAction.PasswordChanged -> {
                _state.value = _state.value.copy(
                    formState = _state.value.formState.copy(
                        password = action.password
                    )
                )
            }
            is LoginScreenAction.Login -> {
                login()
            }
        }
    }

    private fun login(){
        _state.value = _state.value.copy(
            displayState = DisplayState.Loading
        )

    }
}