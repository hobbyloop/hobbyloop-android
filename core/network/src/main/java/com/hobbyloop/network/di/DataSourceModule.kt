package com.hobbyloop.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import datasource.ad.AdDataSource
import datasource.ad.MockAdDataSourceImpl
import datasource.coupon.CouponDataSource
import datasource.coupon.MockCouponDataSourceImpl
import datasource.login.LoginDataSource
import datasource.login.LoginDataSourceImpl
import datasource.point.MockPointDataSourceImpl
import datasource.point.PointDataSource
import datasource.signup.SignUpDataSource
import datasource.signup.SignUpDataSourceImpl
import datasource.ticket.MockTicketDataSourceImpl
import datasource.ticket.TicketDataSource
import datasource.user.UserInfoDataSource
import datasource.user.UserInfoDataSourceImpl
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

    @Singleton
    @Binds
    abstract fun bindUserInfoDataSource(
        impl: UserInfoDataSourceImpl
    ): UserInfoDataSource

    @Singleton
    @Binds
    abstract fun bindCouponDataSource(
        impl: MockCouponDataSourceImpl
    ): CouponDataSource

}