package com.qyk.hiltdemo.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.qyk.hiltdemo.R
import com.qyk.hiltdemo.analytics.AnalyticManager
import com.qyk.hiltdemo.ext.clicks
import com.qyk.hiltdemo.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var analyticManager: AnalyticManager

    val loginViewModel: LoginViewModel by viewModels()

    val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        analyticManager.trackEvent("login open")

        //MVI
        lifecycleScope.launch {
            loginViewModel.state.collect { state ->
                android.util.Log.e("xxxx", "observe state $state")
                when (state.loginStatus) {
                    is LoginStatus.Success -> {
                        progress.visibility = View.GONE
                        status.text = "Login success"
                    }
                    is LoginStatus.Failed -> {
                        progress.visibility = View.GONE
                        status.text = "Login failed"
                    }
                    is LoginStatus.Logout -> {
                        progress.visibility = View.GONE
                        status.text = "Logout"
                    }
                    is LoginStatus.Idle -> {
                        progress.visibility = View.GONE
                        status.text = "Login Idle"
                    }
                    is LoginStatus.Loading -> {
                        status.text = "Loading"
                        progress.visibility = View.VISIBLE
                    }
                }
            }
        }

        login.setOnClickListener {
            lifecycleScope.launch {
                loginViewModel.action.send(LoginAction.DoLoginAction("11111", "122222"))
            }
        }

        logout.setOnClickListener {
            lifecycleScope.launch {
                loginViewModel.action.send(LoginAction.DoLogoutAction)
            }
        }

        //MVVM 不带dataBinding
        lifecycleScope.launch {
            counterViewModel
                .countFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    counter.text = "Count $it"
                }
        }

        //1s内不允许重复点击，过滤了误点
        timeCounter.clicks().debounce(1000).onEach {
            delay(2000)
            counterViewModel.addCount()
            println("clicked")
            startActivity(Intent(this@LoginActivity, DetailActivity::class.java))

        }.launchIn(lifecycleScope)
    }
}