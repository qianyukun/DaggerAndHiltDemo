package com.qyk.hiltdemo.counter

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CounterRepositoryModule {

    @Binds
    @Singleton
    @StateFlowCounterRepository
    abstract fun bindStateCounterRepository(stateCounterRepositoryImpl: StateCounterRepositoryImpl): ICounterRepository

    @Binds
    @Singleton
    @SharedFlowCounterRepository
    abstract fun bindSharedCounterRepository(sharedCounterRepositoryImpl: StateCounterRepositoryImpl): ICounterRepository
}