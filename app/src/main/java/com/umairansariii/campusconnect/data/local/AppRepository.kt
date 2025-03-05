package com.umairansariii.campusconnect.data.local

import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.local.entities.User

class AppRepository(
    private val userDao: UserDao
) {
    // User State
    val users = userDao.getAllUsers()

    // User Methods
    fun getUserById(id: Int) = userDao.getUser(id)

    suspend fun createUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}