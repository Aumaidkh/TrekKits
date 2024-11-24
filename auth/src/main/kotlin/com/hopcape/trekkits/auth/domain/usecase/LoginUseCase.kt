package com.hopcape.trekkits.auth.domain.usecase

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import com.hopcape.trekkits.auth.domain.errors.AuthDomainError
import com.hopcape.trekkits.auth.domain.validation.EmailValidator
import com.hopcape.trekkits.auth.domain.validation.PasswordValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val repository: AuthRepository
) {

    operator fun invoke(email: String, password: String): Flow<UseCaseResult<Unit, AuthDomainError>> {
        return flow {
            emit(UseCaseResult.Loading())
            val emailResult = emailValidator(email)
            val passwordResult = passwordValidator(password)
            if (!emailResult.isValid && emailResult.error != null) {
                emit(UseCaseResult.Error(emailResult.error))
            }
            if (!passwordResult.isValid && passwordResult.error != null) {
                emit(UseCaseResult.Error(passwordResult.error))
            }
            when(val result = repository.login(email, password)){
                is Result.Error -> emit(UseCaseResult.Error<Unit, AuthDomainError>(result.error))
                is Result.Success -> emit(UseCaseResult.Success(Unit))
            }
        }.catch { emit(UseCaseResult.Error(AuthDomainError.SOMETHING_WENT_WRONG)) }
    }

}
