package com.hobbyloop.feature.reservation.center_detail.component.lazylist

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import com.hobbyloop.feature.reservation.center_detail.monthly_calendar.model.DateInfo

object LazyListUtil {

    /**
     * 스크롤 중 뷰포트의 중앙 위치를 계산합니다.
     * 계산은 뷰포트의 너비의 절반에 현재 스크롤 위치를 더하여 수행됩니다.
     *
     * @param listState 현재 LazyListState 객체로, 리스트의 상태 정보를 제공합니다.
     * @return 계산된 뷰포트 중앙의 위치 값(Float)을 반환합니다.
     */
    fun calculateViewportCenter(listState: LazyListState): Float {
        return listState.layoutInfo.viewportSize.width / 2.toFloat() + listState.firstVisibleItemScrollOffset
    }

    /**
     * 뷰포트 중앙에서 가장 가까운 아이템을 찾습니다.
     * 이 함수는 주어진 뷰포트 중앙값을 기준으로 모든 아이템의 중앙 위치와의 차이를 계산하고,
     * 가장 작은 차이를 가진 아이템을 반환합니다.
     *
     * @param listState 현재 LazyListState 객체로, 리스트의 상태 정보를 제공합니다.
     * @param viewportCenter 계산된 뷰포트 중앙의 위치 값입니다.
     * @return 가장 중앙에 가까운 아이템의 정보를 포함하는 LazyListItemInfo 객체를 반환합니다.
     *         해당 아이템이 없을 경우 null을 반환합니다.
     */
    fun findClosestCenterItem(listState: LazyListState, viewportCenter: Float): LazyListItemInfo? {
        return listState.layoutInfo.visibleItemsInfo.minByOrNull { itemInfo ->
            Math.abs((itemInfo.offset + itemInfo.size / 2) - viewportCenter)
        }
    }

    /**
     * 화면에 완전히 보이는 아이템들을 필터링하는 함수입니다.
     * 이 함수는 각 아이템의 시작 위치가 뷰포트의 시작 이후이고, 끝 위치가 뷰포트의 끝 이전인지를 확인하여,
     * 이 조건을 만족하는 아이템들만을 리스트로 반환합니다.
     *
     * @param listState 현재 LazyListState 객체로, 리스트의 상태 정보를 제공합니다.
     * @return 완전히 보이는 아이템들을 포함하는 리스트를 반환합니다.
     */
    fun filterFullyVisibleItems(listState: LazyListState): List<LazyListItemInfo> {
        return listState.layoutInfo.visibleItemsInfo.filter { itemInfo ->
            itemInfo.offset >= listState.layoutInfo.viewportStartOffset &&
                    (itemInfo.offset + itemInfo.size) <= listState.layoutInfo.viewportEndOffset
        }
    }

    /**
     * 계산을 통해 스크롤 완료 후 조정이 필요한 오프셋을 반환합니다.
     *
     * @param listState LazyListState 현재 리스트 상태를 제공합니다.
     * @param dataList 데이터 리스트를 제공하며, 리스트 아이템의 인덱스를 확인하는데 사용됩니다.
     * @param fullyVisibleItems 현재 화면에 완전히 보이는 아이템들의 리스트입니다.
     * @return 계산된 스크롤 조정 오프셋을 반환합니다. 조정이 필요 없는 경우 0f를 반환합니다.
     */
    fun calculateAdjustmentAfterCompletion(
        listState: LazyListState,
        dataList: List<DateInfo>,
        fullyVisibleItems: List<LazyListItemInfo>
    ): Number {
        // 뷰포트 중앙 위치 계산
        val layoutInfo = listState.layoutInfo
        val viewportCenter = layoutInfo.viewportEndOffset / 2  // 화면 중앙 계산

        // 뷰포트 중앙에서 가장 가까운 아이템을 찾습니다.
        val closestCenterItem = layoutInfo.visibleItemsInfo.minByOrNull { itemInfo ->
            kotlin.math.abs((itemInfo.offset + itemInfo.size / 2) - viewportCenter).toFloat()
        }

        // 가장 가까운 아이템을 기준으로 스크롤 오프셋을 계산합니다.
        return closestCenterItem?.let { closest ->
            // 현재 보이는 아이템들 중 가장 첫 번째와 마지막 아이템의 인덱스를 확인합니다.
            val firstFullyVisibleItemIndex = fullyVisibleItems.first().index
            val lastFullyVisibleItemIndex = fullyVisibleItems.last().index

            // 첫 번째 아이템이 완전히 보이고 추가 아이템이 있을 경우 다음 아이템으로 스크롤 조정
            when {
                firstFullyVisibleItemIndex == 0 && fullyVisibleItems.size > 1 -> {
                    val nextIndex = closest.index + 1
                    layoutInfo.visibleItemsInfo.find { it.index == nextIndex }?.let {
                        // 다음 아이템의 중앙으로 스크롤 오프셋 조정
                        (it.offset + it.size / 2) - viewportCenter
                    } ?: 0f
                }
                // 마지막 아이템이 완전히 보이고 추가 아이템이 있을 경우 이전 아이템으로 스크롤 조정
                lastFullyVisibleItemIndex == dataList.lastIndex && fullyVisibleItems.size > 1 -> {
                    val prevIndex = closest.index - 1
                    layoutInfo.visibleItemsInfo.find { it.index == prevIndex }?.let {
                        // 이전 아이템의 중앙으로 스크롤 오프셋 조정
                        (it.offset + it.size / 2) - viewportCenter
                    } ?: 0f
                }
                // 그 외의 경우 가장 가까운 아이템의 중앙으로 스크롤 오프셋 조정
                else -> {
                    (closest.offset + closest.size / 2) - viewportCenter
                }
            }
        } ?: 0f // 아이템을 찾지 못한 경우 0f 반환
    }
}
