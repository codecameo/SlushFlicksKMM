package com.sifat.slushflicks.component.tvshow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.statusBarsPadding
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.component.ShowTypeChip
import com.sifat.slushflicks.component.getErrorMessage
import com.sifat.slushflicks.component.home.model.CollectionListModel
import com.sifat.slushflicks.component.movie.ShowListComponent
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.viewaction.TvCollectionViewAction.FetchCollectionViewAction
import com.sifat.slushflicks.viewaction.TvCollectionViewAction.FetchTvListViewAction
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.FetchCollectionViewEvent
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.FetchTvShowListViewEvent
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.LoadMoreTvShowListViewEvent
import com.sifat.slushflicks.viewevents.TvCollectionViewEvent.UpdateCollectionViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@Composable
fun TvShowScreen(scaffoldState: ScaffoldState, showSelected: (ShowModel) -> Unit = {}) {
    val tvShowViewModel = getViewModel<TvShowViewModel>()
    val collectionItems = remember { mutableStateOf(emptyList<CollectionListModel>()) }
    val showList = remember { mutableStateOf(emptyList<ShowModel>()) }
    val snackBarState = remember { scaffoldState.snackbarHostState }
    val context = LocalContext.current
    LaunchedEffect(true) {
        tvShowViewModel.viewActionState.onEach { action ->
            when (action) {
                is FetchCollectionViewAction -> {
                    (action.viewState as? Success)?.data?.let {
                        collectionItems.value = it
                        tvShowViewModel.viewEventState.value = FetchTvShowListViewEvent
                    }
                    (action.viewState as? ViewState.Error)?.let {
                        launch {
                            snackBarState.showSnackbar(
                                message = getErrorMessage(
                                    context = context,
                                    errorMessage = it.errorMessage,
                                    errorCode = it.errorCode
                                )
                            )
                        }
                    }
                }
                is FetchTvListViewAction -> {
                    (action.viewState as? Success)?.data?.let {
                        showList.value = it
                    }
                    (action.viewState as? ViewState.Error)?.let {
                        launch {
                            snackBarState.showSnackbar(
                                message = getErrorMessage(
                                    context = context,
                                    errorMessage = it.errorMessage,
                                    errorCode = it.errorCode
                                )
                            )
                        }
                    }
                }
            }
        }.launchIn(this)
        tvShowViewModel.viewEventState.value = FetchCollectionViewEvent
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            text = stringResource(id = R.string.title_tv_show),
            style = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .statusBarsPadding()
                .padding(8.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.surface),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(collectionItems.value, key = { it.toString() }) {
                ShowTypeChip(it) { label ->
                    tvShowViewModel.viewEventState.value = UpdateCollectionViewEvent(label = label)
                    tvShowViewModel.viewEventState.value = FetchTvShowListViewEvent
                }
            }
        }
        ShowListComponent(
            label = tvShowViewModel.viewState.currentSelectedLabel,
            showList = showList.value,
            showSelected = showSelected
        ) {
            tvShowViewModel.viewEventState.value = LoadMoreTvShowListViewEvent()
        }
    }
}
