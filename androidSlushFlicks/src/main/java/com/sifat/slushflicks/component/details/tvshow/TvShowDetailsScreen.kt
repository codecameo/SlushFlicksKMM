package com.sifat.slushflicks.component.details.tvshow

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
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
import com.sifat.slushflicks.component.CollapsingTopBar
import com.sifat.slushflicks.component.ReviewListComponent
import com.sifat.slushflicks.component.details.CastComponent
import com.sifat.slushflicks.component.details.RelatedShowComponent
import com.sifat.slushflicks.component.details.ShowInfoComponent
import com.sifat.slushflicks.component.getErrorMessage
import com.sifat.slushflicks.component.getGenreList
import com.sifat.slushflicks.component.shareShow
import com.sifat.slushflicks.component.showTrailer
import com.sifat.slushflicks.component.tvshow.EpisodeComponent
import com.sifat.slushflicks.component.verticalGradientTint
import com.sifat.slushflicks.data.BULLET_SIGN
import com.sifat.slushflicks.data.SPACE
import com.sifat.slushflicks.domain.model.ReviewModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.domain.model.TvShowModel
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

private const val minImageAspectRatio = 0.95f
private const val maxImageAspectRatio = 5f

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
            tvShowModel = tvShowModel,
            aspectRatio = currentAspectRatio,
            snackbarState = snackBarState
        )
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            tvShowModel = tvShowModel,
            aspectRatio = currentAspectRatio,
            canShare = canShare,
            onBack = onBackCallBack,
            shareShow = {
                viewModel.viewEventState.value = ShareViewEvent(tvShowModel)
            }
        )
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    tvShowModel: TvShowModel,
    aspectRatio: Float,
    canShare: Boolean,
    onBack: () -> Unit,
    shareShow: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(aspectRatio)
    ) {
        CollapsingTopBar(
            collapseFactor = aspectRatio.inRange(maxImageAspectRatio, minImageAspectRatio),
            modifier = Modifier.statusBarsPadding()
        ) {
            Icon(
                modifier = Modifier
                    .wrapContentWidth()
                    .layoutId(CollapsingTopBar.BACK_ID)
                    .clickable { onBack() }
                    .padding(16.dp),
                imageVector = Icons.Filled.ArrowBack,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = stringResource(id = R.string.text_back)
            )
            Icon(
                modifier = Modifier
                    .wrapContentSize()
                    .layoutId(CollapsingTopBar.SHARE_ID)
                    .clickable { if (canShare) shareShow() }
                    .padding(16.dp),
                imageVector = Icons.Filled.Share,
                tint = MaterialTheme.colors.onPrimary.copy(alpha = if (canShare) 1f else 0.5f),
                contentDescription = stringResource(id = R.string.title_share)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId(CollapsingTopBar.TITLE_ID)
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                text = tvShowModel.title,
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = (getFontSize(aspectRatio)).sp
                ),
                maxLines = if (aspectRatio > 3f) 1 else 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CollapseImage(tvShowModel: TvShowModel, aspectRatio: Float, snackbarState: SnackbarHostState) {
    val gradientColor = listOf(
        MaterialTheme.colors.primary.copy(alpha = 0.3f),
        MaterialTheme.colors.primary.copy(alpha = 0.3f),
        MaterialTheme.colors.primary
    )
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.aspectRatio(aspectRatio)) {
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
                    .graphicsLayer {
                        aspectRatio
                            .inRange(3f, minImageAspectRatio)
                            .let { ratio ->
                                alpha = 1f - ratio
                                scaleX = 1f * alpha
                                scaleY = 1f * alpha
                            }
                    }
                    .background(
                        color = MaterialTheme.colors.onSecondary.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .padding(6.dp),
                onClick = {
                    if (tvShowModel.video.isNotEmpty()) {
                        if (!showTrailer(context = context, tvShowModel.video)) {
                            coroutineScope.launch {
                                snackbarState.showSnackbar(context.getString(R.string.install_youtube))
                            }
                        }
                    }
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

fun getFontSize(aspectRatio: Float): Float {
    return 22f + (4f - (4f * aspectRatio.inRange(maxImageAspectRatio, minImageAspectRatio)))
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
