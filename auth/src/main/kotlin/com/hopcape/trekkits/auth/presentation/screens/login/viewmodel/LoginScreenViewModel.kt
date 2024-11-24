package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.domain.errors.AuthDomainError
import com.hopcape.trekkits.auth.domain.usecase.LoginUseCase
import com.hopcape.trekkits.auth.presentation.SheetContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    private val _events = Channel<LoginScreenEvents>()
    val events = _events.receiveAsFlow()

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

            LoginScreenAction.ForgotPassword -> sendEvent(LoginScreenEvents.NavigateToForgotPassword)
            LoginScreenAction.Register -> sendEvent(LoginScreenEvents.NavigateToRegister)
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

    private fun sendEvent(event: LoginScreenEvents){
        viewModelScope.launch {
            _events.send(event)
        }
    }

    private fun handleError(error: AuthDomainError) {
        when(error){
            AuthDomainError.SOMETHING_WENT_WRONG -> {
                _state.update { state -> state.copy(displayState = DisplayState.Error(message = "Something went wrong")) }
            }

            AuthDomainError.INVALID_EMAIL -> {
                _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Invalid email")) }
            }

            AuthDomainError.INVALID_PASSWORD -> {
                _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Invalid password")) }
            }

            AuthDomainError.EMPTY_EMAIL -> {
                _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Email cannot be empty")) }
            }

            AuthDomainError.EMPTY_PASSWORD -> {
                _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Password cannot be empty")) }
            }

            AuthDomainError.INVALID_CREDENTIALS -> {
                _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Invalid username or password")) }
            }

            AuthDomainError.EMAIL_NOT_VERIFIED -> {
                sendEvent(
                    LoginScreenEvents.ShowBottomSheet(
                        content = SheetContent(
                            title = "Email not verified",
                            body = "Looks like you didn't verify your email yet. Please check your inbox and verify your email",
                            button = "Dismiss"
                        )
                    )
                )
            }
            else -> Unit
        }
        _state.update { state -> state.copy(displayState = DisplayState.Error(message = "Something went wrong")) }
    }
}