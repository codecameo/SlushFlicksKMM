package com.sifat.slushflicks.component.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsPadding
import com.sifat.slushflicks.R
import com.sifat.slushflicks.component.CollapsingTopBar
import com.sifat.slushflicks.utils.ext.inRange

const val minImageAspectRatio = 0.95f

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    aspectRatio: Float,
    maxAspectRatio: Float,
    canShare: Boolean,
    onBack: () -> Unit,
    shareShow: () -> Unit
) {

    Box(
        modifier = modifier.aspectRatio(aspectRatio)
    ) {
        CollapsingTopBar(
            collapseFactor = aspectRatio.inRange(maxAspectRatio, minImageAspectRatio),
            modifier = Modifier
                .statusBarsPadding()
        ) {
            Icon(
                modifier = Modifier
                    .wrapContentWidth()
                    .layoutId(CollapsingTopBar.BACK_ID)
                    .clickable { onBack() }
                    .padding(16.dp),
                imageVector = Icons.Filled.ArrowBack,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = stringResource(id = R.string.text_back)
            )
            Icon(
                modifier = Modifier
                    .wrapContentSize()
                    .layoutId(CollapsingTopBar.SHARE_ID)
                    .clickable { if (canShare) shareShow() }
                    .padding(16.dp),
                imageVector = Icons.Filled.Share,
                tint = MaterialTheme.colors.onPrimary.copy(alpha = if (canShare) 1f else 0.5f),
                contentDescription = stringResource(id = R.string.title_share)
            )
            Text(
                modifier = Modifier
                    .layoutId(CollapsingTopBar.TITLE_ID)
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                text = title,
                style = MaterialTheme.typography.h4.copy(
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = (getFontSize(aspectRatio, maxAspectRatio)).sp
                ),
                maxLines = if (aspectRatio > 3f) 1 else 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

fun getFontSize(aspectRatio: Float, maxAspectRatio: Float): Float {
    return 22f + (4f - (4f * aspectRatio.inRange(maxAspectRatio, minImageAspectRatio)))
}
