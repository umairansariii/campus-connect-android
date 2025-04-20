package com.umairansariii.campusconnect.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.umairansariii.campusconnect.data.local.converters.DateConverter
import com.umairansariii.campusconnect.data.local.dao.CampusDao
import com.umairansariii.campusconnect.data.local.dao.DepartmentDao
import com.umairansariii.campusconnect.data.local.dao.EnrollmentDao
import com.umairansariii.campusconnect.data.local.dao.EventDao
import com.umairansariii.campusconnect.data.local.dao.NotificationDao
import com.umairansariii.campusconnect.data.local.dao.StudentDao
import com.umairansariii.campusconnect.data.local.dao.UniversityDao
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.local.entities.Academic
import com.umairansariii.campusconnect.data.local.entities.Campus
import com.umairansariii.campusconnect.data.local.entities.Department
import com.umairansariii.campusconnect.data.local.entities.Enrollment
import com.umairansariii.campusconnect.data.local.entities.Event
import com.umairansariii.campusconnect.data.local.entities.Notification
import com.umairansariii.campusconnect.data.local.entities.University
import com.umairansariii.campusconnect.data.local.entities.User

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [
        User::class,
        Enrollment::class,
        Academic::class,
        University::class,
        Department::class,
        Campus::class,
        Notification::class,
        Event::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun enrollmentDao(): EnrollmentDao
    abstract fun universityDao(): UniversityDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun campusDao(): CampusDao
    abstract fun notificationDao(): NotificationDao
    abstract fun eventDao(): EventDao
    abstract fun studentDao(): StudentDao
}