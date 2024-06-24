package com.hobbyloop.domain.usecase.calendar

import com.hobbyloop.domain.entity.calendar.CalendarYear
import com.hobbyloop.domain.repository.calendar.CalendarRepository
import javax.inject.Inject

class GetYearsUseCase @Inject constructor(
    private val repository: CalendarRepository
) {
    operator fun invoke(): CalendarYear {
        return repository.getYears()
    }
}
