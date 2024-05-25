package com.hobbyloop.domain.usecase

import androidx.paging.PagingData
import com.hobbyloop.domain.model.Center
import com.hobbyloop.domain.repository.CenterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCentersUseCase @Inject constructor(
    private val centerRepository: CenterRepository
) {
    operator fun invoke(): Flow<PagingData<Center>> {
        return centerRepository.getCenters()
    }
}