package com.hopcape.designsystem.styles

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hopcape.designsystem.fonts.Poppins


val headingLarge: TextStyle @Composable
get() =
    MaterialTheme.typography.headlineLarge.copy(
        color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = 0.8f
        ),
        fontFamily = Poppins,
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold
    )


val hintSmall: TextStyle @Composable
get() =  MaterialTheme.typography.headlineLarge.copy(
        color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = 0.5f
        ),
        fontFamily = Poppins,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )

val bodyLowEmphasis: TextStyle @Composable
get() =  MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onBackground.copy(
            alpha = 0.5f
        ),
        fontFamily = Poppins,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

val buttonLarge: TextStyle
    @Composable
    get() =
        MaterialTheme.typography.labelMedium.copy(
            color = MaterialTheme.colorScheme.onPrimary.copy(
                alpha = 0.8f
            ),
            fontFamily = Poppins,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

val clickableTextStyle: TextStyle
    @Composable
    get() =
        MaterialTheme.typography.labelLarge.copy(
            color = MaterialTheme.colorScheme.primary,
            fontFamily = Poppins,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )