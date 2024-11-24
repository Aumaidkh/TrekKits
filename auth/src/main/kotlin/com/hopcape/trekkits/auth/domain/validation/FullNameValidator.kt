package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.trekkits.auth.domain.errors.AuthDomainError

class FullNameValidator {
    operator fun invoke(fullName: String): ValidationResult {
        if (fullName.isBlank()) {
            return ValidationResult(
                isValid = false,
                error = AuthDomainError.EMPTY_NAME
            )
        }
        if (fullName.isEmpty()) {
            return ValidationResult(
                isValid = false,
                error = AuthDomainError.EMPTY_NAME
            )
        }
        if (fullName.any { it.isDigit() }) {
            return ValidationResult(
                isValid = false,
                error = AuthDomainError.NAME_CONTAINS_DIGIT
            )
        }
        return ValidationResult(
            isValid = true
        )
    }
}