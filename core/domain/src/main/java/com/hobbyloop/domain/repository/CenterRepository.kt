package com.hobbyloop.domain.repository

import androidx.paging.PagingData
import com.hobbyloop.domain.model.Center
import kotlinx.coroutines.flow.Flow

interface CenterRepository {
    fun getCenters(): Flow<PagingData<Center>>
}
