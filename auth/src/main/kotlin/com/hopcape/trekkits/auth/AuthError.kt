package com.hopcape.trekkits.auth

import com.hopcape.common.domain.error.Error
import com.hopcape.common.domain.wrappers.UiText

interface AuthError: Error {

    enum class Local: AuthError {

    }

    enum class Remote: AuthError {
        SOMETHING_WENT_WRONG,
        USER_NOT_FOUND,
        INVALID_CREDENTIALS,
        EMAIL_NOT_VERIFIED,
        USER_ALREADY_EXISTS,
        NO_INTERNET_CONNECTION
    }

}

fun AuthError.Remote.asUiText(): UiText {
    return when(this){
        AuthError.Remote.EMAIL_NOT_VERIFIED -> UiText.DynamicString("Email not verified")
        AuthError.Remote.INVALID_CREDENTIALS -> UiText.DynamicString("Invalid credentials")
        AuthError.Remote.SOMETHING_WENT_WRONG -> UiText.DynamicString("Something went wrong")
        AuthError.Remote.USER_NOT_FOUND -> UiText.DynamicString("User not found")
        AuthError.Remote.USER_ALREADY_EXISTS -> UiText.DynamicString("User already exists")
        AuthError.Remote.NO_INTERNET_CONNECTION -> UiText.DynamicString("No internet connection")
    }
}

fun AuthError.asUiText(): UiText {
    return when(this){
        is AuthError.Local -> UiText.DynamicString("Local Error")
        is AuthError.Remote -> this.asUiText()
        else -> UiText.DynamicString("Something went wrong")
    }
}