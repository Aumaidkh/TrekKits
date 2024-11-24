package com.hopcape.trekkits.auth.data.repository

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError
import com.hopcape.trekkits.auth.data.api.AuthService
import com.hopcape.trekkits.auth.domain.models.User
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<User, AuthError> {
        val result = authService.login(
            email = email,
            password = password
        )
        return when(result){
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(User())
        }
    }

    override suspend fun register(user: User,password: String): Result<User, AuthError> {
        val result = authService.register(
            email = user.email,
            password = password,
            name = user.name
        )
        return when(result){
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(User())
        }
    }

    override suspend fun forgotPassword(email: String): Result<Unit, AuthError> {
        val result = authService.forgotPassword(
            email = email
        )
        return when(result){
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(Unit)
        }
    }
}