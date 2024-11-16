package com.hopcape.common.domain.wrappers

import com.hopcape.common.domain.error.Error

typealias RootError = Error

sealed class UseCaseResult<D,out E: RootError>() {
    class Loading<D,out E: RootError> : UseCaseResult<D,E>()
    class Success<D,out E: RootError>(val data: D) : UseCaseResult<D,E>()
    class Error<D,out E: RootError>(val error: E) : UseCaseResult<D,E>()
}