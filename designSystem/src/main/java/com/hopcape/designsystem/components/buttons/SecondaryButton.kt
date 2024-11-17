package com.hopcape.designsystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hopcape.designsystem.dimens.primaryButtonHeight
import com.hopcape.designsystem.styles.buttonLarge

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String = "Login",
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier
            .height(primaryButtonHeight),
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        enabled = enabled,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ){
        Text(
            text = text,
            style = buttonLarge.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Preview
@Composable
private fun SecondaryButtonPreview() {
    SecondaryButton()
}