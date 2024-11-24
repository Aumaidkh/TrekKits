package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.common.domain.error.Error
import com.hopcape.common.domain.wrappers.Result

class EmailValidator {
    operator fun invoke(email: String): Result<Unit,EmailError> {
        if (email.isBlank()){
            return Result.Error(EmailError.BLANK)
        }
        if (email.isEmpty()){
            return Result.Error(EmailError.EMPTY)
        }
        if (!email.contains("@")){
            return Result.Error(EmailError.INVALID)
        }
        return Result.Success(Unit)
    }

    enum class EmailError: Error {
        EMPTY,
        INVALID,
        BLANK
    }
}