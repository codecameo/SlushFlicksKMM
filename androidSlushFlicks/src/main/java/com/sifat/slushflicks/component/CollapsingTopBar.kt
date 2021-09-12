package com.sifat.slushflicks.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.Constraints
import com.sifat.slushflicks.component.CollapsingTopBar.BACK_ID
import com.sifat.slushflicks.component.CollapsingTopBar.SHARE_ID
import com.sifat.slushflicks.component.CollapsingTopBar.TITLE_ID
import kotlin.math.roundToInt

@Composable
fun CollapsingTopBar(
    modifier: Modifier = Modifier,
    collapseFactor: Float = 1f,
    content: @Composable () -> Unit
) {
    val map = mutableMapOf<Placeable, Int>()
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        map.clear()
        val placeables = mutableListOf<Placeable>()
        measurables.map { measurable ->
            when (measurable.layoutId) {
                BACK_ID -> measurable.measure(constraints)
                SHARE_ID -> measurable.measure(constraints)
                TITLE_ID -> measurable.measure(Constraints.fixedWidth(constraints.maxWidth - (collapseFactor * (placeables.first().width * 2)).toInt()))
                else -> throw IllegalStateException("Id Not found")
            }.also { placeable ->
                map[placeable] = measurable.layoutId as Int
                placeables.add(placeable)
            }
        }

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                when (map[placeable]) {
                    BACK_ID -> placeable.placeRelative(0, 0)
                    SHARE_ID -> placeable.run {
                        placeRelative(constraints.maxWidth - width, 0)
                    }
                    TITLE_ID -> placeable.run {
                        val widthOffset = (placeables[0].width * collapseFactor).roundToInt()
                        val heightOffset = (placeables.first().height - placeable.height) / 2
                        placeRelative(
                            widthOffset,
                            if (collapseFactor == 1f) heightOffset else constraints.maxHeight - height
                        )
                    }
                }
            }
        }
    }
}

object CollapsingTopBar {
    const val BACK_ID = 1001
    const val SHARE_ID = 1002
    const val TITLE_ID = 1003
    const val COLLAPSE_FACTOR = 0.6f
}
