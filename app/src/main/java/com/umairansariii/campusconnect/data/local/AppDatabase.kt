package com.umairansariii.campusconnect.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.umairansariii.campusconnect.data.local.converters.DateConverter
import com.umairansariii.campusconnect.data.local.dao.CampusDao
import com.umairansariii.campusconnect.data.local.dao.DepartmentDao
import com.umairansariii.campusconnect.data.local.dao.UniversityDao
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.local.entities.Campus
import com.umairansariii.campusconnect.data.local.entities.Department
import com.umairansariii.campusconnect.data.local.entities.University
import com.umairansariii.campusconnect.data.local.entities.User

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [
        User::class,
        University::class,
        Department::class,
        Campus::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun universityDao(): UniversityDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun campusDao(): CampusDao
}