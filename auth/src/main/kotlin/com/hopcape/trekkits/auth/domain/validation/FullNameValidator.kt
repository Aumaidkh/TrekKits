package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.common.domain.error.Error
import com.hopcape.common.domain.wrappers.Result

class FullNameValidator {
    operator fun invoke(fullName: String): Result<Unit,Error> {
        if (fullName.isBlank()) {
            return Result.Error(NameError.BLANK_NAME)
        }
        if (fullName.isEmpty()) {
            return Result.Error(NameError.EMPTY_NAME)
        }
        if (fullName.any { it.isDigit() }) {
            return Result.Error(NameError.NAME_CONTAINS_DIGIT)
        }
        return Result.Success(Unit)
    }

    enum class NameError: Error {
        EMPTY_NAME,
        BLANK_NAME,
        NAME_CONTAINS_DIGIT,
        INVALID_NAME
    }
}