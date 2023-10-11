package org.andiez.testusecase.ui.component.textfield

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import org.andiez.testusecase.R
import androidx.compose.material.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import org.andiez.testusecase.ui.theme.*

/**
 * Created by AndiezSatria on 06/04/2023.
 */

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    isMustFilled: Boolean = false,
    hasError: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    onDone: () -> Unit = {},
    @DrawableRes icon: Int? = null,
    message: String = "",
    alwaysShownMessage: Boolean = false,
    semanticContentDescription: String = "",
) {
    Column(modifier = modifier) {
        val focusManager = LocalFocusManager.current
        val mustFilled = "mustFilled"
        val annotatedString = buildAnnotatedString {
            append(label)
            withStyle(
                style = SpanStyle(
                    color = ErrorPrimary,
                ),
            ) {
                pushStringAnnotation(
                    tag = mustFilled,
                    annotation = mustFilled,
                )
                append(if (isMustFilled) "*" else "")
            }
        }
        Text(
            annotatedString,
            style = MaterialTheme.typography.body2.copy(
                color = ContentPrimary
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = semanticContentDescription },
            value = value,
            onValueChange = onValueChange,
            isError = hasError,
            readOnly = readOnly,
            placeholder = {
                Text(
                    placeholder,
                    style = MaterialTheme.typography.body1.copy(
                        color = InactivePrimary,
                    )
                )
            },
            enabled = enabled,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                errorBorderColor = ErrorPrimary,
                cursorColor = ActivePrimary,
                disabledTextColor = InactiveSecondary,
                textColor = if (enabled) ContentPrimary else InactiveSecondary,
                focusedBorderColor = InactivePrimary,
                unfocusedBorderColor = InactivePrimary,
            ),
            shape = MaterialTheme.shapes.small,
            trailingIcon = {
                icon?.let{
                    Icon(
                        imageVector = ImageVector.vectorResource(id = icon),
                        contentDescription = null,
                        tint = if (enabled) ContentPrimary else InactiveSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onDone()
                }
            ),
        )
        if (alwaysShownMessage || hasError) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                message,
                style = if (hasError) MaterialTheme.typography.body2.copy(color = ErrorPrimary)
                else MaterialTheme.typography.body2.copy(color = ContentPrimary),
            )
        }
    }
}

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    hasError: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    semanticContentDescription: String = "",
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = semanticContentDescription },
        value = value,
        onValueChange = onValueChange,
        isError = hasError,
        placeholder = {
            Text(
                placeholder,
                style = MaterialTheme.typography.body1.copy(
                    color = InactivePrimary,
                )
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            errorBorderColor = ErrorPrimary,
            cursorColor = ActivePrimary,
            disabledTextColor = InactiveSecondary,
            textColor = ContentPrimary,
            focusedBorderColor = InactivePrimary,
            unfocusedBorderColor = InactivePrimary,
        ),
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = null,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
    )
}

@Composable
fun PasswordTextField(
    text: String,
    modifier: Modifier = Modifier,
    semanticContentDescription: String = "",
    label: String = "",
    placeholder: String = "",
    hasError: Boolean = false,
    onTextChanged: (text: String) -> Unit,
    isMustFilled: Boolean = false,
    alwaysShownMessage: Boolean = false,
    message: String = "",
) {
    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val mustFilled = "mustFilled"
        val annotatedString = buildAnnotatedString {
            append(label)
            withStyle(
                style = SpanStyle(
                    color = ErrorPrimary,
                ),
            ) {
                pushStringAnnotation(
                    tag = mustFilled,
                    annotation = mustFilled,
                )
                append(if (isMustFilled) "*" else "")
            }
        }
        Text(
            annotatedString,
            style = MaterialTheme.typography.body2.copy(color = ContentPrimary)
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = semanticContentDescription },
            value = text,
            onValueChange = onTextChanged,
            placeholder = {
                Text(
                    placeholder,
                    style = MaterialTheme.typography.body1.copy(
                        color = InactivePrimary,
                    )
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            isError = hasError,
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val (icon, iconColor) = if (showPassword.value) {
                    Pair(
                        R.drawable.ic_eye_off,
                        ContentTertiary,
                    )
                } else {
                    Pair(
                        R.drawable.ic_eye,
                        ContentTertiary,
                    )
                }

                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                errorBorderColor = ErrorPrimary,
                cursorColor = ActivePrimary,
                disabledTextColor = InactiveSecondary,
                textColor = ContentPrimary,
                focusedBorderColor = InactivePrimary,
                unfocusedBorderColor = InactivePrimary,
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
//        if (validateStrengthPassword && text.isNotEmpty()) {
//            val strengthPasswordType = strengthChecker(text)
//            if (strengthPasswordType == StrengthPasswordTypes.STRONG) {
//                onHasStrongPassword(true)
//            } else {
//                onHasStrongPassword(false)
//            }
//            Text(
//                modifier = Modifier.semantics { contentDescription = "StrengthPasswordMessage" },
//                text = buildAnnotatedString {
//                    append(stringResource(id = R.string.warning_password_level))
//                    withStyle(style = SpanStyle(color = ActivePrimary)) {
//                        when (strengthPasswordType) {
//                            StrengthPasswordTypes.STRONG ->
//                                append(" ${stringResource(id = R.string.warning_password_level_strong)}")
//                            StrengthPasswordTypes.WEAK ->
//                                append(" ${stringResource(id = R.string.warning_password_level_weak)}")
//                        }
//                    }
//                },
//                style = MaterialTheme.typography.body2
//            )
//        }
        if (alwaysShownMessage || hasError) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                message,
                style = if (hasError) MaterialTheme.typography.body2.copy(color = ErrorPrimary)
                else MaterialTheme.typography.body2.copy(color = ContentPrimary),
            )
        }
    }
}


private fun strengthChecker(password: String): StrengthPasswordTypes =
    when {
        REGEX_STRONG_PASSWORD.toRegex().containsMatchIn(password) -> StrengthPasswordTypes.STRONG
        else -> StrengthPasswordTypes.WEAK
    }

enum class StrengthPasswordTypes {
    STRONG,
    WEAK
}

private const val REGEX_STRONG_PASSWORD =
    "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})"

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    SubmissionComposeTheme {
        Column {

//            BaseTextField(
//                value = "",
//                onValueChange = {},
//                label = "Testing",
//                placeholder = "Testing",
//            )
//            BaseTextField(
//                value = "",
//                onValueChange = {},
//                label = "Testing",
//                placeholder = "Testing",
//                hasError = true,
//                message = "Error Message",
//                isMustFilled = true,
//                alwaysShownMessage = true,
//            )
            PasswordTextField(
                text = "",
                onTextChanged = {},
                label = "Password",
                placeholder = "Password",
                message = "Password",
            )
            SearchTextField(
                value = "",
                onValueChange = {},
                placeholder = "Search",
            )
        }
    }
}