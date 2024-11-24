package com.hopcape.trekkits.auth.domain.usecase

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import com.hopcape.trekkits.auth.domain.models.User
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<User, AuthError> {
        return repository.login(email,password)
    }

}
