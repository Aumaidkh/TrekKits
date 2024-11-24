package com.hopcape.trekkits.auth.domain.usecase

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import com.hopcape.trekkits.auth.domain.errors.AuthDomainError
import com.hopcape.trekkits.auth.domain.validation.EmailValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val emailValidator: EmailValidator
){

    operator fun invoke(email: String): Flow<UseCaseResult<Unit,AuthDomainError>>{
        return flow {
            emit(UseCaseResult.Loading())
            val emailValidationResult = emailValidator(email)
            if (!emailValidationResult.isValid && emailValidationResult.error != null){
                return@flow emit(UseCaseResult.Error(emailValidationResult.error))
            }
            when(val result = repository.forgotPassword(email)){
                is Result.Error -> emit(UseCaseResult.Error(result.error))
                is Result.Success -> emit(UseCaseResult.Success<Unit,AuthDomainError>(Unit))
            }
        }.catch { emit(UseCaseResult.Error(AuthDomainError.SOMETHING_WENT_WRONG)) }
    }

}