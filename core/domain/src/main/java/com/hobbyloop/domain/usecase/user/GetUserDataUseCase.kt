package com.hobbyloop.domain.usecase.user

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ticket.HotTicket
import com.hobbyloop.domain.entity.user.UserData
import com.hobbyloop.domain.repository.ticket.TicketRepository
import com.hobbyloop.domain.repository.user.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    operator fun invoke(): Flow<UserData> {
        return repository.userData
    }
}