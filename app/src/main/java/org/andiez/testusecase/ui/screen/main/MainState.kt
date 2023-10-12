package org.andiez.testusecase.ui.screen.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * Created by AndiezSatria on 11/10/2023.
 */
class MainState(val lazyListState: LazyListState)

@Composable
fun rememberMainState(lazyListState: LazyListState = rememberLazyListState()): MainState = remember {
    MainState(lazyListState)
}