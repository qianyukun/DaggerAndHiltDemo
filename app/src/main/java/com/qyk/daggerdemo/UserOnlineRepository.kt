package com.qyk.daggerdemo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserOnlineRepository @Inject constructor() : IUserRepository {
    override fun enable(): Boolean {
        return true
    }

    override fun getUserById(userId: String): String {
        return "Online $userId"
    }

}
