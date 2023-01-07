package com.fa.cwtdailyapp.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DailyModule {

    @Provides
    @Singleton
    fun getDailyDataSource() = DailyDataSource()

    @Provides
    @Singleton
    fun getDailyRepository(
        dataSource: DailyDataSource
    ) = DailyRepository(dataSource)

}
