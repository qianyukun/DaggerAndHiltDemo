package com.qyk.hiltdemo.counter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class StateCounterRepositoryImpl @Inject constructor() : ICounterRepository {
    val fl = MutableStateFlow(1)

    override val countFlow: Flow<Int>
        get() = fl

    override fun add() {
        fl.value++
    }
}