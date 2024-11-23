package com.hopcape.trekkits.auth.presentation.screens.reset_password

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hopcape.designsystem.styles.bodyLowEmphasis
import com.hopcape.trekkits.auth.presentation.composables.AuthInputField
import com.hopcape.trekkits.auth.presentation.composables.AuthScreen
import com.hopcape.trekkits.auth.presentation.screens.reset_password.composables.ForgotPasswordButton
import com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel.DisplayState
import com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel.ForgotPasswordScreenAction
import com.hopcape.trekkits.auth.presentation.screens.reset_password.viewmodel.ForgotPasswordScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    state: ForgotPasswordScreenState = ForgotPasswordScreenState(),
    onAction: (ForgotPasswordScreenAction) -> Unit = {}
) {
    AuthScreen(
        modifier = modifier,
        scrollState = scrollState,
        screenTitle = "Forgot Password",
        onSignInWithGoogleClick = {},
        onSignInWithFacebookClick = {},
        textWithClickableText = {},
        sheetContent = (state.displayState as? DisplayState.Success)?.sheetContent,
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        onBottomSheetButtonClick = { onAction(ForgotPasswordScreenAction.OnBottomSheetButtonClick) },
        content = {
            AuthInputField(
                text = "Email",
                onValueChange = { onAction(ForgotPasswordScreenAction.EmailChanged(it)) },
                value = state.formState.email,
                error = state.formState.emailError,
                startIconResId = com.hopcape.designsystem.R.drawable.envelope,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )

            ForgotPasswordButton(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                state = state.displayState,
                onAction = onAction
            )

            Text(
                modifier = Modifier
                    .padding(vertical = 50.dp),
                text = "Or, login with...",
                style = bodyLowEmphasis
            )
        }
    )
}