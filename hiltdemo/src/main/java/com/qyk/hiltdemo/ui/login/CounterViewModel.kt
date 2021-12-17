package com.qyk.hiltdemo.ui.login

import androidx.lifecycle.ViewModel
import com.qyk.hiltdemo.counter.ICounterRepository
import com.qyk.hiltdemo.counter.SharedFlowCounterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    /**
     * 也可以通过注解切换 StateFlow
     * @StateFlowCounterRepository
     */
    @SharedFlowCounterRepository
    private val counterRepository: ICounterRepository
) : ViewModel() {

    val countFlow: Flow<Int> = counterRepository.countFlow

    fun addCount() {
        counterRepository.add()
    }
}