package com.sifat.slushflicks.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.domain.model.ShowModel
import com.sifat.slushflicks.theme.Black10
import com.sifat.slushflicks.theme.Black85
import com.sifat.slushflicks.theme.Transparent

@ExperimentalCoilApi
@Composable
fun ShowListItem(modifier: Modifier = Modifier, showModel: ShowModel) {
    val colors = listOf(Transparent, Black10, Black85)
    val genreList = getGenreList(showModel.genres)

    Card(
        modifier = modifier
            .wrapContentSize()
            .background(MaterialTheme.colors.primary)
            .padding(8.dp)
            .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.medium, clip = true)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            constraintSet = showItemConstraints()
        ) {
            Image(
                modifier = Modifier
                    .layoutId("poster")
                    .aspectRatio(2f)
                    .verticalGradientTint(colors = colors, blendMode = BlendMode.Darken)
                    .background(MaterialTheme.colors.primaryVariant),
                painter = rememberImagePainter(
                    data = showModel.backdropPath,
                    builder = {
                        crossfade(false)
                        placeholder(R.drawable.placeholder)
                    }
                ),
                contentDescription = showModel.title,
                contentScale = ContentScale.Crop
            )
            Text(
                text = genreList,
                style = MaterialTheme.typography.caption.copy(
                    color = MaterialTheme.colors.onSecondary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .layoutId("genre")
            )
            RatingComponent(
                voteAvg = showModel.voteAvg,
                modifier = Modifier.layoutId("rating")
            )
            Text(
                text = showModel.title,
                modifier = Modifier
                    .layoutId("title")
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = showModel.overview,
                modifier = Modifier
                    .layoutId("overview")
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                style = MaterialTheme.typography.caption,
                maxLines = 3
            )
        }
    }
}

private fun showItemConstraints(): ConstraintSet {
    return ConstraintSet {
        val poster = createRefFor("poster")
        val rating = createRefFor("rating")
        val title = createRefFor("title")
        val overview = createRefFor("overview")
        val genre = createRefFor("genre")

        constrain(poster) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(title) {
            top.linkTo(poster.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
        }
        constrain(overview) {
            top.linkTo(title.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            bottom.linkTo(parent.bottom, margin = 16.dp)
        }
        constrain(rating) {
            end.linkTo(parent.end)
            bottom.linkTo(poster.bottom, margin = 12.dp)
        }
        constrain(genre) {
            linkTo(start = parent.start, end = rating.start, bias = 0f)
            bottom.linkTo(rating.bottom)
        }
    }
}
