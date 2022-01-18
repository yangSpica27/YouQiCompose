package me.spica.wuqi.tool

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.abs


@Composable
fun rememberPagerSnapState(): PagerSnapState {
    return remember {
        PagerSnapState()
    }
}


class PagerSnapState {

    val isSwiping = mutableStateOf(false)

    val firstVisibleItemIndex = mutableStateOf(0)

    val offsetInfo = mutableStateOf(0)

    internal fun updateScrollToItemPosition(itemPos: LazyListItemInfo?) {
        if (itemPos != null) {
            this.offsetInfo.value = itemPos.offset
            this.firstVisibleItemIndex.value = itemPos.index
        }
    }

    internal suspend fun scrollItemToSnapPosition(listState: LazyListState, position: Int) {
        listState.animateScrollToItem(position)
    }
}

/**
 * [PagerSnapNestedScrollConnection] reacts to the scroll left to right and vice-versa.
 */
class PagerSnapNestedScrollConnection(
    private val state: PagerSnapState,
    private val listState: LazyListState,
    private val scrollTo: () -> Unit
) : NestedScrollConnection {

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset = when (source) {
        NestedScrollSource.Drag -> onScroll()
        else -> Offset.Zero
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset = when (source) {
        NestedScrollSource.Drag -> onScroll()
        else -> Offset.Zero
    }

    private fun onScroll(): Offset {
        state.isSwiping.value = true
        return Offset.Zero
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity = when {
        state.isSwiping.value -> {

            state.updateScrollToItemPosition(listState.layoutInfo.visibleItemsInfo.firstOrNull())

            scrollTo()

            Velocity.Zero
        }
        else -> {
            Velocity.Zero
        }
    }.also {
        state.isSwiping.value = false
    }

}


@Composable
fun ComposePagerSnapHelper(
    width: Dp,
    modifier: Modifier,
    context: @Composable (LazyListState) -> Unit,
) {

    val state = rememberPagerSnapState()
    val listState = rememberLazyListState()

    val scope = rememberCoroutineScope()

    val widthPx = with(LocalDensity.current) {
        width.roundToPx()
    }

    val connection = remember(state, listState) {
        PagerSnapNestedScrollConnection(state, listState) {

            val firstItemIndex = state.firstVisibleItemIndex.value
            val firstItemOffset = abs(state.offsetInfo.value)

            val position = when {
                firstItemOffset <= widthPx.div(2) -> firstItemIndex
                else -> firstItemIndex.plus(1)
            }

            scope.launch {
                state.scrollItemToSnapPosition(listState, position)
            }

        }
    }

    Box(
        modifier = modifier
            .nestedScroll(connection)
            .padding(top = 80.dp),
    ) {
        context(listState)
    }
}