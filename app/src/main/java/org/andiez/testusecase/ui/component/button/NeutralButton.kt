package org.andiez.testusecase.ui.component.button

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.andiez.testusecase.ui.theme.ActivePrimary
import org.andiez.testusecase.ui.theme.BackgroundPrimary
import org.andiez.testusecase.ui.theme.ContentPrimary
import org.andiez.testusecase.ui.theme.SubmissionComposeTheme

@Composable
fun PrimaryNeutralButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    BaseFilledButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BackgroundPrimary,
            contentColor = ContentPrimary,
        ),
        text = text,
        modifier = modifier,
    )
}

@Composable
fun SecondaryNeutralButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
) {
    BaseOutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = BackgroundPrimary,
        ),
        text = text,
        borderColor = BackgroundPrimary,
        modifier = modifier,
    )
}

@Composable
fun TertiaryNeutralButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    textColor: Color = ActivePrimary,
    text: String,
) {
    BaseFilledButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BackgroundPrimary,
            contentColor = ActivePrimary,
        ),
        text = text,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun NeutralButtonPreview() {
    SubmissionComposeTheme {
        Column {
            PrimaryNeutralButton(onClick = { }, text = "Button")
            Spacer(modifier = Modifier.height(8.dp))
            SecondaryNeutralButton(onClick = { }, text = "Button")
        }
    }
}