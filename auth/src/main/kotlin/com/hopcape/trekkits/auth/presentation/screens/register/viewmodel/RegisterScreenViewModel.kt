
package com.hopcape.trekkits.auth.presentation.screens.register.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(RegisterScreenState())
    val state: StateFlow<RegisterScreenState> = _state

    fun onAction(action: RegisterScreenAction) {
        when (action) {
            is RegisterScreenAction.FirstNameChanged -> {
                _state.update { state -> state.copy(formState = state.formState.copy(firstName = action.value)) }
            }
            is RegisterScreenAction.EmailChanged -> {
                _state.update { state -> state.copy(formState = state.formState.copy(email = action.value)) }
            }
            is RegisterScreenAction.PasswordChanged -> {
                _state.update { state -> state.copy(formState = state.formState.copy(password = action.value)) }
            }

            is RegisterScreenAction.ConfirmPasswordChanged -> {
                _state.update { state -> state.copy(formState = state.formState.copy(confirmPassword = action.value)) }
            }

            else -> {}

        }
    }
}