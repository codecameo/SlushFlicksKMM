package com.sifat.slushflicks.component.details.tvshow

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.component.IconTextComponent
import com.sifat.slushflicks.component.formatReleaseDate
import com.sifat.slushflicks.component.verticalGradientTint
import com.sifat.common.domain.model.SeasonModel

@ExperimentalCoilApi
@Composable
fun SessionComponent(modifier: Modifier = Modifier, seasons: List<SeasonModel>) {
    val gradientColors = listOf(
        Color.Transparent,
        MaterialTheme.colors.primary.copy(alpha = 0.25f),
        MaterialTheme.colors.primary
    )
    Column(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.text_season),
            style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary)
        )
        Spacer(modifier = Modifier.height(12.dp))
        seasons.forEach { season ->
            SeasonItem(seasonModel = season, gradient = gradientColors)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun SeasonItem(modifier: Modifier = Modifier, seasonModel: SeasonModel, gradient: List<Color>) {
    Column(
        modifier = modifier
            .background(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.primary
            )
            .padding(vertical = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(Modifier.padding(horizontal = 16.dp)) {
            Surface(shape = CircleShape) {
                Image(
                    modifier = Modifier
                        .size(56.dp)
                        .verticalGradientTint(gradient),
                    painter = rememberImagePainter(
                        data = seasonModel.posterPath,
                        builder = {
                            crossfade(false)
                            placeholder(R.drawable.placeholder)
                            error(R.drawable.placeholder)
                        }
                    ),
                    contentDescription = seasonModel.name,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = seasonModel.name,
                    style = MaterialTheme.typography.subtitle2.copy(MaterialTheme.colors.onPrimary),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    IconTextComponent(
                        icon = R.drawable.ic_calender,
                        text = formatReleaseDate(seasonModel.airDate)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    IconTextComponent(
                        icon = R.drawable.ic_episode,
                        text = String.format(
                            stringResource(id = R.string.text_episode_count),
                            seasonModel.episodeCount
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = seasonModel.overview,
            style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onSecondary)
        )
    }
}
