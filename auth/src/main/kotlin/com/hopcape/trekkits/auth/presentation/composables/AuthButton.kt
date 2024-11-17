package com.hopcape.trekkits.auth.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hopcape.designsystem.components.buttons.PrimaryButton
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.DisplayState
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.LoginScreenAction
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.LoginScreenState

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    state: DisplayState? = DisplayState.Loading,
    onAction: (LoginScreenAction) -> Unit
) {
    PrimaryButton(
        modifier = modifier
            .fillMaxWidth(),
        text = if (state == DisplayState.Loading) "Loading..." else "Login",
        onClick = { onAction(LoginScreenAction.Login)},
        enabled = state != DisplayState.Loading
    )
}