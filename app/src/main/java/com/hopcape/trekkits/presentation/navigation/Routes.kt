package com.hopcape.trekkits.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object HomeScreen: Routes()
}