package com.qyk.hiltdemo.counter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class SharedCounterRepositoryImpl @Inject constructor() : ICounterRepository {
    val fl = MutableSharedFlow<Int>()
    private var count = 1
    override val countFlow: Flow<Int>
        get() = fl

    override fun add() {
        fl.tryEmit(++count)
    }
}