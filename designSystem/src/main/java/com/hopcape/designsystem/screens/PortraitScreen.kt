package com.hopcape.designsystem.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hopcape.designsystem.dimens.ScreenHorizontalPadding
import com.hopcape.designsystem.dimens.ScreenVerticalPadding

@Composable
fun PortraitScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .padding(
                horizontal = ScreenHorizontalPadding
            )
            .padding(
                top = ScreenVerticalPadding,
            ),
        contentAlignment = Alignment.TopStart
    ){
        content()
    }
}