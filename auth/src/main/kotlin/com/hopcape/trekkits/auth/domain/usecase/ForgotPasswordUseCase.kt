package com.hopcape.trekkits.auth.domain.usecase

import com.hopcape.common.domain.wrappers.Result
import com.hopcape.trekkits.auth.AuthError
import com.hopcape.trekkits.auth.data.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
){

    suspend operator fun invoke(email: String): Result<Unit,AuthError>{
        return repository.forgotPassword(email)
    }

}