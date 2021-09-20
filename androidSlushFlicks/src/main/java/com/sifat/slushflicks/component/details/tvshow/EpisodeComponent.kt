package com.sifat.slushflicks.component.tvshow

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.component.IconTextComponent
import com.sifat.slushflicks.component.RatingComponent
import com.sifat.slushflicks.component.formatReleaseDate
import com.sifat.common.data.NA
import com.sifat.common.domain.model.EpisodeModel

@ExperimentalCoilApi
@Composable
fun EpisodeComponent(modifier: Modifier = Modifier, title: String, episodeModel: EpisodeModel) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(Modifier.padding(horizontal = 16.dp)) {
            Box(
                modifier = Modifier.background(
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colors.primary
                )
            ) {
                Surface(shape = MaterialTheme.shapes.medium) {
                    Image(
                        modifier = Modifier
                            .width(112.dp)
                            .aspectRatio(2f),
                        painter = rememberImagePainter(
                            data = episodeModel.stillPath,
                            builder = {
                                crossfade(false)
                                placeholder(R.drawable.placeholder)
                                error(R.drawable.placeholder)
                            }
                        ),
                        contentDescription = episodeModel.name,
                        contentScale = ContentScale.Crop
                    )
                }
                RatingComponent(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 8.dp),
                    voteAvg = episodeModel.voteAvg
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
                    text = if (episodeModel.name.isEmpty()) NA else episodeModel.name,
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
                        text = formatReleaseDate(episodeModel.airDate)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    IconTextComponent(
                        icon = R.drawable.ic_movie,
                        text = getEpisodeNumber(LocalContext.current, episodeModel.episodeNumber)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = episodeModel.overview,
            style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onSecondary)
        )
    }
}

fun getEpisodeNumber(context: Context, episodeNumber: Int): String {
    return when (episodeNumber % 10) {
        1 -> String.format(context.getString(R.string.text_st), episodeNumber)
        2 -> String.format(context.getString(R.string.text_nd), episodeNumber)
        else -> String.format(context.getString(R.string.text_th), episodeNumber)
    }
}
