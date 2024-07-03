package com.hobbyloop.domain.usecase.calendar

import com.hobbyloop.domain.entity.calendar.DateInfo
import com.hobbyloop.domain.repository.calendar.CalendarRepository
import javax.inject.Inject

class GetCurrentMonthUseCase @Inject constructor(
    private val repository: CalendarRepository
) {
    operator fun invoke(): List<DateInfo> {
        return repository.getCurrentMonth()
    }
}
