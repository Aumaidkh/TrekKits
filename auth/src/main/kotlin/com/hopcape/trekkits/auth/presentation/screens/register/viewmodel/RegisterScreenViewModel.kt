
package com.hopcape.trekkits.auth.presentation.screens.register.viewmodel

import androidx.lifecycle.viewModelScope
import com.hopcape.common.domain.base.presentation.BaseViewModel
import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError
import com.hopcape.trekkits.auth.asUiText
import com.hopcape.trekkits.auth.domain.models.User
import com.hopcape.trekkits.auth.domain.usecase.RegisterUseCase
import com.hopcape.trekkits.auth.domain.validation.EmailValidator
import com.hopcape.trekkits.auth.domain.validation.FullNameValidator
import com.hopcape.trekkits.auth.domain.validation.PasswordValidator
import com.hopcape.trekkits.auth.presentation.SheetContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class RegisterScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val nameValidator: FullNameValidator,
) : BaseViewModel<RegisterScreenState, RegisterScreenAction, RegisterScreenEvents>(
    initialState = RegisterScreenState()
) {
    override fun onAction(action: RegisterScreenAction) {
        when (action) {
            is RegisterScreenAction.FirstNameChanged -> handleNameChange(
                name = action.value
            )

            is RegisterScreenAction.EmailChanged -> handleEmailChange(
                email = action.value
            )

            is RegisterScreenAction.PasswordChanged -> handlePasswordChange(
                password = action.value
            )

            is RegisterScreenAction.ConfirmPasswordChanged -> handleConfirmPasswordChange(
                password = action.value
            )

            is RegisterScreenAction.Register -> beginRegistration()

            is RegisterScreenAction.OnBottomSheetButtonClick -> sendEvent(RegisterScreenEvents.DismissBottomSheet)

            is RegisterScreenAction.Login -> sendEvent(RegisterScreenEvents.NavigateBack)

            is RegisterScreenAction.SignInWithFacebook -> TODO()

            is RegisterScreenAction.SignInWithGoogle -> TODO()
        }
    }

    private fun handleNameChange(name: String) {
        _state.update {
            it.copy(
                formState = it.formState.copy(
                    firstName = name.trim(),
                    firstNameError = null
                )
            )
        }
    }

    private fun handleEmailChange(email: String) {
        _state.update {
            it.copy(
                formState = it.formState.copy(
                    email = email.trim(),
                    emailError = null
                )
            )
        }
    }

    private fun handlePasswordChange(password: String) {
        _state.update {
            it.copy(
                formState = it.formState.copy(
                    password = password.trim(),
                    passwordError = null
                )
            )
        }
    }

    private fun handleConfirmPasswordChange(password: String) {
        _state.update {
            it.copy(
                formState = it.formState.copy(
                    confirmPassword = password.trim(),
                    confirmPasswordError = null
                )
            )
        }
    }

    private fun isNameValid(name: String): Boolean {
        return when (val result = nameValidator(name)) {
            is Result.Error -> {
                when (result.error) {
                    FullNameValidator.NameError.EMPTY_NAME -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(firstNameError = "Name cannot be empty")) }
                    }

                    FullNameValidator.NameError.BLANK_NAME -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(firstNameError = "Name cannot be blank")) }
                    }

                    FullNameValidator.NameError.NAME_CONTAINS_DIGIT -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(firstNameError = "Name can't contain a digit")) }
                    }

                    FullNameValidator.NameError.INVALID_NAME -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(firstNameError = "Invalid name")) }
                    }
                }
                false
            }

            is Result.Success -> true
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return when (val result = emailValidator(email)) {
            is Result.Error -> {
                when (result.error) {
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

    private fun isPasswordValid(password: String): Boolean {
        return when (val result = passwordValidator(password)) {
            is Result.Error -> {
                when (result.error) {
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

    private fun isConfirmPasswordValid(password: String): Boolean {
        return when (val result = passwordValidator(password)) {
            is Result.Error -> {
                when (result.error) {
                    PasswordValidator.PasswordError.EMPTY -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(confirmPasswordError = "Password cannot be empty")) }
                    }

                    PasswordValidator.PasswordError.INVALID -> {
                        _state.update { state -> state.copy(formState = state.formState.copy(confirmPasswordError = "Invalid password")) }
                    }
                }
                false
            }

            is Result.Success -> true
        }
    }

    private fun passwordsMatch(
        password: String,
        confirmPassword: String
    ): Boolean {
        return (password == confirmPassword).also {
            if (!it) {
                _state.update { state -> state.copy(formState = state.formState.copy(confirmPasswordError = "Passwords don't match")) }
            }
        }
    }

    private fun shouldBeginRegistration(): Boolean {
        return isNameValid(
            name = _state.value.formState.firstName
        ) && isEmailValid(
            email = _state.value.formState.email
        ) && isPasswordValid(
            password = _state.value.formState.password
        ) && isConfirmPasswordValid(
            password = _state.value.formState.confirmPassword
        ) && passwordsMatch(
            password = _state.value.formState.password,
            confirmPassword = _state.value.formState.confirmPassword
        )
    }

    private fun beginRegistration() {
        if (!shouldBeginRegistration()) {
            return
        }
        register(
            user = User(
                name = _state.value.formState.firstName,
                email = _state.value.formState.email
            ),
            password = _state.value.formState.password
        )
    }

    private fun register(
        user: User,
        password: String
    ) {
        viewModelScope.withDispatcher {
            registerUseCase(
                user = user,
                password = password
            ).also { result ->
                when (result) {
                    is Result.Error -> handleError(result.error)
                    is Result.Success -> _state.update {
                        it.copy(
                            displayState = RegisterScreenState.DisplayState.Success(
                                sheetContent = SheetContent(
                                    title = "Success",
                                    body = "A confirmation email has been sent to ${user.email}. Please click the link in the email to confirm your account",
                                    button = "Login"
                                )
                            )
                        )
                    }
                }
            }
        }
    }

    private fun handleError(error: AuthError) {
        sendEvent(RegisterScreenEvents.Error(error.asUiText()))
    }

}