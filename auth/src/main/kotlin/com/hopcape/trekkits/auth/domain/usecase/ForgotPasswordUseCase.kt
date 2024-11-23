package com.hopcape.trekkits.auth.domain.usecase

import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import com.hopcape.trekkits.auth.domain.errors.AuthError
import com.hopcape.trekkits.auth.domain.validation.EmailValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val emailValidator: EmailValidator
){

    operator fun invoke(email: String): Flow<UseCaseResult<Unit,AuthError>>{
        return flow {
            emit(UseCaseResult.Loading())
            val emailValidationResult = emailValidator(email)
            if (!emailValidationResult.isValid && emailValidationResult.error != null){
                return@flow emit(UseCaseResult.Error(emailValidationResult.error))
            }
            val result = repository.forgotPassword(email)
            emit(UseCaseResult.Success<Unit,AuthError>(Unit))
        }.catch { emit(UseCaseResult.Error(AuthError.SOMETHING_WENT_WRONG)) }
    }

}