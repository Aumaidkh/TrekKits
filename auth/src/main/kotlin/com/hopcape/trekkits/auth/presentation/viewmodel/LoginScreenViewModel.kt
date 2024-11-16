package com.hopcape.trekkits.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(): ViewModel() {
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

            LoginScreenAction.ForgotPassword -> TODO()
            LoginScreenAction.Register -> TODO()
            LoginScreenAction.SignInWithFacebook -> TODO()
            LoginScreenAction.SignInWithGoogle -> TODO()
        }
    }

    private fun login(){
        _state.value = _state.value.copy(
            displayState = DisplayState.Loading
        )

    }
}