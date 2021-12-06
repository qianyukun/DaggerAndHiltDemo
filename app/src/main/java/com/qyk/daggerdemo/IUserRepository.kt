package com.qyk.daggerdemo

interface IUserRepository {
    fun enable(): Boolean
    fun getUserById(userId: String): String
}