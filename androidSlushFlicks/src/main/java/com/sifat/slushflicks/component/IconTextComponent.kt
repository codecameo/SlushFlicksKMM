package com.sifat.slushflicks.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconTextComponent(modifier: Modifier = Modifier, @DrawableRes icon: Int, text: String) {
    Row(modifier = modifier) {
        Icon(
            modifier = Modifier.size(12.dp).align(Alignment.CenterVertically),
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = MaterialTheme.colors.secondary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = text,
            style = MaterialTheme.typography.caption.copy(MaterialTheme.colors.onSecondary)
        )
    }
}
