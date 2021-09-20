package com.sifat.slushflicks.component.details.tvshow

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.navigationBarsPadding
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ViewState
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.component.ReviewListComponent
import com.sifat.slushflicks.component.details.CastComponent
import com.sifat.slushflicks.component.details.CollapseImage
import com.sifat.slushflicks.component.details.RelatedShowComponent
import com.sifat.slushflicks.component.details.ShowInfoComponent
import com.sifat.slushflicks.component.details.TopBar
import com.sifat.slushflicks.component.details.maxImageAspectRatio
import com.sifat.slushflicks.component.details.minImageAspectRatio
import com.sifat.slushflicks.component.getErrorMessage
import com.sifat.slushflicks.component.getGenreList
import com.sifat.slushflicks.component.shareShow
import com.sifat.slushflicks.component.tvshow.EpisodeComponent
import com.sifat.common.data.BULLET_SIGN
import com.sifat.common.data.SPACE
import com.sifat.common.domain.model.ReviewModel
import com.sifat.common.domain.model.ShowModel
import com.sifat.common.domain.model.TvShowModel
import com.sifat.slushflicks.utils.ext.inRange
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchRecommendedTvShowViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchReviewViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchSimilarTvShowViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.FetchTvShowDetailsViewAction
import com.sifat.slushflicks.viewaction.TvShowDetailsViewAction.ShareViewAction
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchRelatedTvShowViewEvent
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchReviewViewEvent
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.FetchTvShowDetailsViewEvent
import com.sifat.slushflicks.viewevents.TvShowDetailsViewEvent.ShareViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@Composable
fun TvShowDetailsScreen(scaffoldState: ScaffoldState, tvShowId: Long, onBack: () -> Unit) {
    val viewModel = getViewModel<TvShowDetailsViewModel>()
    var similar by remember { mutableStateOf(emptyList<ShowModel>()) }
    var recommendation by remember { mutableStateOf(emptyList<ShowModel>()) }
    var tvShowModel by remember { mutableStateOf(TvShowModel()) }
    val scrollState = rememberScrollState()
    var userReviews by remember { mutableStateOf(emptyList<ReviewModel>()) }
    var currentTvShowId by remember { mutableStateOf(tvShowId) }
    var canShare by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onBackCallBack by rememberUpdatedState(newValue = onBack)
    val snackBarState = remember { scaffoldState.snackbarHostState }
    val context = LocalContext.current
    val maxImageHeight = context.resources.displayMetrics.widthPixels / minImageAspectRatio
    val currentAspectRatio by remember {
        derivedStateOf {
            val collapseRange = maxOf(maxImageHeight - scrollState.value, 0f)
            minOf(maxImageAspectRatio, (maxImageHeight / collapseRange))
        }
    }
    LaunchedEffect(true) {
        viewModel.viewActionState.onEach { action ->
            when (action) {
                is FetchTvShowDetailsViewAction -> {
                    (action.viewState as? Success)?.data?.let { tvShow ->
                        canShare = true
                        tvShowModel = tvShow
                    }
                    (action.viewState as? ViewState.Error)?.let {
                        snackBarState.showSnackbar(
                            message = getErrorMessage(context, it.errorCode, it.errorMessage)
                        )
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
                is ShareViewAction -> {
                    canShare = action.viewState !is ViewState.Loading
                    when (action.viewState) {
                        is ViewState.Error -> snackBarState.showSnackbar(
                            message = getErrorMessage(
                                context,
                                action.viewState.errorCode,
                                action.viewState.errorMessage
                            )
                        )
                        is ViewState.Loading -> { // No Op
                        }
                        is Success -> if (!shareShow(context, action.viewState.data)) {
                            coroutineScope.launch {
                                snackBarState.showSnackbar(context.getString(R.string.error_no_app_found))
                            }
                        }
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
        modifier = Modifier.fillMaxSize()
    ) {
        Body(
            tvShowModel = tvShowModel,
            scrollState = scrollState,
            recommendation = recommendation,
            similar = similar,
            userReviews = userReviews,
        ) {
            coroutineScope.launch { scrollState.animateScrollTo(0) }
            currentTvShowId = it.id
        }
        CollapseImage(
            title = tvShowModel.title,
            posterPath = tvShowModel.posterPath,
            video = tvShowModel.video,
            aspectRatio = currentAspectRatio,
            snackbarState = snackBarState
        )
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            title = tvShowModel.title,
            aspectRatio = currentAspectRatio,
            canShare = canShare,
            onBack = onBackCallBack,
            shareShow = {
                viewModel.viewEventState.value = ShareViewEvent(tvShowModel)
            }
        )
    }
}

@ExperimentalCoilApi
@Composable
fun Body(
    modifier: Modifier = Modifier,
    tvShowModel: TvShowModel,
    recommendation: List<ShowModel>,
    similar: List<ShowModel>,
    userReviews: List<ReviewModel>,
    scrollState: ScrollState,
    onShowSelected: (ShowModel) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(state = scrollState)
    ) {
        Spacer(modifier = Modifier.aspectRatio(minImageAspectRatio))
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(
                text = getSeasonEpisode(
                    LocalContext.current,
                    tvShowModel.numOfSeason,
                    tvShowModel.numOfEpisode
                ),
                style = MaterialTheme.typography.overline.copy(
                    color = MaterialTheme.colors.onPrimary
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .background(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colors.secondary
                    )
                    .padding(horizontal = 4.dp)
                    .align(Alignment.CenterVertically),
                text = tvShowModel.status.uppercase(),
                style = MaterialTheme.typography.overline.copy(fontSize = 10.sp)
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

        DirectorComponent(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
            directors = tvShowModel.directors
        )

        if (tvShowModel.casts.isNotEmpty()) {
            CastComponent(casts = tvShowModel.casts)
        }
        tvShowModel.nextEpisode?.let { episode ->
            EpisodeComponent(
                episodeModel = episode,
                title = stringResource(id = R.string.text_next_episode)
            )
        }
        tvShowModel.lastEpisode?.let { episode ->
            EpisodeComponent(
                episodeModel = episode,
                title = stringResource(id = R.string.text_last_episode)
            )
        }
        tvShowModel.seasons?.let { seasons ->
            if (seasons.isNotEmpty()) {
                SessionComponent(seasons = seasons)
            }
        }
        if (recommendation.isNotEmpty()) {
            RelatedShowComponent(
                showModels = recommendation,
                title = stringResource(id = R.string.text_recommended)
            ) {
                onShowSelected(it)
            }
        }
        if (similar.isNotEmpty()) {
            RelatedShowComponent(
                showModels = similar,
                title = stringResource(id = R.string.text_similar)
            ) {
                onShowSelected(it)
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
        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}

fun getSeasonEpisode(context: Context, seasonCount: Int, episodeCount: Int): String {
    return StringBuilder(context.getString(R.string.seasons))
        .append(SPACE)
        .append(seasonCount)
        .append(SPACE)
        .append(BULLET_SIGN)
        .append(SPACE)
        .append(context.getString(R.string.episodes))
        .append(SPACE)
        .append(episodeCount).toString()
}
