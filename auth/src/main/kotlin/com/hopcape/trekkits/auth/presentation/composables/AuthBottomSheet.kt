package com.hopcape.trekkits.auth.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hopcape.designsystem.components.buttons.SecondaryButton
import com.hopcape.designsystem.dimens.ContentInnerPadding
import com.hopcape.designsystem.styles.bodyMediumEmphasis
import com.hopcape.designsystem.styles.headingMedium
import com.hopcape.trekkits.auth.presentation.SheetContent

@Composable
fun AuthBottomSheet(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
    sheetContent: SheetContent = SheetContent()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding( horizontal =  ContentInnerPadding)
            .padding(bottom = ContentInnerPadding + 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = sheetContent.title,
            style = headingMedium
        )
        Text(
            text = sheetContent.body,
            style = bodyMediumEmphasis
        )
        SecondaryButton(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            text = sheetContent.button,
            onClick = onButtonClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthBottomSheetPreview() {
    AuthBottomSheet()
}