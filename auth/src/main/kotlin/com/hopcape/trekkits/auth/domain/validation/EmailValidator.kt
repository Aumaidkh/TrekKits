package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.trekkits.auth.domain.errors.AuthError

class EmailValidator {
    operator fun invoke(email: String): ValidationResult {
        if (email.isBlank()){
            return ValidationResult(
                isValid = false,
                error = AuthError.EMPTY_EMAIL
            )
        }
        if (email.isEmpty()){
            return ValidationResult(
                isValid = false,
                error = AuthError.EMPTY_EMAIL
            )
        }
        if (!email.contains("@")){
            return ValidationResult(
                isValid = false,
                error = AuthError.INVALID_EMAIL
            )
        }
        return ValidationResult(
            isValid = true
        )
    }
}