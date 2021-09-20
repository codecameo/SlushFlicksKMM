package com.sifat.slushflicks.component.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sifat.slushflicks.R
import com.sifat.slushflicks.component.SlushFlicksTheme
import com.sifat.common.data.Constants.EMPTY_STRING

@Composable
fun SearchBarComponent(
    modifier: Modifier = Modifier,
    query: String = EMPTY_STRING,
    onChangeQuery: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 8.dp)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = MaterialTheme.colors.onPrimary
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(6f)
        ) {
            if (query.isEmpty()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = stringResource(id = R.string.hint_search),
                    style = MaterialTheme.typography.caption.copy(MaterialTheme.colors.onSecondary)
                )
            }
            BasicTextField(
                value = query,
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colors.onPrimary),
                onValueChange = onChangeQuery,
                textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onPrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .wrapContentHeight(),
            )
        }
        if (query.isNotEmpty()) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .clickable {
                        onChangeQuery(EMPTY_STRING)
                    },
                imageVector = Icons.Filled.Clear,
                contentDescription = "Clear",
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SlushFlicksTheme {
        SearchBarComponent(query = "Search") {
        }
    }
}
