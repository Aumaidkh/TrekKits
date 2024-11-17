package com.hopcape.trekkits.auth.domain.errors

import com.hopcape.common.domain.error.DomainError

enum class AuthError: DomainError {
    SOMETHING_WENT_WRONG,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    INVALID_CREDENTIALS,
    EMPTY_NAME,
    NAME_CONTAINS_DIGIT,
    INVALID_NAME,
    PASSWORDS_DONT_MATCH
}

