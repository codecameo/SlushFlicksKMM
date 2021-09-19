package com.sifat.slushflicks.component.details.movie

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.sifat.slushflicks.R
import com.sifat.slushflicks.ViewState.Error
import com.sifat.slushflicks.ViewState.Loading
import com.sifat.slushflicks.ViewState.Success
import com.sifat.slushflicks.component.ReviewListComponent
import com.sifat.slushflicks.component.details.CastComponent
import com.sifat.slushflicks.component.details.CollapseImage
import com.sifat.slushflicks.component.details.RelatedShowComponent
import com.sifat.slushflicks.component.details.ShowInfoComponent
import com.sifat.slushflicks.component.details.TopBar
import com.sifat.slushflicks.component.details.minImageAspectRatio
import com.sifat.slushflicks.component.getErrorMessage
import com.sifat.slushflicks.component.getGenreList
import com.sifat.slushflicks.component.shareShow
import com.sifat.slushflicks.domain.model.MovieModel
import com.sifat.slushflicks.domain.model.ReviewModel
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchMovieDetailsViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchRecommendedMovieViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchReviewViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.FetchSimilarMovieViewAction
import com.sifat.slushflicks.viewaction.MovieDetailsViewAction.ShareViewAction
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.FetchMovieDetailsViewEvent
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.FetchRelatedMovieViewEvent
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.FetchReviewViewEvent
import com.sifat.slushflicks.viewevents.MovieDetailsViewEvent.ShareViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@Composable
fun MovieDetailsScreen(scaffoldState: ScaffoldState, movieId: Long, onBack: () -> Unit) {
    val viewModel = getViewModel<MovieDetailsViewModel>()
    var similar by remember { mutableStateOf(emptyList<ShowModel>()) }
    var recommendation by remember { mutableStateOf(emptyList<ShowModel>()) }
    var movieModel by remember { mutableStateOf(MovieModel()) }
    var userReviews by remember { mutableStateOf(emptyList<ReviewModel>()) }
    var currentMovieId by remember { mutableStateOf(movieId) }
    var canShare by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState(0)
    val coroutineScope = rememberCoroutineScope()
    val onBackCallBack by rememberUpdatedState(newValue = onBack)
    val snackBarState = remember { scaffoldState.snackbarHostState }
    val context = LocalContext.current
    val width = context.resources.displayMetrics.widthPixels
    val maxImageHeight = width / minImageAspectRatio
    val statusBarHeight = LocalWindowInsets.current.statusBars.top
    val actionBarSize = LocalDensity.current.run { 56.dp.roundToPx() }
    val maxImageRatio by lazy { width.toFloat() / (statusBarHeight + actionBarSize) }
    val currentAspectRatio by remember {
        derivedStateOf {
            val collapseRange = maxOf(maxImageHeight - scrollState.value, 0f)
            minOf(maxImageRatio, (maxImageHeight / collapseRange))
        }
    }

    LaunchedEffect(true) {
        viewModel.viewActionState.onEach { action ->
            when (action) {
                is FetchMovieDetailsViewAction -> {
                    (action.viewState as? Success)?.data?.let { movie ->
                        canShare = true
                        movieModel = movie
                    }
                    (action.viewState as? Error)?.let {
                        snackBarState.showSnackbar(
                            message = getErrorMessage(context, it.errorCode, it.errorMessage)
                        )
                    }
                }
                is FetchSimilarMovieViewAction -> {
                    (action.viewState as? Success)?.data?.let { shows ->
                        similar = shows
                    }
                }
                is FetchRecommendedMovieViewAction -> {
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
                    canShare = action.viewState !is Loading
                    when (action.viewState) {
                        is Error -> snackBarState.showSnackbar(
                            message = getErrorMessage(
                                context,
                                action.viewState.errorCode,
                                action.viewState.errorMessage
                            )
                        )
                        is Loading -> { // No Op
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
        snapshotFlow { currentMovieId }.onEach {
            viewModel.viewEventState.value = FetchMovieDetailsViewEvent(movieId = currentMovieId)
            viewModel.viewEventState.value = FetchRelatedMovieViewEvent(movieId = currentMovieId)
            viewModel.viewEventState.value = FetchReviewViewEvent(movieId = currentMovieId)
        }.launchIn(this)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Body(
            movieModel = movieModel,
            scrollState = scrollState,
            recommendation = recommendation,
            similar = similar,
            userReviews = userReviews,
        ) {
            coroutineScope.launch { scrollState.animateScrollTo(0) }
            currentMovieId = it.id
        }
        CollapseImage(
            title = movieModel.title,
            posterPath = movieModel.posterPath,
            video = movieModel.video,
            aspectRatio = currentAspectRatio,
            snackbarState = snackBarState
        )
        TopBar(
            modifier = Modifier.fillMaxWidth(),
            title = movieModel.title,
            aspectRatio = currentAspectRatio,
            maxAspectRatio = maxImageRatio,
            canShare = canShare,
            onBack = onBackCallBack,
            shareShow = {
                viewModel.viewEventState.value = ShareViewEvent(movieModel)
            }
        )
    }
}

@ExperimentalCoilApi
@Composable
fun Body(
    modifier: Modifier = Modifier,
    movieModel: MovieModel,
    recommendation: List<ShowModel>,
    similar: List<ShowModel>,
    userReviews: List<ReviewModel>,
    scrollState: ScrollState,
    onShowSelected: (ShowModel) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(state = scrollState)
    ) {
        Spacer(
            modifier = Modifier.aspectRatio(minImageAspectRatio)
        )
        if (movieModel.tagline.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = movieModel.tagline,
                style = MaterialTheme.typography.overline.copy(
                    color = MaterialTheme.colors.onSecondary
                )
            )
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = getGenreList(movieModel.genres),
            style = MaterialTheme.typography.caption.copy(
                fontSize = 11.sp,
                color = MaterialTheme.colors.secondary
            )
        )

        ShowInfoComponent(
            voteAvg = movieModel.voteAvg,
            voteCount = movieModel.voteCount,
            releaseDate = movieModel.releaseDate,
            runtime = movieModel.runtime
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
                text = movieModel.popularity.toString(),
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 10.sp
                )
            )
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp),
            text = movieModel.overview,
            style = MaterialTheme.typography.caption.copy(MaterialTheme.colors.onSecondary)
        )

        if (movieModel.casts.isNotEmpty()) {
            CastComponent(casts = movieModel.casts)
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
