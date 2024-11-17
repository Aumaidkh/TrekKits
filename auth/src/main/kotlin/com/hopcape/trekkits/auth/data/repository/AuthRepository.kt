package com.hopcape.trekkits.auth.data.repository

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.domain.errors.AuthError
import com.hopcape.trekkits.auth.domain.models.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User,AuthError>
    suspend fun register(user: User,password: String): Result<User,AuthError>
}