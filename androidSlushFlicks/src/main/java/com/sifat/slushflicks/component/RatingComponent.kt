package com.sifat.slushflicks.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sifat.slushflicks.R

@Composable
fun RatingComponent(modifier: Modifier = Modifier, voteAvg: Double) {
    Row(
        modifier = modifier
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
            contentDescription = "Rating $voteAvg",
            tint = MaterialTheme.colors.secondary
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = voteAvg.toString(),
            style = MaterialTheme.typography.overline.copy(color = MaterialTheme.colors.primary)
        )
    }
}
