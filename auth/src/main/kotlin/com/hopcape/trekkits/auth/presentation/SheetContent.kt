package com.hopcape.trekkits.auth.presentation

data class SheetContent(
    val title: String = "Verify Email",
    val body: String = "An email has been sent to the email you provided. Please check your inbox.",
    val button: String = "Dismiss"
)
