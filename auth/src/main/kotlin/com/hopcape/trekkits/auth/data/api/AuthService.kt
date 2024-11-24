package com.hopcape.trekkits.auth.data.api

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError

interface AuthService {

    suspend fun login(email: String, password: String): Result<Unit,AuthError>

    suspend fun register(email: String,password: String,name: String): Result<Unit,AuthError>

    suspend fun forgotPassword(email: String): Result<Unit,AuthError>

}