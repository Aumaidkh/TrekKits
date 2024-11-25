package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.common.domain.error.Error
import com.hopcape.common.domain.wrappers.Result

class PasswordValidator {
    operator fun invoke(password: String): Result<Unit,PasswordError> {
        if (password.isEmpty()){
            return Result.Error(PasswordError.EMPTY)
        }
        if (password.isBlank()){
            return Result.Error(PasswordError.EMPTY)
        }
        return Result.Success(Unit)
    }

    enum class PasswordError: Error{
        EMPTY,
        INVALID
    }
}