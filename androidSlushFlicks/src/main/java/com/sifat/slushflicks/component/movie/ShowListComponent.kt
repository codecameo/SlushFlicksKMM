package com.sifat.slushflicks.component.movie

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.sifat.slushflicks.component.InfiniteListHandler
import com.sifat.slushflicks.component.ShowListItem
import com.sifat.common.domain.model.ShowModel

@ExperimentalCoilApi
@Composable
fun ShowListComponent(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    label: String,
    showList: List<ShowModel>,
    showSelected: (ShowModel) -> Unit = {},
    loadMore: () -> Unit
) {
    val loadMoreCallback by rememberUpdatedState(newValue = loadMore)
    val selectedShowCallback by rememberUpdatedState(newValue = showSelected)

    LaunchedEffect(label) {
        listState.animateScrollToItem(0)
    }
    LazyColumn(modifier = modifier.fillMaxSize(), state = listState) {
        items(showList, key = { it.id }) { show ->
            ShowListItem(
                modifier = Modifier.clickable { selectedShowCallback(show) },
                showModel = show
            )
        }
    }

    if (showList.isNotEmpty()) {
        InfiniteListHandler(
            listState = listState,
            onLoadMore = loadMoreCallback
        )
    }
}
