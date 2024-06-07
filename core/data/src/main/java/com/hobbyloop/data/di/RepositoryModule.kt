package com.hobbyloop.data.di

import com.hobbyloop.data.repository.remote.ad.MockAdRepositoryImpl
import com.hobbyloop.data.repository.remote.center.MockCenterRepositoryImpl
import com.hobbyloop.data.repository.remote.ticket.MockTicketRepositoryImpl
import com.hobbyloop.data.repository.remote.user.MockUserRepositoryImpl
import com.hobbyloop.domain.repository.ad.AdRepository
import com.hobbyloop.domain.repository.center.CenterRepository
import com.hobbyloop.domain.repository.ticket.TicketRepository
import com.hobbyloop.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindAdRepository(
        impl: MockAdRepositoryImpl
    ): AdRepository

    @Singleton
    @Binds
    abstract fun bindCenterRepository(
        impl: MockCenterRepositoryImpl
    ): CenterRepository

    @Singleton
    @Binds
    abstract fun bindTicketRepository(
        impl: MockTicketRepositoryImpl
    ): TicketRepository

    @Singleton
    @Binds
    abstract fun bindUserRepository(
        impl: MockUserRepositoryImpl
    ): UserRepository
}