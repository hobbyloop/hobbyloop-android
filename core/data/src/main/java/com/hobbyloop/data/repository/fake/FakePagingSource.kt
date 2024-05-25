package com.hobbyloop.data.repository.fake

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hobbyloop.network.model.CenterDTO

class FakePagingSource : PagingSource<Int, CenterDTO>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CenterDTO> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        Log.d("FakePagingSource", "Loading page: $page, pageSize: $pageSize")

        val centers = (1.. 100).map {
            CenterDTO(
                centerId = it.toLong(),
                centerName = "Center $it",
                address = "Address $it",
                logoImageUrl = "https://picsum.photos/100${it}/1002",
                price = it * 1400L,
                score = (it % 5).toDouble(),
                reviewCount = it * 3,
                refundable = it % 7 == 0,
                isBookMarked = it % 6 == 0,
                isRefundable = it % 7 == 0,
            )
        }.chunked(pageSize).getOrNull(page - 1) ?: emptyList()

        return LoadResult.Page(
            data = centers,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (centers.isEmpty()) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, CenterDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
