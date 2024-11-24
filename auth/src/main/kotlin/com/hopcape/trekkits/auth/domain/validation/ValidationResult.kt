package com.hopcape.trekkits.auth.domain.validation

import com.hopcape.common.domain.error.Error

data class ValidationResult(
    val isValid: Boolean,
    val error: Error? = null
){
    val hasError get() =
        error != null
}