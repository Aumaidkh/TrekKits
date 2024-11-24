package com.hopcape.trekkits.auth.domain.usecase

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import com.hopcape.trekkits.auth.domain.errors.AuthDomainError
import com.hopcape.trekkits.auth.domain.models.User
import com.hopcape.trekkits.auth.domain.validation.EmailValidator
import com.hopcape.trekkits.auth.domain.validation.FullNameValidator
import com.hopcape.trekkits.auth.domain.validation.PasswordValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val fullNameValidator: FullNameValidator,
    private val authRepository: AuthRepository
) {
    operator fun invoke(user: User,password: String,confirmPassword: String): Flow<UseCaseResult<User, AuthDomainError>> {
        return flow<UseCaseResult<User,AuthDomainError>> {
            emit(UseCaseResult.Loading())
            val fullNameValidationResult = fullNameValidator(user.name)
            if (!fullNameValidationResult.isValid && fullNameValidationResult.error != null) {
                emit(UseCaseResult.Error(fullNameValidationResult.error))
                return@flow
            }
            val emailValidationResult = emailValidator(user.email)
            if (!emailValidationResult.isValid && emailValidationResult.error != null) {
                emit(UseCaseResult.Error(emailValidationResult.error))
                return@flow
            }
            val passwordValidationResult = passwordValidator(password)
            if (!passwordValidationResult.isValid && passwordValidationResult.error != null) {
                emit(UseCaseResult.Error(passwordValidationResult.error))
                return@flow
            }
            val confirmPasswordValidationResult = passwordValidator(confirmPassword)
            if (!confirmPasswordValidationResult.isValid && confirmPasswordValidationResult.error != null){
                emit(UseCaseResult.Error(confirmPasswordValidationResult.error))
                return@flow
            }
            if (password != confirmPassword) {
                emit(UseCaseResult.Error(AuthDomainError.PASSWORDS_DONT_MATCH))
                return@flow
            }
            when(val result = authRepository.register(user,password)){
                is Result.Error -> emit(UseCaseResult.Error(result.error))
                is Result.Success -> emit(UseCaseResult.Success(result.data))
            }
            emit(UseCaseResult.Success(User()))
        }.catch {
            emit(UseCaseResult.Error(AuthDomainError.SOMETHING_WENT_WRONG))
        }
    }
}