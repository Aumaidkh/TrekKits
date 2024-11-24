package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.trekkits.auth.domain.errors.AuthDomainError

class PasswordValidator {
    operator fun invoke(password: String): ValidationResult {
        if (password.isEmpty()){
            return ValidationResult(
                isValid = false,
                error = AuthDomainError.EMPTY_PASSWORD
            )
        }
        if (password.isBlank()){
            return ValidationResult(
                isValid = false,
                error = AuthDomainError.EMPTY_PASSWORD
            )
        }
        return ValidationResult(
            isValid = true
        )
    }
}