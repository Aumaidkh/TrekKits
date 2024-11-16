package com.hopcape.designsystem.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hopcape.designsystem.dimens.primaryButtonHeight
import com.hopcape.designsystem.styles.buttonLarge

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String = "Login",
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier
            .height(primaryButtonHeight),
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ){
        Text(
            text = text,
            style = buttonLarge
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton()
}