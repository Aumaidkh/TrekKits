package com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel

import androidx.lifecycle.viewModelScope
import com.hopcape.common.domain.base.presentation.BaseViewModel
import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError
import com.hopcape.trekkits.auth.asUiText
import com.hopcape.trekkits.auth.domain.usecase.ForgotPasswordUseCase
import com.hopcape.trekkits.auth.domain.validation.EmailValidator
import com.hopcape.trekkits.auth.presentation.SheetContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ForgotPasswordScreenViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val emailValidator: EmailValidator
) : BaseViewModel<ForgotPasswordScreenState, ForgotPasswordScreenAction, ForgotPasswordScreenEvent>(
    initialState = ForgotPasswordScreenState()
) {

    override fun onAction(action: ForgotPasswordScreenAction) {
        when (action) {
            is ForgotPasswordScreenAction.EmailChanged -> handleEmailChange(action.email.trim())

            is ForgotPasswordScreenAction.OnBottomSheetButtonClick -> handleBottomSheetButtonClick()

            is ForgotPasswordScreenAction.OnBackClick -> handleBackClick()

            is ForgotPasswordScreenAction.Submit -> validateAndForgotPassword()
        }

    }

    private fun handleEmailChange(email: String) {
        _state.update {
            it.copy(
                formState = it.formState.copy(
                    email = email,
                    emailError = null
                )
            )
        }
    }

    private fun handleBackClick() {
        sendEvent(ForgotPasswordScreenEvent.NavigateToLogin)
    }

    private fun handleBottomSheetButtonClick() {
        sendEvent(ForgotPasswordScreenEvent.NavigateToLogin)
    }

    private fun validateAndForgotPassword(){
        if (!isEmailValid()){
            return
        }
        forgotPassword(_state.value.formState.email)
    }

    private fun isEmailValid(email: String = _state.value.formState.email): Boolean{
        return when(val result = emailValidator(email)){
            is Result.Error -> {
                when(result.error){
                    EmailValidator.EmailError.EMPTY -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Email cannot be empty")) }
                    }
                    EmailValidator.EmailError.INVALID -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Invalid email")) }
                    }
                    EmailValidator.EmailError.BLANK -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Email cannot be blank")) }
                    }
                }
                false
            }
            is Result.Success -> true
        }
    }

    private fun forgotPassword(email: String){
        viewModelScope.withDispatcher {
            forgotPasswordUseCase(email).also {
                handleForgotPasswordResult(it)
            }
        }
    }

    private fun handleForgotPasswordResult(result: Result<Unit, AuthError>){
        when(result){
            is Result.Error -> handleError(result.error)
            is Result.Success -> _state.update {
                it.copy(
                    displayState = DisplayState.Success(
                        sheetContent = SheetContent(
                            title = "Verification Link Sent",
                            body = "Password reset link has been sent to ${_state.value.formState.email}. Please follow the link to update password.",
                            button = "Dismiss",
                        )
                    )
                )
            }
        }
    }

    private fun handleError(error: AuthError){
         sendEvent(ForgotPasswordScreenEvent.Error(error.asUiText()))
        _state.update { it.copy(displayState = DisplayState.Initial) }
    }

}