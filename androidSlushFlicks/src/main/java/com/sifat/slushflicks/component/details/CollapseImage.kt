package com.sifat.slushflicks.component.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.component.showTrailer
import com.sifat.slushflicks.component.verticalGradientTint
import com.sifat.slushflicks.utils.ext.inRange
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@Composable
fun CollapseImage(
    title: String,
    posterPath: String,
    video: String,
    aspectRatio: Float,
    snackbarState: SnackbarHostState
) {
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
                data = posterPath,
                builder = {
                    crossfade(false)
                    placeholder(R.drawable.placeholder)
                    error(R.drawable.placeholder)
                }
            ),
            contentDescription = title,
            contentScale = ContentScale.Crop
        )

        if (video.isNotEmpty()) {
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
                    if (video.isNotEmpty()) {
                        if (!showTrailer(context = context, video)) {
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
