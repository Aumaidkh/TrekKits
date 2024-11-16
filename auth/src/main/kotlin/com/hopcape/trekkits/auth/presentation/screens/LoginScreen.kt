package com.hopcape.trekkits.auth.presentation.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.hopcape.designsystem.components.buttons.TextWithClickableText
import com.hopcape.designsystem.styles.bodyLowEmphasis
import com.hopcape.trekkits.auth.presentation.composables.AuthButton
import com.hopcape.trekkits.auth.presentation.composables.AuthInputField
import com.hopcape.trekkits.auth.presentation.composables.AuthScreen
import com.hopcape.trekkits.auth.presentation.viewmodel.LoginScreenAction
import com.hopcape.trekkits.auth.presentation.viewmodel.LoginScreenState

@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    screenState: LoginScreenState = LoginScreenState(),
    onAction: (LoginScreenAction) -> Unit
) {
    AuthScreen(
        modifier = modifier,
        scrollState = scrollState,
        onSignInWithGoogleClick = { onAction(LoginScreenAction.SignInWithGoogle) },
        onSignInWithFacebookClick = { onAction(LoginScreenAction.SignInWithFacebook) },
        textWithClickableText = {
            TextWithClickableText(
                modifier = Modifier
                    .padding(vertical = 32.dp),
                text = "Don't have an account?",
                clickableText = "Register",
                onClick = { onAction(LoginScreenAction.Register) }
            )
        }
    ){
        AuthInputField(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Email",
            value = screenState.formState.email,
            onValueChange = { onAction(LoginScreenAction.EmailChanged(it)) },
            startIconResId = com.hopcape.designsystem.R.drawable.envelope,
            error = screenState.formState.emailError,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        )

        AuthInputField(
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    bottom = 24.dp
                )
                .fillMaxWidth(),
            text = "Password",
            value = screenState.formState.password,
            onValueChange = { onAction(LoginScreenAction.PasswordChanged(it)) },
            startIconResId = com.hopcape.designsystem.R.drawable.key,
            error = screenState.formState.passwordError,
            trailingContent = {
                TextButton(
                    modifier = Modifier
                        .size(24.dp),
                    onClick = { onAction(LoginScreenAction.ForgotPassword) }
                ) {
                    Text("Forgot Password?")
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        )

        AuthButton(
            modifier = Modifier
                .fillMaxWidth(),
            state = screenState.displayState,
            onAction = onAction
        )

        Text(
            modifier = Modifier
                .padding(vertical = 40.dp),
            text = "Or, login with...",
            style = bodyLowEmphasis
        )
    }
}