package com.qyk.daggerdemo

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserOfflineRepository @Inject constructor() : IUserRepository {
    override fun enable(): Boolean {
        return false
    }

    override fun getUserById(userId: String): String {
        return "Offline $userId"
    }
}
