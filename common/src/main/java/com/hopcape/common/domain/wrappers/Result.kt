package com.hopcape.common.domain.wrappers

sealed class Result<D,out E: RootError> {
    class Success<D,out E: RootError>(val data: D) : Result<D,E>()
    class Error<D,out E: RootError>(val error: E) : Result<D,E>()
}