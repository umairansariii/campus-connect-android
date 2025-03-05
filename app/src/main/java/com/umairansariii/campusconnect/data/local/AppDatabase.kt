package com.umairansariii.campusconnect.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    name = "campusconnect_db"
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}