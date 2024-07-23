package com.hobbyloop.data.di

import com.hobbyloop.data.repository.local.calendar.CalendarRepositoryImpl
import com.hobbyloop.data.repository.remote.ad.MockAdRepositoryImpl
import com.hobbyloop.data.repository.remote.center.MockCenterRepositoryImpl
import com.hobbyloop.data.repository.remote.coupon.MockCouponRepositoryImpl
import com.hobbyloop.data.repository.remote.login.LoginRepositoryImpl
import com.hobbyloop.data.repository.remote.point.MockPointRepositoryImpl
import com.hobbyloop.data.repository.remote.signup.SignUpRepositoryImpl
import com.hobbyloop.data.repository.remote.ticket.MockTicketRepositoryImpl
import com.hobbyloop.data.repository.remote.user.MockUserInfoRepositoryImpl
import com.hobbyloop.data.repository.remote.user.UserDataRepositoryImpl
import com.hobbyloop.domain.repository.ad.AdRepository
import com.hobbyloop.domain.repository.calendar.CalendarRepository
import com.hobbyloop.domain.repository.center.CenterRepository
import com.hobbyloop.domain.repository.coupon.CouponRepository
import com.hobbyloop.domain.repository.login.LoginRepository
import com.hobbyloop.domain.repository.point.PointRepository
import com.hobbyloop.domain.repository.signup.SignUpRepository
import com.hobbyloop.domain.repository.ticket.TicketRepository
import com.hobbyloop.domain.repository.user.UserDataRepository
import com.hobbyloop.domain.repository.user.UserInfoRepository
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
    abstract fun bindUserInfoRepository(
        impl: MockUserInfoRepositoryImpl
    ): UserInfoRepository

    @Singleton
    @Binds
    abstract fun bindPointRepository(
        impl: MockPointRepositoryImpl
    ): PointRepository

    @Singleton
    @Binds
    abstract fun bindsUserDataRepository(
        userDataRepository: UserDataRepositoryImpl,
    ): UserDataRepository

    @Singleton
    @Binds
    abstract fun bindsCalendarRepository(
        impl: CalendarRepositoryImpl
    ): CalendarRepository

    @Singleton
    @Binds
    abstract fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Singleton
    @Binds
    abstract fun bindSignUpRepository(
        impl: SignUpRepositoryImpl
    ): SignUpRepository


    @Singleton
    @Binds
    abstract fun bindsCouponRepository(
        impl: MockCouponRepositoryImpl
    ): CouponRepository

}
