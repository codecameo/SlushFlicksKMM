package com.sifat.slushflicks.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.sifat.slushflicks.R
import com.sifat.slushflicks.domain.model.ShowModel

@ExperimentalCoilApi
@Composable
fun ShowListItem(showModel: ShowModel) {
    Card(
        modifier = Modifier
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
                    .background(MaterialTheme.colors.primaryVariant),
                painter = rememberImagePainter(
                    data = "https://image.tmdb.org/t/p/w342/${showModel.backdropPath}",
                    builder = {
                        crossfade(false)
                        placeholder(R.drawable.placeholder)
                    }
                ),
                contentDescription = showModel.title,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .layoutId("rating")
                    .shadow(elevation = 6.dp, clip = false)
                    .background(
                        color = MaterialTheme.colors.onPrimary,
                        shape = RoundedCornerShape(
                            topStart = CornerSize(10.dp),
                            bottomStart = CornerSize(10.dp),
                            bottomEnd = CornerSize(0.dp),
                            topEnd = CornerSize(0.dp)
                        )
                    )
                    .padding(horizontal = 6.dp, vertical = 2.dp)

            ) {
                Icon(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_star_orange),
                    contentDescription = "Rating${showModel.voteAvg}",
                    tint = MaterialTheme.colors.secondary
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = showModel.voteAvg.toString(),
                    style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.primary)
                )
            }
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
        val overlay = createRefFor("overlay")

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
        constrain(overlay) {
            top.linkTo(poster.top)
            bottom.linkTo(poster.bottom)
            start.linkTo(poster.start)
            end.linkTo(poster.end)
        }
    }
}
