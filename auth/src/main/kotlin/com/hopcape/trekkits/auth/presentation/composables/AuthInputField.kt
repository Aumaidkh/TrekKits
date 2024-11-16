package com.hopcape.trekkits.auth.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hopcape.designsystem.components.fields.InputField
import com.hopcape.designsystem.dimens.inputFieldStartIconDrawable

@Composable
fun AuthInputField(
    modifier: Modifier = Modifier,
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    startIconResId: Int,
    trailingContent: @Composable (() -> Unit) = { },
) {
    InputField(
        modifier = modifier
            .fillMaxWidth(),
        label = text,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(inputFieldStartIconDrawable),
                painter = painterResource(startIconResId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.5f
                )
            )
        },
        trailingIcon = {
            trailingContent.invoke()
        }
    )
}