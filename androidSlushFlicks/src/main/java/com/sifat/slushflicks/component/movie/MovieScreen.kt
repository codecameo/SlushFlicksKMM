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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.component.ShowTypeChip
import com.sifat.slushflicks.component.movie.model.CollectionListModel
import com.sifat.slushflicks.viewaction.MovieListViewAction.FetchCollectionViewAction
import com.sifat.slushflicks.viewevents.MovieListViewEvent.FetchCollectionViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieScreen() {
    val movieViewModel = getViewModel<MovieViewModel>()
    val collectionItems = remember {
        mutableStateOf(emptyList<CollectionListModel>())
    }
    LaunchedEffect(true) {
        movieViewModel.viewActionState.onEach { action ->
            when (action) {
                is FetchCollectionViewAction -> {
                    (action.viewState as? ViewState.Success)?.data?.let {
                        collectionItems.value = it
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
                    movieViewModel.updateLabel(label)
                }
            }
        }
    }
}
