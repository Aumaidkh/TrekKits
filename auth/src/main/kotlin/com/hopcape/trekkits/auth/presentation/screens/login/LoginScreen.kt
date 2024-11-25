package com.hopcape.trekkits.auth.presentation.screens.login

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hopcape.designsystem.components.buttons.TextWithClickableText
import com.hopcape.designsystem.styles.bodyLowEmphasis
import com.hopcape.trekkits.auth.presentation.SheetContent
import com.hopcape.trekkits.auth.presentation.composables.AuthButton
import com.hopcape.trekkits.auth.presentation.composables.AuthInputField
import com.hopcape.trekkits.auth.presentation.composables.AuthScreen
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.LoginScreenAction
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.LoginScreenState
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.toPasswordIconResId
import com.hopcape.trekkits.auth.presentation.screens.login.viewmodel.toVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    sheetContent: SheetContent = SheetContent(),
    screenState: LoginScreenState = LoginScreenState(),
    onAction: (LoginScreenAction) -> Unit
) {
    AuthScreen(
        modifier = modifier,
        scrollState = scrollState,
        bottomSheetScaffoldState = bottomSheetScaffoldState,
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
        },
        onBottomSheetButtonClick = { onAction(LoginScreenAction.OnBottomSheetButtonClick) },
        sheetContent = sheetContent
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
                    bottom = 0.dp
                )
                .fillMaxWidth(),
            text = "Password",
            value = screenState.formState.password,
            onValueChange = { onAction(LoginScreenAction.PasswordChanged(it)) },
            startIconResId = com.hopcape.designsystem.R.drawable.key,
            error = screenState.formState.passwordError,
            trailingContent = {
                IconButton(
                    onClick = {
                        onAction(LoginScreenAction.ShowHidePassword)
                    }
                ){
                    Icon(
                        painter = painterResource(screenState.toPasswordIconResId()),
                        contentDescription = ""
                    )
                }
            },
            visualTransformation = screenState.toVisualTransformation(),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        )

        Row(
            modifier = Modifier
                .padding(bottom = 36.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                modifier = Modifier,
                onClick = { onAction(LoginScreenAction.ForgotPassword) }
            ) {
                Text("Forgot Password?")
            }
        }

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