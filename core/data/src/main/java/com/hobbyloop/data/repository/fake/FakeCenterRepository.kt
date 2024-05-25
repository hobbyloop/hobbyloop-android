package com.hobbyloop.data.repository.fake

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hobbyloop.domain.model.Center
import com.hobbyloop.domain.repository.CenterRepository
import com.hobbyloop.network.model.CenterDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FakeCenterRepository  @Inject constructor() : CenterRepository {
    override fun getCenters(): Flow<PagingData<Center>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FakePagingSource() }
        ).flow.map { pagingData ->
            pagingData.map(CenterDTO::asUiModel)
        }
    }
}
