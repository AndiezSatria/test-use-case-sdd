package org.andiez.testusecase.ui.screen.main

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.ajalt.timberkt.d
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import org.andiez.common.domain.model.LineData
import org.andiez.common.domain.model.PieDataDomain
import org.andiez.common.domain.model.PieDataEntry
import org.andiez.core.common.UiState
import org.andiez.testusecase.R
import org.andiez.testusecase.ui.component.appbar.SecondaryAppBar
import org.andiez.testusecase.ui.component.button.PrimaryRegularButton
import org.andiez.testusecase.ui.theme.ActivePrimary
import org.andiez.testusecase.ui.theme.BackgroundPrimary
import org.andiez.testusecase.ui.theme.BlueNeon
import org.andiez.testusecase.ui.theme.ContentPrimary
import org.andiez.testusecase.ui.theme.PurpleNeon
import org.andiez.testusecase.ui.theme.RedNeon
import org.andiez.testusecase.ui.theme.GreenNeon
import org.andiez.testusecase.ui.util.rememberChartStyle
import org.andiez.testusecase.ui.util.rememberMarker

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onChartClicked: (String) -> Unit,
) {
    val mainUiState = viewModel.uiState.collectAsState().value
    Column(modifier = modifier.fillMaxSize()) {
        SecondaryAppBar(
            title = stringResource(id = R.string.txt_app_title),
            actions = {},
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Pie Chart Bulanan",
                maxLines = 1,
                style = MaterialTheme.typography.h6.copy(
                    color = ContentPrimary
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            PieChartContent(
                uiState = mainUiState.pieUiState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 24.dp),
                onRetryClick = { viewModel.getPieData() },
                onClick = onChartClicked,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Line Chart Bulanan",
                maxLines = 1,
                style = MaterialTheme.typography.h6.copy(
                    color = ContentPrimary
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            )
            LineChartContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 24.dp),
                uiState = mainUiState.lineUiState,
                onRetryClick = { viewModel.getLineData() }
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Composable
fun LineChartContent(
    modifier: Modifier = Modifier,
    uiState: UiState<LineData>,
    onRetryClick: () -> Unit,
) {
    when (uiState) {
        is UiState.Success -> {
            val data = uiState.data
            val chartEntryModel =
                entryModelOf(data.data.monthly.mapIndexed { i, item ->
                    FloatEntry(
                        (i + 1).toFloat(),
                        item.toFloat()
                    )
                })
            ProvideChartStyle(rememberChartStyle(chartColors = listOf(GreenNeon))) {

                Chart(
                    chart = lineChart(),
                    model = chartEntryModel,
                    startAxis = rememberStartAxis(
                        guideline = null,
                        horizontalLabelPosition = VerticalAxis.HorizontalLabelPosition.Inside,
                        titleComponent = textComponent(
                            color = BackgroundPrimary,
                            background = shapeComponent(Shapes.pillShape, ActivePrimary),
                            padding = dimensionsOf(8.dp, 2.dp),
                            margins = dimensionsOf(end = 4.dp),
                            typeface = Typeface.MONOSPACE,
                        ),
                        title = "Persentase",
                    ),
                    bottomAxis = rememberBottomAxis(
                        titleComponent = textComponent(
                            color = BackgroundPrimary,
                            background = shapeComponent(Shapes.pillShape, ActivePrimary),
                            padding = dimensionsOf(8.dp, 2.dp),
                            margins = dimensionsOf(end = 4.dp),
                            typeface = Typeface.MONOSPACE,
                        ),
                        title = "Bulan",
                    ),
                    marker = rememberMarker(),
                    modifier = modifier,
                )
            }
        }

        is UiState.Loading -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    modifier = modifier.padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(36.dp),
                        color = ActivePrimary,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Loading...",
                        style = MaterialTheme.typography.body2.copy(
                            color = ContentPrimary,
                        ),
                    )
                }
            }
        }

        is UiState.Error -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Terjadi Masalah : ${uiState.errorMessage}",
                        style = MaterialTheme.typography.body2.copy(
                            color = ContentPrimary,
                        ),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryRegularButton(
                        onClick = { onRetryClick() },
                        text = "Muat Ulang"
                    )
                }
            }
        }

        else -> {}
    }
}

@Composable
fun PieChartContent(
    modifier: Modifier = Modifier,
    uiState: UiState<PieDataDomain>,
    onRetryClick: () -> Unit,
    onClick: (String) -> Unit,
) {
    when (uiState) {
        is UiState.Success -> {
            PieChart(
                modifier = modifier,
                onClick = { onClick(it) },
                pieData = uiState.data.data,
            )
        }

        is UiState.Loading -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    modifier = modifier.padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(36.dp),
                        color = ActivePrimary,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "Loading...",
                        style = MaterialTheme.typography.body2.copy(
                            color = ContentPrimary,
                        ),
                    )
                }
            }
        }

        is UiState.Error -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Terjadi Masalah : ${uiState.errorMessage}",
                        style = MaterialTheme.typography.body2.copy(
                            color = ContentPrimary,
                        ),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PrimaryRegularButton(
                        onClick = { onRetryClick() },
                        text = "Muat Ulang"
                    )
                }
            }
        }

        else -> {}
    }
}

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    pieData: List<PieDataEntry>,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Crossfade(targetState = pieData) { pieData ->
            AndroidView(
                factory = { context ->
                    PieChart(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                        )
                        this.description.isEnabled = false
                        this.legend.isEnabled = true
                        this.legend.textSize = 12F
                        this.legend.horizontalAlignment =
                            Legend.LegendHorizontalAlignment.CENTER
                        this.setOnChartValueSelectedListener(
                            object : OnChartValueSelectedListener {
                                override fun onValueSelected(e: Entry?, h: Highlight?) {
                                    e?.let { entry ->
                                        val pieEntry = entry as PieEntry
                                        d { "Entry ${pieEntry.label} : ${pieEntry.value}" }
                                        onClick(pieEntry.label)
                                    } ?: return
                                }

                                override fun onNothingSelected() {}

                            }
                        )
                        this.setEntryLabelColor(BackgroundPrimary.toArgb())
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                update = {
                    updatePieChartData(it, pieData)
                }
            )
        }
    }
}

private fun updatePieChartData(
    chart: PieChart,
    data: List<PieDataEntry>,
) {
    val entries = ArrayList<PieEntry>()
    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.percentage.toFloat(), item.label))
        val ds = PieDataSet(entries, "")
        ds.colors = arrayListOf(
            BlueNeon.toArgb(),
            PurpleNeon.toArgb(),
            RedNeon.toArgb(),
            GreenNeon.toArgb(),
        )
        ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
        ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
        ds.sliceSpace = 2f

        ds.valueTextColor = BackgroundPrimary.toArgb()
        ds.valueTextSize = 12f
        ds.valueTypeface = Typeface.DEFAULT_BOLD

        val d = PieData(ds)
        chart.data = d
        chart.invalidate()
    }
}