package com.sifat.slushflicks.component.movie

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import coil.annotation.ExperimentalCoilApi
import com.sifat.slushflicks.component.InfiniteListHandler
import com.sifat.slushflicks.component.ShowListItem
import com.sifat.slushflicks.domain.model.ShowModel

@ExperimentalCoilApi
@Composable
fun ShowListComponent(label: String, showList: List<ShowModel>, loadMore: () -> Unit) {
    val listState = rememberLazyListState()
    val loadMoreCallback by rememberUpdatedState(newValue = loadMore)

    LaunchedEffect(label) {
        listState.animateScrollToItem(0)
    }

    LazyColumn(state = listState) {
        items(showList, key = { it.id }) {
            ShowListItem(it)
        }
    }

    if (showList.isNotEmpty()) {
        InfiniteListHandler(
            listState = listState,
            onLoadMore = loadMoreCallback
        )
    }
}
