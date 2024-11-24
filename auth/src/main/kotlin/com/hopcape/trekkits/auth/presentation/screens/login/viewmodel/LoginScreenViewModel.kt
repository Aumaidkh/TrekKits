package com.hopcape.trekkits.auth.presentation.screens.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.hopcape.common.domain.base.presentation.BaseViewModel
import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError
import com.hopcape.trekkits.auth.asUiText
import com.hopcape.trekkits.auth.domain.usecase.LoginUseCase
import com.hopcape.trekkits.auth.domain.validation.EmailValidator
import com.hopcape.trekkits.auth.domain.validation.PasswordValidator
import com.hopcape.trekkits.auth.presentation.SheetContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
) : BaseViewModel<LoginScreenState, LoginScreenAction, LoginScreenEvents>(
    initialState = LoginScreenState()
) {

    override fun onAction(action: LoginScreenAction) {
        when (action) {
            is LoginScreenAction.EmailChanged -> handleEmailChange(
                email = action.email
            )
            is LoginScreenAction.PasswordChanged -> handlePasswordChange(
                password = action.password
            )
            is LoginScreenAction.Login -> validateAndLogin()
            is LoginScreenAction.ForgotPassword -> sendEvent(LoginScreenEvents.NavigateToForgotPassword)
            is LoginScreenAction.Register -> sendEvent(LoginScreenEvents.NavigateToRegister)
            is LoginScreenAction.SignInWithFacebook -> TODO()
            is LoginScreenAction.SignInWithGoogle -> TODO()
            is LoginScreenAction.OnBottomSheetButtonClick -> sendEvent(LoginScreenEvents.DismissBottomSheet)
        }
    }

    private fun handleEmailChange(email: String){
        _state.update { state -> state.copy(formState = state.formState.copy(email = email.trim())) }
    }

    private fun handlePasswordChange(password: String){
        _state.update { state -> state.copy(formState = state.formState.copy(password = password.trim())) }
    }

    private fun validateAndLogin(){
        if (!shouldBeingLogin()){
            return
        }
        viewModelScope.withDispatcher {
            val useCaseResult = loginUseCase(
                email = _state.value.formState.email,
                password = _state.value.formState.password
            )
            when(useCaseResult){
                is Result.Error -> handleUseCaseError(useCaseResult.error)
                is Result.Success -> {
                    _state.update { state -> state.copy(displayState = DisplayState.Success) }
                }
            }
        }
    }

    private fun shouldBeingLogin(): Boolean {
        return isValidEmail(_state.value.formState.email) && isValidPassword(_state.value.formState.password)
    }

    private fun isValidEmail(email: String): Boolean {
        return when(val result = emailValidator(email)){
            is Result.Error -> {
                when(result.error){
                    EmailValidator.EmailError.EMPTY -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Email cannot be empty")) }
                        false
                    }
                    EmailValidator.EmailError.INVALID -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Invalid email")) }
                        false
                    }
                    EmailValidator.EmailError.BLANK -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Email cannot be blank")) }
                        false
                    }
                }
            }
            is Result.Success -> return true
        }
    }

    private fun isValidPassword(password: String): Boolean {
        return when(val result = passwordValidator(password)){
            is Result.Error -> {
                when(result.error){
                    PasswordValidator.PasswordError.EMPTY -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Password cannot be empty")) }
                    }
                    PasswordValidator.PasswordError.INVALID -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(passwordError = "Invalid password")) }
                    }
                }
                false
            }
            is Result.Success -> true
        }
    }

    private fun handleUseCaseError(error: AuthError){
        if (error == AuthError.Remote.EMAIL_NOT_VERIFIED){
            sendEvent(
                LoginScreenEvents.ShowBottomSheet(
                    content = SheetContent(
                        title = "Email not verified",
                        body = "Looks like you didn't verify your email yet. Please check your inbox and verify your email",
                        button = "Dismiss"
                    )
                )
            )
            return
        }
        sendEvent(LoginScreenEvents.Error(
            error = error.asUiText()
        ))
    }
}