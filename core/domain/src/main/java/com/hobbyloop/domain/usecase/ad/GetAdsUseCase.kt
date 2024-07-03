package com.hobbyloop.domain.usecase.ad

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ad.Ad
import com.hobbyloop.domain.repository.ad.AdRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAdsUseCase @Inject constructor(
    private val repository: AdRepository
) {
    operator fun invoke(): Flow<Resource<List<Ad>>> {
        return repository.getAds()
    }
}