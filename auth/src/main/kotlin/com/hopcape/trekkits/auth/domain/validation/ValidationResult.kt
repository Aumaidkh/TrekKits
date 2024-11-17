package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.trekkits.auth.domain.errors.AuthError

data class ValidationResult(
    val isValid: Boolean,
    val error: AuthError? = null
){
    val hasError get() =
        error != null
}