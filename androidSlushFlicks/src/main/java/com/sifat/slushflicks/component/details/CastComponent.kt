package com.sifat.slushflicks.component.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.component.SlushFlicksTheme
import com.sifat.slushflicks.component.verticalGradientTint
import com.sifat.common.domain.model.CastModel

@Composable
fun CastComponent(casts: List<CastModel>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 12.dp, bottom = 4.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.text_cast),
            style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onPrimary)
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(casts) {
                CastItem(castModel = it, modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CastItem(
    modifier: Modifier = Modifier,
    castModel: CastModel
) {
    val gradientColors = listOf(
        Color.Transparent,
        MaterialTheme.colors.primary.copy(alpha = 0.25f),
        MaterialTheme.colors.primary
    )
    Card(
        modifier = modifier,
        elevation = 6.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Box(
            modifier = Modifier
                .width(106.dp)
                .height(136.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalGradientTint(gradientColors),
                painter = rememberImagePainter(
                    data = castModel.profileImage,
                    builder = {
                        crossfade(false)
                        placeholder(R.drawable.ic_avater)
                        error(R.drawable.ic_avater)
                    }
                ),
                contentDescription = castModel.name,
                contentScale = ContentScale.Crop
            )

            Text(
                text = castModel.name,
                style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onPrimary),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(4.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Preview
@ExperimentalCoilApi
@Composable
fun CastItemPreview() {
    SlushFlicksTheme {
        CastItem(castModel = CastModel(1L, "Char", "Slush", 1, ""))
    }
}
