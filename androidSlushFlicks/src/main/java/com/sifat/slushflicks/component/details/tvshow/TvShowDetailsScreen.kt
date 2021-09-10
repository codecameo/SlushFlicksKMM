package com.sifat.slushflicks.component.details.tvshow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.component.ReviewListComponent
import com.sifat.slushflicks.component.details.CastComponent
import com.sifat.slushflicks.component.details.RelatedShowComponent
import com.sifat.slushflicks.component.details.ShowInfoComponent
import com.sifat.slushflicks.component.getGenreList
import com.sifat.slushflicks.component.verticalGradientTint
import com.sifat.slushflicks.domain.model.ReviewModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.model.TvShowModel
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchRecommendedTvShowViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchReviewViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchSimilarTvShowViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchTvShowDetailsViewAction
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchRelatedTvShowViewEvent
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchReviewViewEvent
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchTvShowDetailsViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@Composable
fun TvShowDetailsScreen(tvShowId: Long, onBack: () -> Unit) {
    val viewModel = getViewModel<TvShowDetailsViewModel>()
    var similar by remember { mutableStateOf(emptyList<ShowModel>()) }
    var recommendation by remember { mutableStateOf(emptyList<ShowModel>()) }
    var tvShowModel by remember { mutableStateOf(TvShowModel()) }
    val scrollState = rememberScrollState()
    var userReviews by remember { mutableStateOf(emptyList<ReviewModel>()) }
    var currentTvShowId by remember { mutableStateOf(tvShowId) }
    var canShare by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val gradientColor = listOf(
        MaterialTheme.colors.primary.copy(alpha = 0.3f),
        MaterialTheme.colors.primary.copy(alpha = 0.3f),
        MaterialTheme.colors.primary
    )
    LaunchedEffect(currentTvShowId) {
        viewModel.viewActionState.onEach { action ->
            when (action) {
                is FetchTvShowDetailsViewAction -> {
                    (action.viewState as? Success)?.data?.let { movie ->
                        canShare = true
                        tvShowModel = movie
                    }
                }
                is FetchSimilarTvShowViewAction -> {
                    (action.viewState as? Success)?.data?.let { shows ->
                        similar = shows
                    }
                }
                is FetchRecommendedTvShowViewAction -> {
                    (action.viewState as? Success)?.data?.let { shows ->
                        recommendation = shows
                    }
                }
                is FetchReviewViewAction -> {
                    (action.viewState as? Success)?.data?.let { reviews ->
                        userReviews = reviews
                    }
                }
            }
        }.launchIn(this)
        snapshotFlow { currentTvShowId }.onEach {
            viewModel.viewEventState.value = FetchTvShowDetailsViewEvent(tvShowId = currentTvShowId)
            viewModel.viewEventState.value = FetchRelatedTvShowViewEvent(tvShowId = currentTvShowId)
            viewModel.viewEventState.value = FetchReviewViewEvent(tvShowId = currentTvShowId)
        }.launchIn(this)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.verticalScroll(state = scrollState)
        ) {
            Box(modifier = Modifier.aspectRatio(0.95f)) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalGradientTint(gradientColor),
                    painter = rememberImagePainter(
                        data = tvShowModel.posterPath,
                        builder = {
                            crossfade(false)
                            placeholder(R.drawable.placeholder)
                            error(R.drawable.placeholder)
                        }
                    ),
                    contentDescription = tvShowModel.title,
                    contentScale = ContentScale.Crop
                )

                if (tvShowModel.video.isNotEmpty()) {
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(
                                color = MaterialTheme.colors.onSecondary.copy(alpha = 0.5f),
                                shape = CircleShape
                            )
                            .padding(6.dp),
                        onClick = {
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Filled.PlayArrow,
                            tint = MaterialTheme.colors.onPrimary,
                            contentDescription = stringResource(id = R.string.text_trailer)
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    text = tvShowModel.title,
                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onPrimary),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = getGenreList(tvShowModel.genres),
                style = MaterialTheme.typography.caption.copy(
                    fontSize = 11.sp,
                    color = MaterialTheme.colors.secondary
                )
            )

            ShowInfoComponent(
                voteAvg = tvShowModel.voteAvg,
                voteCount = tvShowModel.voteCount,
                releaseDate = tvShowModel.releaseDate,
                runtime = tvShowModel.runtime
            )

            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.text_overview),
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary)
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 4.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_popularity),
                    contentDescription = stringResource(id = R.string.text_popularity),
                    tint = MaterialTheme.colors.secondary
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = tvShowModel.popularity.toString(),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.onSecondary,
                        fontSize = 10.sp
                    )
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
                text = tvShowModel.overview,
                style = MaterialTheme.typography.caption.copy(MaterialTheme.colors.onSecondary)
            )
            if (tvShowModel.casts.isNotEmpty()) {
                CastComponent(casts = tvShowModel.casts)
            }
            if (recommendation.isNotEmpty()) {
                RelatedShowComponent(
                    showModels = recommendation,
                    title = stringResource(id = R.string.text_recommended)
                ) {
                    coroutineScope.launch { scrollState.animateScrollTo(0) }
                    currentTvShowId = it.id
                }
            }
            if (similar.isNotEmpty()) {
                RelatedShowComponent(
                    showModels = similar,
                    title = stringResource(id = R.string.text_similar)
                ) {
                    coroutineScope.launch { scrollState.animateScrollTo(0) }
                    currentTvShowId = it.id
                }
            }
            if (userReviews.isNotEmpty()) {
                ReviewListComponent(
                    modifier = Modifier.padding(top = 8.dp),
                    reviews = userReviews,
                    loadMore = {
                    }
                )
            }
        }
        Icon(
            modifier = Modifier
                .statusBarsPadding()
                .clickable { onBack() }
                .padding(16.dp),
            imageVector = Icons.Filled.ArrowBack,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = stringResource(id = R.string.text_back)
        )

        if (canShare) {
            Icon(
                modifier = Modifier
                    .statusBarsPadding()
                    .align(Alignment.TopEnd)
                    .clickable { onBack() }
                    .padding(16.dp),
                imageVector = Icons.Filled.Share,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = stringResource(id = R.string.title_share)
            )
        }
    }
}
