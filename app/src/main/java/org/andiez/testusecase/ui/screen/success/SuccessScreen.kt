package org.andiez.testusecase.ui.screen.success

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.andiez.common.util.CommonUtils
import org.andiez.testusecase.R
import org.andiez.testusecase.ui.component.button.PrimaryRegularButton
import org.andiez.testusecase.ui.theme.BackgroundTertiary
import org.andiez.testusecase.ui.theme.ContentSecondary
import org.andiez.testusecase.ui.theme.MutePrimary

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@Composable
fun SuccessScanner(
    modifier: Modifier = Modifier,
    viewModel: SuccessViewModel,
    goToMainScreen: () -> Unit,
) {
    BackHandler(enabled = true) {
        goToMainScreen()
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(75.dp)
                    .background(MutePrimary),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = R.string.txt_payment_success),
                style = MaterialTheme.typography.h4.copy(
                    color = BackgroundTertiary,
                    fontWeight = FontWeight.Bold,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Berhasil melakukan transaksi ke ${viewModel.merchantName} dengan nominal sebesar Rp ${
                    CommonUtils.formatThousands(
                        viewModel.amount
                    )
                }",
                style = MaterialTheme.typography.body2.copy(
                    color = ContentSecondary,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            PrimaryRegularButton(
                onClick = goToMainScreen,
                text = stringResource(id = R.string.txt_back_to_home),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}