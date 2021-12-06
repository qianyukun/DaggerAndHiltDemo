package com.qyk.daggerdemo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {

    @Inject
    lateinit var userOnlineRepository: UserOnlineRepository

    @Inject
    lateinit var userOfflineRepository: UserOfflineRepository

    @Inject
    lateinit var userOfflineRepository2: UserOfflineRepository

    fun getUserById(userId: String): String {
        android.util.Log.e("xxxx", (userOfflineRepository  == userOfflineRepository2).toString())
        return if (userOnlineRepository.enable()) {
            userOnlineRepository.getUserById(userId)
        } else {
            userOfflineRepository.getUserById(userId)
        }
    }
}
