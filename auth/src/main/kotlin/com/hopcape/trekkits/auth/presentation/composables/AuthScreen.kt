package com.hopcape.trekkits.auth.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hopcape.auth.R
import com.hopcape.designsystem.components.text.LargeHeading
import com.hopcape.designsystem.screens.PortraitScreen

@Composable
internal fun AuthScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    illustrationResId: Int = R.drawable.off_road,
    screenTitle: String = "Login",
    onSignInWithGoogleClick: () -> Unit,
    onSignInWithFacebookClick: () -> Unit,
    textWithClickableText: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    PortraitScreen(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(400.dp),
                painter = painterResource(illustrationResId),
                contentDescription = null
            )
            LargeHeading(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(),
                text = screenTitle
            )

            content()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialSignInButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    iconResId = R.drawable.google,
                    onClick = onSignInWithGoogleClick
                )
                SocialSignInButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    iconResId = R.drawable.facebook_circle,
                    onClick = onSignInWithFacebookClick
                )
            }

            textWithClickableText()
        }
    }
}