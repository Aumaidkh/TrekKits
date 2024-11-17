package com.hopcape.trekkits.auth.domain.models

data class User(
    val id: String? = null,
    val email: String = "",
    val name: String = "",
)
