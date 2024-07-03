package com.hobbyloop.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import datasource.ad.AdDataSource
import datasource.ad.MockAdDataSourceImpl
import datasource.login.LoginDataSource
import datasource.login.LoginDataSourceImpl
import datasource.signup.SignUpDataSource
import datasource.signup.SignUpDataSourceImpl
import datasource.ticket.MockTicketDataSourceImpl
import datasource.ticket.TicketDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceBindsModule {

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
    abstract fun bindLoginDataSource(
        impl: LoginDataSourceImpl
    ): LoginDataSource


    @Singleton
    @Binds
    abstract fun bindSignUpDataSource(
        impl: SignUpDataSourceImpl
    ): SignUpDataSource


}