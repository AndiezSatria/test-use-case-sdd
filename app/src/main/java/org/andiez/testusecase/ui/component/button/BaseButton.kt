package org.andiez.testusecase.ui.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import org.andiez.testusecase.R

enum class LoadingColor { PRIMARY, SECONDARY }

@Composable
fun BaseFilledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors,
    loadingColor: LoadingColor = LoadingColor.PRIMARY,
    text: String,
    buttonState: ButtonState = ButtonState.NORMAL,
) {
    Button(
        onClick = {
            if (buttonState == ButtonState.NORMAL)
                onClick()
        },
        colors = colors,
        modifier = modifier
            .clip(MaterialTheme.shapes.small),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
    ) {
        if (buttonState != ButtonState.LOADING)
            Text(
                text,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
            )
        else {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    when (loadingColor) {

                        LoadingColor.PRIMARY -> R.raw.loading_primary
                        LoadingColor.SECONDARY -> R.raw.loading_white
                    }
                )
            )
            LottieAnimation(
                modifier = Modifier
                    .size(24.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}

@Composable
fun BaseOutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors,
    loadingColor: LoadingColor = LoadingColor.PRIMARY,
    text: String,
    borderColor: Color,
    buttonState: ButtonState = ButtonState.NORMAL,
) {
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .border(BorderStroke(1.dp, borderColor), MaterialTheme.shapes.small),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
    ) {
        if (buttonState != ButtonState.LOADING) {
            Text(
                text,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
            )
        } else {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    when (loadingColor) {

                        LoadingColor.PRIMARY -> R.raw.loading_primary
                        LoadingColor.SECONDARY -> R.raw.loading_white
                    }
                )
            )
            LottieAnimation(
                modifier = Modifier
                    .size(24.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}

@Composable
fun BaseSideOutlinedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors,
    loadingColor: LoadingColor = LoadingColor.PRIMARY,
    text: String,
    borderColor: Color,
    @DrawableRes icon: Int,
    buttonState: ButtonState = ButtonState.NORMAL,
) {
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .border(BorderStroke(1.dp, borderColor), MaterialTheme.shapes.small),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
    ) {
        if (buttonState != ButtonState.LOADING) {
            Text(
                text,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
            )
        } else {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(
                    when (loadingColor) {

                        LoadingColor.PRIMARY -> R.raw.loading_primary
                        LoadingColor.SECONDARY -> R.raw.loading_white
                    }
                )
            )
            LottieAnimation(
                modifier = Modifier
                    .size(24.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )
        }
    }
}

enum class ButtonState {
    LOADING,
    DISABLED,
    NORMAL
}