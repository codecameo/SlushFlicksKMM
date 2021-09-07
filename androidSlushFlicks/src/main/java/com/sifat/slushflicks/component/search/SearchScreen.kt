package com.sifat.slushflicks.component.search

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.statusBarsPadding
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.component.movie.ShowListComponent
import com.sifat.slushflicks.component.search.ShowType.MOVIE
import com.sifat.slushflicks.component.search.ShowType.TV_SHOW
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.viewaction.SearchViewAction.SearchResultViewAction
import com.sifat.slushflicks.viewaction.SearchViewAction.UpdateShowTypeViewAction
import com.sifat.slushflicks.viewevents.SearchViewEvent.LoadMoreShowViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.RefreshShowViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.SearchShowViewEvent
import com.sifat.slushflicks.viewevents.SearchViewEvent.UpdateShowTypeViewEvent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@FlowPreview
@ExperimentalCoilApi
@Composable
fun SearchScreen(
    movieSelected: (ShowModel) -> Unit = {},
    tvShowSelected: (ShowModel) -> Unit = {}
) {
    val inputDelay = 500L
    val searchViewModel = getViewModel<SearchViewModel>()
    var searchResult by remember { mutableStateOf(emptyList<ShowModel>()) }
    val keyboardController = LocalSoftwareKeyboardController.current
    var query by remember { mutableStateOf(searchViewModel.viewState.query) }
    val bottomState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var showType by remember { mutableStateOf(searchViewModel.viewState.showType) }
    var showEmptyState by remember { mutableStateOf(true) }
    var searching by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(query) {
        searchViewModel.viewActionState.onEach { action ->
            when (action) {
                is SearchResultViewAction -> {
                    searching = action.viewState is ViewState.Loading && searchResult.isEmpty()
                    (action.viewState as? Success)?.data?.let { list ->
                        searchResult = list
                        showEmptyState = list.isEmpty()
                        if (list.isNotEmpty()) keyboardController?.hide()
                        if (bottomState.bottomSheetState.isExpanded) {
                            coroutineScope.launch { bottomState.bottomSheetState.collapse() }
                        }
                    }
                }
                is UpdateShowTypeViewAction -> {
                    showType = action.showType
                    searchViewModel.viewEventState.value = RefreshShowViewEvent()
                }
            }
        }.launchIn(this)
        snapshotFlow { query }
            .onEach { showEmptyState = false }
            .debounce(inputDelay)
            .distinctUntilChanged()
            .collect {
                searchViewModel.viewEventState.value = SearchShowViewEvent(it)
            }
    }
    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomState,
        sheetContent = {
            FilterList(showType) {
                searchViewModel.viewEventState.value = UpdateShowTypeViewEvent(it)
                coroutineScope.launch { bottomState.bottomSheetState.collapse() }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .statusBarsPadding()
        ) {
            SearchBarComponent(query = query) { query = it }
            Box(modifier = Modifier.background(MaterialTheme.colors.primary)) {
                val listState = rememberLazyListState()
                if (!searching && showEmptyState) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 32.dp)
                            .align(Alignment.Center),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        text = if (query.isNotBlank()) {
                            String.format(
                                stringResource(id = R.string.text_no_result_found),
                                getShowName(context, showType),
                                query
                            )
                        } else {
                            String.format(
                                stringResource(id = R.string.text_no_recent_result_found),
                                getShowName(context, showType)
                            )
                        }
                    )
                }

                if (searching) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colors.secondary
                    )
                }

                ShowListComponent(
                    listState = listState,
                    label = query,
                    showList = searchResult,
                    showSelected = if (showType == MOVIE) movieSelected else tvShowSelected
                ) {
                    searchViewModel.viewEventState.value = LoadMoreShowViewEvent()
                }

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp, 16.dp),
                    onClick = { coroutineScope.launch { bottomState.bottomSheetState.expand() } }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filter Show",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun FilterList(showType: ShowType, onSelected: (ShowType) -> Unit = {}) {
    Column {
        FilterItem(stringResource(id = R.string.title_movie), showType == MOVIE) {
            onSelected(MOVIE)
        }
        FilterItem(stringResource(id = R.string.title_tv_show), showType == TV_SHOW) {
            onSelected(TV_SHOW)
        }
    }
}

@Composable
fun FilterItem(label: String, selected: Boolean, onSelected: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelected()
            }
            .padding(16.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.subtitle1)
        if (selected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = label,
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .wrapContentWidth()
            )
        }
    }
}

fun getShowName(context: Context, showType: ShowType): String {
    return when (showType) {
        MOVIE -> context.getString(R.string.title_movie)
        TV_SHOW -> context.getString(R.string.title_tv_show)
    }
}
