package com.qyk.hiltdemo.login

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor() {
    suspend fun login(username: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            delay(5000)
            android.util.Log.e("xxxx", "login success")
            true
        }
    }

    suspend fun logout(): Boolean {
        return withContext(Dispatchers.IO) {
            delay(1000)
            return@withContext true
        }
    }
}