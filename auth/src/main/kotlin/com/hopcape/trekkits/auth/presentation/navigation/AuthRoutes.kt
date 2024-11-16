package com.hopcape.trekkits.auth.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
object Auth

@Serializable
internal data object Login
@Serializable
internal data object Register
@Serializable
internal data object ForgotPassword