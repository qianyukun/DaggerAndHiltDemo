package com.qyk.hiltdemo.login
sealed class LoginAction {
    object DoLogoutAction : LoginAction()
    data class DoLoginAction(var username: String, var password: String) : LoginAction()
}
