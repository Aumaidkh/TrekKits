package com.hopcape.trekkits.auth.data.repository

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.data.AuthDataError
import com.hopcape.trekkits.auth.data.api.AuthService
import com.hopcape.trekkits.auth.domain.errors.AuthDomainError
import com.hopcape.trekkits.auth.domain.models.User
import kotlinx.coroutines.delay
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<User, AuthDomainError> {
        val result = authService.login(
            email = email,
            password = password
        )
        return when(result){
            is Result.Error -> Result.Error(result.error.toAuthDomainError())
            is Result.Success -> Result.Success(User())
        }
    }

    override suspend fun register(user: User,password: String): Result<User, AuthDomainError> {
        val result = authService.register(
            email = user.email,
            password = password,
            name = user.name
        )
        return when(result){
            is Result.Error -> Result.Error(result.error.toAuthDomainError())
            is Result.Success -> Result.Success(User())
        }
    }

    override suspend fun forgotPassword(email: String): Result<Unit, AuthDomainError> {
        val result = authService.forgotPassword(
            email = email
        )
        return when(result){
            is Result.Error -> Result.Error(result.error.toAuthDomainError())
            is Result.Success -> Result.Success(Unit)
        }
    }

    private fun AuthDataError.toAuthDomainError(): AuthDomainError {
        return when(this){
            AuthDataError.SOMETHING_WENT_WRONG -> AuthDomainError.SOMETHING_WENT_WRONG
            AuthDataError.USER_NOT_FOUND -> AuthDomainError.INVALID_CREDENTIALS
            AuthDataError.EMAIL_NOT_VERIFIED -> AuthDomainError.EMAIL_NOT_VERIFIED
            else -> AuthDomainError.SOMETHING_WENT_WRONG
        }
    }
}