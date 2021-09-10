package com.sifat.slushflicks.component.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sifat.slushflicks.R
import com.sifat.slushflicks.component.DividerComponent
import com.sifat.slushflicks.component.IconTextComponent
import com.sifat.slushflicks.component.formatReleaseDate
import com.sifat.slushflicks.data.Constants

@Composable
fun ShowInfoComponent(
    modifier: Modifier = Modifier,
    voteAvg: Double,
    voteCount: Int,
    releaseDate: String,
    runtime: Int
) {
    Row(modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        IconTextComponent(
            icon = R.drawable.ic_star_orange,
            text = stringResource(
                id = R.string.text_rating, voteAvg, voteCount
            )
        )
        if (runtime > Constants.DEFAULT_INT) {
            DividerComponent(modifier = Modifier.align(Alignment.CenterVertically))
            IconTextComponent(
                icon = R.drawable.ic_time,
                text = stringResource(
                    id = R.string.text_runtime, runtime / 60, runtime % 60
                )
            )
        }
        if (releaseDate.isNotEmpty()) {
            DividerComponent(modifier = Modifier.align(Alignment.CenterVertically))
            IconTextComponent(
                icon = R.drawable.ic_movie,
                text = formatReleaseDate(releaseDate)
            )
        }
    }
}
