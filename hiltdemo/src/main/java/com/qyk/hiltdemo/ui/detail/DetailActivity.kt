package com.qyk.hiltdemo.ui.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.qyk.hiltdemo.counter.ICounterRepository
import com.qyk.hiltdemo.counter.SharedFlowCounterRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 简单测试SharedFlow,没有按照架构的要求进行书写
 */
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    @Inject
    @SharedFlowCounterRepository
    lateinit var counterRepository: ICounterRepository

    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textView = TextView(this)
        setContentView(textView)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                println("aaaaaaaaa")
                counterRepository.countFlow.collect {
                    println(it)
                    textView.text = it.toString()
                }
                println("bbbbbb")
            }
        }
    }
}