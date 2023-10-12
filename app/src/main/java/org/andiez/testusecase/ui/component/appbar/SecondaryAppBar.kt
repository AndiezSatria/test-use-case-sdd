package org.andiez.testusecase.ui.component.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.andiez.testusecase.R
import org.andiez.testusecase.ui.theme.ActivePrimary
import org.andiez.testusecase.ui.theme.BackgroundPrimary
import org.andiez.testusecase.ui.theme.ContentFourth
import org.andiez.testusecase.ui.theme.ContentPrimary

/**
 * Created by AndiezSatria on 08/06/2023.
 */

@Composable
fun SecondaryAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: (() -> Unit)? = null,
    title: String,
    centeredTitle: Boolean = false,
    actions: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(ActivePrimary)
            .padding(
                start = 24.dp,
                top = 48.dp,
                bottom = 24.dp,
                end = 24.dp
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
            ) {
                onBackPressed?.let {
                    IconButton(
                        onClick = { onBackPressed() },
                        modifier = Modifier.size(24.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_left_white),
                            tint = BackgroundPrimary,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Text(
                    title,
                    maxLines = 1,
                    style = MaterialTheme.typography.h4.copy(
                        color = BackgroundPrimary
                    ),
                    textAlign = if (centeredTitle) TextAlign.Center else TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    content = actions
                )
            }
        }
    }
}

@Composable
fun SecondaryBackgroundAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: (() -> Unit)? = null,
    title: String,
    centeredTitle: Boolean = false,
    actions: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .padding(
                start = 24.dp,
                top = 48.dp,
                bottom = 24.dp,
                end = 24.dp
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
            ) {
                onBackPressed?.let {
                    IconButton(
                        onClick = { onBackPressed() },
                        modifier = Modifier.size(24.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_left_white),
                            tint = ContentPrimary,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Text(
                    title,
                    maxLines = 1,
                    style = MaterialTheme.typography.h4.copy(
                        color = ContentPrimary
                    ),
                    textAlign = if (centeredTitle) TextAlign.Center else TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    content = actions
                )
            }
        }
    }
}

@Composable
fun SecondaryAppBar(
    modifier: Modifier = Modifier,
    onBackPressed: (() -> Unit)? = null,
    title: String,
    subtitle: String,
    actions: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundPrimary)
            .padding(
                start = 24.dp,
                top = 48.dp,
                bottom = 24.dp,
                end = 24.dp
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                onBackPressed?.let {
                    IconButton(
                        onClick = { onBackPressed() },
                        modifier = Modifier.size(24.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_left_white),
                            tint = ContentPrimary,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp),
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        title,
                        maxLines = 1,
                        style = MaterialTheme.typography.h4.copy(
                            color = ContentPrimary
                        ),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        subtitle,
                        maxLines = 1,
                        style = MaterialTheme.typography.caption.copy(
                            color = ContentFourth,
                        ),
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    content = actions
                )
            }
        }
    }
}