package com.sifat.slushflicks.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sifat.slushflicks.component.home.model.CollectionListModel

@Composable
fun ShowTypeChip(collectionModel: CollectionListModel, onTypeSelected: (String) -> Unit = {}) {

    val callback by rememberUpdatedState(newValue = onTypeSelected)

    Text(
        text = collectionModel.name,
        style = MaterialTheme.typography.caption.copy(
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.onPrimary
        ),
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .background(
                shape = MaterialTheme.shapes.large,
                color = if (collectionModel.selected) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
            )
            .toggleable(
                value = collectionModel.selected,
                enabled = !collectionModel.selected
            ) {
                callback(collectionModel.label)
            }
            .run {
                if (!collectionModel.selected) {
                    this.border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onPrimary,
                        shape = MaterialTheme.shapes.large
                    )
                } else this
            }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Preview
@Composable
fun ChipSelected() {
    SlushFlicksTheme {
        // ShowTypeChip(selected = true, label = "Movie")
    }
}

@Preview
@Composable
fun ChipUnselected() {
    SlushFlicksTheme {
        // ShowTypeChip(selected = false, label = "Movie")
    }
}
