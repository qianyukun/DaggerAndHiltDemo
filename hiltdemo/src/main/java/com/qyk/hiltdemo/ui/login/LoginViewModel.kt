package com.qyk.hiltdemo.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var loginRepository: LoginRepository

    val action = Channel<LoginAction>(Channel.UNLIMITED)

    private val _state =
        MutableStateFlow(LoginState(loginStatus = LoginStatus.Idle))
    val state: StateFlow<LoginState>
        get() {
            return _state
        }

    init {
        handleActions()
    }

    //监听来自View的action
    private fun handleActions() {
        viewModelScope.launch {
            action.consumeAsFlow().collect {
                dispatchAction(loginAction = it)
            }
        }
    }

    private fun dispatchAction(loginAction: LoginAction) {
        when (loginAction) {
            is LoginAction.DoLoginAction -> {
                login(loginAction.username, loginAction.password)
            }
            is LoginAction.DoLogoutAction -> {
                logout()
            }
        }
    }

    //发布状态
    private fun emit(state: LoginState) {
        _state.value = state
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            emit(_state.value.copy(loginStatus = LoginStatus.Loading))
            val loginResult = loginRepository.login(username, password)
            if (loginResult) {
                emit(_state.value.copy(loginStatus = LoginStatus.Success))
            } else {
                emit(_state.value.copy(loginStatus = LoginStatus.Failed))
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            emit(_state.value.copy(loginStatus = LoginStatus.Loading))
            val loginResult = loginRepository.logout()
            if (loginResult) {
                emit(_state.value.copy(loginStatus = LoginStatus.Logout))
            }
        }
    }
}

data class LoginState(var loginStatus: LoginStatus)

sealed class LoginStatus {
    object Idle : LoginStatus()
    object Success : LoginStatus()
    object Loading : LoginStatus()
    object Failed : LoginStatus()
    object Logout : LoginStatus()
}
