package com.hobbyloop.data.di.datasource

import com.hobbyloop.data.repository.local.calendar.DatesDataSource
import com.hobbyloop.data.repository.local.calendar.DatesLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsDatesDataSource(
        datesLocalDataSource: DatesLocalDataSource
    ): DatesDataSource
}
