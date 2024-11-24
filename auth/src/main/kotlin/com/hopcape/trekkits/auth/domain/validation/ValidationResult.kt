package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.trekkits.auth.domain.errors.AuthDomainError

data class ValidationResult(
    val isValid: Boolean,
    val error: AuthDomainError? = null
){
    val hasError get() =
        error != null
}