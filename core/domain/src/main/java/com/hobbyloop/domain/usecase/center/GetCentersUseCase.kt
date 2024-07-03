package com.hobbyloop.domain.usecase.center

import com.hobbyloop.domain.repository.center.CenterRepository
import javax.inject.Inject

class GetCentersUseCase @Inject constructor(
    private val repository: CenterRepository
) {
}