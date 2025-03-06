package com.umairansariii.campusconnect.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.local.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val userDao: UserDao
): ViewModel() {
    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    fun createUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }
}