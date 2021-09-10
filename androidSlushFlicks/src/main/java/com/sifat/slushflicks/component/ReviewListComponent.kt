package com.sifat.slushflicks.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sifat.slushflicks.R
import com.sifat.slushflicks.domain.model.ReviewModel

@Composable
fun ReviewListComponent(
    modifier: Modifier = Modifier,
    reviews: List<ReviewModel>,
    loadMore: () -> Unit,
    listState: LazyListState = rememberLazyListState(),
) {
    val loadMoreCallback by rememberUpdatedState(newValue = loadMore)

    Text(
        modifier = modifier.padding(horizontal = 16.dp),
        text = stringResource(id = R.string.text_review),
        style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary)
    )
    Spacer(modifier = Modifier.height(8.dp))
    for (review in reviews) {
        ReviewItem(
            reviewModel = review,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)
        )
    }

    /*LazyColumn(modifier = modifier.verticalScroll(state = rememberLazyListState())) {
        items(reviews) { review ->
            ReviewItem(
                reviewModel = review,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            )
        }
    }

    if (reviews.isNotEmpty()) {
        InfiniteListHandler(
            listState = listState,
            onLoadMore = loadMoreCallback
        )
    }*/
}

@Composable
fun ReviewItem(modifier: Modifier = Modifier, reviewModel: ReviewModel) {
    Card(
        modifier = modifier.background(color = MaterialTheme.colors.primaryVariant),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = modifier.padding(6.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = reviewModel.content,
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSecondary)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 6.dp),
                text = reviewModel.author,
                style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.secondary)
            )
        }
    }
}
