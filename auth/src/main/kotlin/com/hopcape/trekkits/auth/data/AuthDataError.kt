package com.hopcape.trekkits.auth.data

import com.hopcape.common.domain.error.DataError

enum class AuthDataError: DataError {
    SOMETHING_WENT_WRONG,
    USER_NOT_FOUND,
    EMAIL_NOT_VERIFIED
}