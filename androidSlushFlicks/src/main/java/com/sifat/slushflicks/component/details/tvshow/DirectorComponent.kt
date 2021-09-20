package com.sifat.slushflicks.component.details.tvshow

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp
import com.sifat.slushflicks.R
import com.sifat.common.data.SPACE

@Composable
fun DirectorComponent(modifier: Modifier = Modifier, directors: String) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(
                AnnotatedString(
                    text = stringResource(id = R.string.text_directed_by),
                    spanStyle = SpanStyle(color = MaterialTheme.colors.secondary, fontSize = 11.sp)
                )
            )
            append(text = SPACE)
            append(
                AnnotatedString(
                    text = directors,
                    spanStyle = SpanStyle(color = MaterialTheme.colors.onPrimary, fontSize = 12.sp)
                )
            )
        }
    )
}
