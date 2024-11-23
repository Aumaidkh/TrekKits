
package com.hopcape.trekkits.auth.presentation.screens.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.domain.errors.AuthError
import com.hopcape.trekkits.auth.domain.models.User
import com.hopcape.trekkits.auth.domain.usecase.RegisterUseCase
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.DisplayState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterScreenState())
    val state: StateFlow<RegisterScreenState> = _state

    fun onAction(action: RegisterScreenAction) {
        when (action) {
            is RegisterScreenAction.FirstNameChanged -> {
                _state.update { state -> state.copy(formState = state.formState.copy(firstName = action.value, firstNameError = null)) }
            }
            is RegisterScreenAction.EmailChanged -> {
                _state.update { state -> state.copy(formState = state.formState.copy(email = action.value, emailError = null)) }
            }
            is RegisterScreenAction.PasswordChanged -> {
                _state.update { state -> state.copy(formState = state.formState.copy(password = action.value, passwordError = null)) }
            }

            is RegisterScreenAction.ConfirmPasswordChanged -> {
                _state.update { state -> state.copy(formState = state.formState.copy(confirmPassword = action.value, confirmPasswordError = null)) }
            }

            is RegisterScreenAction.Register -> {
                register(
                    user = User(
                        name = _state.value.formState.firstName,
                        email = _state.value.formState.email
                    ),
                    password = _state.value.formState.password,
                    confirmPassword = _state.value.formState.confirmPassword
                )
            }
            else -> {}

        }
    }

    private fun register(user: User,password: String,confirmPassword: String){
        registerUseCase(
            user = user,
            password = password,
            confirmPassword = confirmPassword
        ).onEach { emission ->
            when(emission){
                is UseCaseResult.Error -> { _state.update { it.copy(displayState = RegisterScreenState.DisplayState.Initial) }; handleError(emission.error) }
                is UseCaseResult.Loading -> { _state.update { it.copy(displayState = RegisterScreenState.DisplayState.Loading) } }
                is UseCaseResult.Success -> { _state.update { it.copy(displayState = RegisterScreenState.DisplayState.Success) } }
            }
        }.launchIn(viewModelScope)
    }

    private fun handleError(error: AuthError){
        when(error){
            AuthError.SOMETHING_WENT_WRONG -> { _state.update { state -> state.copy(displayState = RegisterScreenState.DisplayState.Error(message = "Something went wrong")) } }
            AuthError.INVALID_EMAIL -> { _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Invalid email")) } }
            AuthError.INVALID_PASSWORD -> { _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Invalid password")) } }
            AuthError.EMPTY_EMAIL -> { _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Email cannot be empty")) } }
            AuthError.EMPTY_PASSWORD -> { _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Password cannot be empty")) } }
            AuthError.INVALID_CREDENTIALS -> { _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Invalid username or password")) } }
            AuthError.PASSWORDS_DONT_MATCH -> { _state.update { state -> state.copy(formState = state.formState.copy(confirmPasswordError = "Passwords don't match")) } }
            AuthError.EMPTY_NAME -> { _state.update { state -> state.copy(formState = state.formState.copy(firstNameError = "Name cannot be empty")) } }
            AuthError.NAME_CONTAINS_DIGIT -> { _state.update { state -> state.copy(formState = state.formState.copy(firstNameError = "Name can't contain a digit")) } }
            AuthError.INVALID_NAME -> { _state.update { state -> state.copy(formState = state.formState.copy(firstNameError = "Invalid name")) } }
            else -> {}
        }
    }
}