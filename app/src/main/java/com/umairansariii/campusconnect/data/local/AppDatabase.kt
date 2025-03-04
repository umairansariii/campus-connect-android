package com.umairansariii.campusconnect.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.local.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}