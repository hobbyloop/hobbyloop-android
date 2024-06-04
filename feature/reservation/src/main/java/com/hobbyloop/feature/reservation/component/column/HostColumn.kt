package com.hobbyloop.feature.reservation.component.column

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity

@Composable
fun HostColumn(
    scrollType: ScrollType,
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val hostScrollState = rememberScrollState()

    val nested = object : NestedScrollConnection {
        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
            return super.onPostFling(consumed, available)
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource,
        ): Offset {
            return super.onPostScroll(consumed, available, source)
        }

        override suspend fun onPreFling(available: Velocity): Velocity {
            return super.onPreFling(available)
        }

        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            return super.onPreScroll(available, source)
        }
    }

    val scrollState = when(scrollType) {
        ScrollType.VERTICAL -> Modifier.verticalScroll(hostScrollState)
        ScrollType.HORIZONTAL -> Modifier.horizontalScroll(hostScrollState)
    }

    Column(
        modifier = modifier
            .nestedScroll(nested)
            .then(scrollState)
    ) {
        content()
    }
}

enum class ScrollType {
    VERTICAL(),
    HORIZONTAL()
}
