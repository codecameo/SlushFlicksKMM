package com.sifat.slushflicks.component.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.statusBarsPadding
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.component.ShowTypeChip
import com.sifat.slushflicks.component.home.model.CollectionListModel
import com.sifat.slushflicks.domain.model.ReviewModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.viewaction.MovieCollectionViewAction.FetchCollectionViewAction
import com.sifat.slushflicks.viewaction.MovieCollectionViewAction.FetchMovieListViewAction
import com.sifat.slushflicks.viewevents.MovieCollectionViewEvent.FetchCollectionViewEvent
import com.sifat.slushflicks.viewevents.MovieCollectionViewEvent.FetchMovieListViewEvent
import com.sifat.slushflicks.viewevents.MovieCollectionViewEvent.LoadMoreMovieListViewEvent
import com.sifat.slushflicks.viewevents.MovieCollectionViewEvent.UpdateCollectionViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@Composable
fun MovieScreen(showSelected: (ShowModel) -> Unit = {}) {
    val movieViewModel = getViewModel<MovieViewModel>()
    val collectionItems = remember { mutableStateOf(emptyList<CollectionListModel>()) }
    var showList by remember { mutableStateOf(emptyList<ShowModel>()) }
    val selectedShowCallback by rememberUpdatedState(newValue = showSelected)
    LaunchedEffect(true) {
        movieViewModel.viewActionState.onEach { action ->
            when (action) {
                is FetchCollectionViewAction -> {
                    (action.viewState as? Success)?.data?.let {
                        collectionItems.value = it
                        movieViewModel.viewEventState.value = FetchMovieListViewEvent
                    }
                }
                is FetchMovieListViewAction -> {
                    (action.viewState as? Success)?.data?.let {
                        showList = it
                    }
                }
            }
        }.launchIn(this)
        movieViewModel.viewEventState.value = FetchCollectionViewEvent
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        Text(
            text = stringResource(id = R.string.title_movie),
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
            items(collectionItems.value, key = {
                it.toString()
            }) {
                ShowTypeChip(it) { label ->
                    movieViewModel.viewEventState.value = UpdateCollectionViewEvent(label = label)
                    movieViewModel.viewEventState.value = FetchMovieListViewEvent
                }
            }
        }
        ShowListComponent(
            label = movieViewModel.viewState.currentSelectedLabel,
            showList = showList,
            showSelected = selectedShowCallback
        ) {
            movieViewModel.viewEventState.value = LoadMoreMovieListViewEvent()
        }
    }
}
