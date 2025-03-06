package com.umairansariii.campusconnect.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.umairansariii.campusconnect.data.local.converters.DateConverter
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.local.entities.User

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}