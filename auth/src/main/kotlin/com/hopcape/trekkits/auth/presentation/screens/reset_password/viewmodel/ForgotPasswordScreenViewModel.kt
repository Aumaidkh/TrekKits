package com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.domain.errors.AuthDomainError
import com.hopcape.trekkits.auth.domain.usecase.ForgotPasswordUseCase
import com.hopcape.trekkits.auth.presentation.SheetContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ForgotPasswordScreenViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordScreenState())
    val state: StateFlow<ForgotPasswordScreenState> = _state.asStateFlow()

    private val _event = Channel<ForgotPasswordScreenEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: ForgotPasswordScreenAction) {
        when (action) {
            is ForgotPasswordScreenAction.EmailChanged -> handleEmailChange(action.email)

            is ForgotPasswordScreenAction.OnBottomSheetButtonClick -> handleBottomSheetButtonClick()

            is ForgotPasswordScreenAction.OnBackClick -> handleBackClick()

            is ForgotPasswordScreenAction.Submit -> forgotPassword(_state.value.formState.email)
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
        pushEvent(ForgotPasswordScreenEvent.NavigateToLogin)
    }

    private fun handleBottomSheetButtonClick() {
        pushEvent(ForgotPasswordScreenEvent.DismissBottomSheet)
    }

    private fun forgotPassword(email: String) {
        forgotPasswordUseCase(email)
            .onEach { result ->
                when (result) {
                    is UseCaseResult.Error -> handleError(result.error)

                    is UseCaseResult.Loading -> _state.update { it.copy(displayState = DisplayState.Loading) }

                    is UseCaseResult.Success -> {
                        _state.update {
                            it.copy(
                                displayState = DisplayState.Success(
                                    sheetContent = SheetContent(
                                        title = "Verification Link Sent",
                                        body = "Password reset link has been sent to $email. Please follow the link to update password.",
                                        button = "Dismiss",
                                    )
                                )
                            )
                        }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun handleError(error: AuthDomainError){
        when(error){
            AuthDomainError.SOMETHING_WENT_WRONG -> { _state.update { state -> state.copy(displayState = DisplayState.Error(message = "Something went wrong")) } }
            AuthDomainError.INVALID_EMAIL -> { _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Invalid email")) } }
            AuthDomainError.EMPTY_EMAIL -> { _state.update { state -> state.copy(formState = state.formState.copy(emailError = "Email cannot be empty")) } }
            else -> Unit
        }
        _state.update { it.copy(displayState = DisplayState.Initial) }
    }

    private fun pushEvent(event: ForgotPasswordScreenEvent) {
        viewModelScope.launch {
            _event.send(event)
        }
    }

}