package com.hobbyloop.data.di

import com.hobbyloop.data.repository.fake.FakeCenterRepository
import com.hobbyloop.domain.repository.CenterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCenterRepository(centerRepository: FakeCenterRepository): CenterRepository

}

