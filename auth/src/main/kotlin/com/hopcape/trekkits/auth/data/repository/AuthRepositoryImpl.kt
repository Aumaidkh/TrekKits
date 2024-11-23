package com.hopcape.trekkits.auth.data.repository

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.domain.errors.AuthError
import com.hopcape.trekkits.auth.domain.models.User
import kotlinx.coroutines.delay
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(): AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<User, AuthError> {
        delay(4000)
        return Result.Success(User())
    }

    override suspend fun register(user: User,password: String): Result<User, AuthError> {
        delay(4000)
        return Result.Success(User())
    }

    override suspend fun forgotPassword(email: String): Result<Unit, AuthError> {
        delay(4000)
        return Result.Success(Unit)
    }
}