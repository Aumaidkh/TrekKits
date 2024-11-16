package com.hopcape.designsystem.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hopcape.designsystem.styles.bodyLowEmphasis
import com.hopcape.designsystem.styles.clickableTextStyle

@Composable
fun TextWithClickableText(
    modifier: Modifier = Modifier,
    text: String,
    clickableText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = text,
            style = bodyLowEmphasis
        )
        Text(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.small)
                .padding(horizontal = 2.dp, vertical = 2.dp)
                .clickable { onClick() },
            text = clickableText,
            color = MaterialTheme.colorScheme.primary,
            style = clickableTextStyle
        )
    }
}