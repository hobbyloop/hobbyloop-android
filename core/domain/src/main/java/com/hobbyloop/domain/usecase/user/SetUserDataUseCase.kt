package com.hobbyloop.domain.usecase.user

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ticket.HotTicket
import com.hobbyloop.domain.repository.ticket.TicketRepository
import com.hobbyloop.domain.repository.user.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetUserDataUseCase @Inject constructor(
    private val repository: UserDataRepository
) {
    suspend fun setJwt(jwt: String) {
        repository.setJwt(jwt)
    }
}
