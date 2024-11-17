package com.hopcape.trekkits.auth.presentation.screens.register

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.hopcape.auth.R
import com.hopcape.designsystem.components.buttons.PrimaryButton
import com.hopcape.designsystem.components.buttons.TextWithClickableText
import com.hopcape.designsystem.styles.bodyLowEmphasis
import com.hopcape.trekkits.auth.presentation.composables.AuthInputField
import com.hopcape.trekkits.auth.presentation.composables.AuthScreen
import com.hopcape.trekkits.auth.presentation.screens.register.viewmodel.RegisterScreenAction
import com.hopcape.trekkits.auth.presentation.screens.register.viewmodel.RegisterScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    screenState: RegisterScreenState = RegisterScreenState(),
    onAction: (RegisterScreenAction) -> Unit = {}
) {
    AuthScreen(
        modifier = modifier,
        scrollState = scrollState,
        screenTitle = "Register",
        onSignInWithGoogleClick = { onAction(RegisterScreenAction.SignInWithGoogle) },
        onSignInWithFacebookClick = { onAction(RegisterScreenAction.SignInWithFacebook) },
        textWithClickableText = {
            TextWithClickableText(
                modifier = Modifier
                    .padding(vertical = 32.dp),
                text = "Already have an account?",
                clickableText = "Login",
                onClick = { onAction(RegisterScreenAction.Login) }
            )
        },
        illustrationResId = R.drawable.welcoming,
        illustrationHeight = 200.dp,
        content = {
            AuthInputField(
                modifier = Modifier,
                text = "First Name",
                value = screenState.formState.firstName,
                onValueChange = { onAction(RegisterScreenAction.FirstNameChanged(it)) },
                startIconResId = com.hopcape.designsystem.R.drawable.ic_person,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                error = screenState.formState.firstNameError
            )
            AuthInputField(
                modifier = Modifier,
                text = "Email",
                value = screenState.formState.email,
                onValueChange = { onAction(RegisterScreenAction.EmailChanged(it)) },
                startIconResId = com.hopcape.designsystem.R.drawable.envelope,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                error = screenState.formState.emailError
            )
            AuthInputField(
                modifier = Modifier,
                text = "Password",
                value = screenState.formState.password,
                onValueChange = { onAction(RegisterScreenAction.PasswordChanged(it)) },
                startIconResId = com.hopcape.designsystem.R.drawable.key,
                error = screenState.formState.passwordError,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                visualTransformation = PasswordVisualTransformation()
            )

            AuthInputField(
                modifier = Modifier,
                text = "Confirm Password",
                value = screenState.formState.confirmPassword,
                onValueChange = { onAction(RegisterScreenAction.ConfirmPasswordChanged(it)) },
                startIconResId = com.hopcape.designsystem.R.drawable.key,
                error = screenState.formState.confirmPasswordError,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
            )

            PrimaryButton(
                modifier = modifier
                    .fillMaxWidth(),
                text = if (screenState.displayState == RegisterScreenState.DisplayState.Loading) "Registering..." else "Register",
                onClick = { onAction(RegisterScreenAction.Register)},
                enabled = screenState.displayState != RegisterScreenState.DisplayState.Loading
            )

            Text(
                modifier = Modifier
                    .padding(vertical = 40.dp),
                text = "Or, login with...",
                style = bodyLowEmphasis
            )
        }
    )
}