package com.hopcape.trekkits.auth.domain.usecase

import com.hopcape.common.domain.wrappers.UseCaseResult
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import com.hopcape.trekkits.auth.domain.errors.AuthError
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

    operator fun invoke(email: String, password: String): Flow<UseCaseResult<Unit, AuthError>> {
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
            val result = repository.login(email, password)
            emit(UseCaseResult.Success<Unit,AuthError>(Unit))
        }.catch { emit(UseCaseResult.Error(AuthError.SOMETHING_WENT_WRONG)) }
    }

}
