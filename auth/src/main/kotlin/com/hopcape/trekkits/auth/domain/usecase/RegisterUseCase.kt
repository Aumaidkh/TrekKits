package com.hopcape.trekkits.auth.domain.usecase

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import com.hopcape.trekkits.auth.domain.models.User
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(user: User,password: String): Result<User, AuthError> {
        return authRepository.register(user,password)
    }
}