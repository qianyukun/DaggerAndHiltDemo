package com.qyk.hiltdemo.counter

import kotlinx.coroutines.flow.Flow
import javax.inject.Qualifier

interface ICounterRepository {
    val countFlow: Flow<Int>

    fun add()
}

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class StateFlowCounterRepository


@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class SharedFlowCounterRepository