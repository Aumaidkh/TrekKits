package com.hopcape.trekkits.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.domain.errors.AuthError
import com.hopcape.trekkits.auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
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
        loginUseCase(
            email = _state.value.formState.email,
            password = _state.value.formState.password
        ).onEach { result ->
            when (result) {
                is UseCaseResult.Error -> {
                    handleError(result.error)
                }
                is UseCaseResult.Loading -> {
                    _state.update { state -> state.copy(displayState = DisplayState.Loading) }
                }
                is UseCaseResult.Success -> {
                    _state.update { state -> state.copy(displayState = DisplayState.Success) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun handleError(error: AuthError){
        when(error){
            AuthError.SOMETHING_WENT_WRONG -> { _state.update { state -> state.copy(displayState = DisplayState.Error(message = "Something went wrong")) } }
            AuthError.INVALID_EMAIL -> { _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Invalid email")) } }
            AuthError.INVALID_PASSWORD -> { _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Invalid password")) } }
            AuthError.EMPTY_EMAIL -> { _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Email cannot be empty")) } }
            AuthError.EMPTY_PASSWORD -> { _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Password cannot be empty")) } }
            AuthError.INVALID_CREDENTIALS -> { _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Invalid username or password")) } }
        }
    }
}