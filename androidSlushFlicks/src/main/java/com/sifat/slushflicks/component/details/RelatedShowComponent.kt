package com.sifat.slushflicks.component.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.component.RatingComponent
import com.sifat.slushflicks.component.verticalGradientTint
import com.sifat.slushflicks.domain.model.ShowModel

@ExperimentalCoilApi
@Composable
fun RelatedShowComponent(
    title: String,
    showModels: List<ShowModel>,
    onShowSelected: (ShowModel) -> Unit = {}
) {
    if (!showModels.isNullOrEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 12.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = title,
                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onPrimary)
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(showModels) {
                    RelatedShowItem(
                        showModel = it,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        onShowSelected = onShowSelected
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun RelatedShowItem(
    showModel: ShowModel,
    modifier: Modifier = Modifier,
    onShowSelected: (ShowModel) -> Unit = {}
) {
    val gradientColors = listOf(
        Color.Transparent,
        MaterialTheme.colors.primary.copy(alpha = 0.25f),
        MaterialTheme.colors.primary
    )
    Card(
        modifier = modifier.clickable {
            onShowSelected(showModel)
        },
        elevation = 6.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Box(
            modifier = Modifier
                .width(256.dp)
                .aspectRatio(1.5f)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalGradientTint(gradientColors),
                painter = rememberImagePainter(
                    data = showModel.backdropPath,
                    builder = {
                        crossfade(false)
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.placeholder)
                    }
                ),
                contentDescription = showModel.title,
                contentScale = ContentScale.Crop
            )

            RatingComponent(
                voteAvg = showModel.voteAvg,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 12.dp)
            )

            Text(
                text = showModel.title,
                style = MaterialTheme.typography.subtitle2.copy(color = MaterialTheme.colors.onPrimary),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}
