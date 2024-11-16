package com.hopcape.designsystem.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hopcape.designsystem.styles.headingLarge

@Composable
fun LargeHeading(modifier: Modifier = Modifier,text: String) {
    Text(
        modifier = modifier,
        text = text,
        style = headingLarge
    )
}