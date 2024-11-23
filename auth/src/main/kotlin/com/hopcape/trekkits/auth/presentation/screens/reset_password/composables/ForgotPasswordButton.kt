package com.hopcape.trekkits.auth.presentation.screens.reset_password.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hopcape.designsystem.components.buttons.PrimaryButton
import com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel.DisplayState
import com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel.ForgotPasswordScreenAction

@Composable
fun ForgotPasswordButton(
    modifier: Modifier = Modifier,
    state: DisplayState = DisplayState.Initial,
    onAction: (ForgotPasswordScreenAction) -> Unit = {}
) {
    PrimaryButton(
        modifier = modifier,
        text = if(state.isLoading()) "Requesting Verification.." else "Forgot Password",
        onClick = { onAction(ForgotPasswordScreenAction.Submit) },
        enabled = !state.isLoading()
    )
}