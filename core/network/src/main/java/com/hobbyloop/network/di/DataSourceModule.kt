package com.hobbyloop.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import datasource.ad.AdDataSource
import datasource.ad.MockAdDataSourceImpl
import datasource.point.MockPointDataSourceImpl
import datasource.point.PointDataSource
import datasource.ticket.MockTicketDataSourceImpl
import datasource.ticket.TicketDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindAdDataSource(
        impl: MockAdDataSourceImpl
    ): AdDataSource

    @Singleton
    @Binds
    abstract fun bindTicketDataSource(
        impl: MockTicketDataSourceImpl
    ): TicketDataSource

    @Singleton
    @Binds
    abstract fun bindPointDataSource(
        impl: MockPointDataSourceImpl
    ): PointDataSource
}