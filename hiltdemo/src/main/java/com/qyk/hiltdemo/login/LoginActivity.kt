package com.qyk.hiltdemo.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.qyk.hiltdemo.R
import com.qyk.hiltdemo.analytics.AnalyticManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var analyticManager: AnalyticManager

    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        analyticManager.trackEvent("login open")
        lifecycleScope.launch {
            loginViewModel.state.collect { state ->
                android.util.Log.e("xxxx", "observe state $state")
                when (state.loginStatus) {
                    is LoginStatus.Success -> {
                        progress.visibility = View.GONE
                        text.text = "Login success"
                    }
                    is LoginStatus.Failed -> {
                        progress.visibility = View.GONE
                        text.text = "Login failed"
                    }
                    is LoginStatus.Logout -> {
                        progress.visibility = View.GONE
                        text.text = "Logout"
                    }
                    is LoginStatus.Idle -> {
                        progress.visibility = View.GONE
                        text.text = "Login Idle"
                    }
                    is LoginStatus.Loading -> {
                        text.text = "Loading"
                        progress.visibility = View.VISIBLE
                    }
                }
            }
        }
        text.setOnClickListener {
            lifecycleScope.launch {
                loginViewModel.action.send(LoginAction.DoLoginAction("11111", "122222"))
                delay(10000)
                loginViewModel.action.send(LoginAction.DoLogoutAction)
            }
        }
        go_home.setOnClickListener{

        }
    }
}