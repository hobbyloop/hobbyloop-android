package com.hobbyloop.domain.repository.ad

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ad.Ad
import kotlinx.coroutines.flow.Flow

interface AdRepository {
    fun getAds(): Flow<Resource<List<Ad>>>
}