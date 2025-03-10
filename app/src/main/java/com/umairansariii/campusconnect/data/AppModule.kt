package com.umairansariii.campusconnect.data

import android.content.Context
import androidx.room.Room
import com.umairansariii.campusconnect.data.local.AppDatabase
import com.umairansariii.campusconnect.data.local.dao.DepartmentDao
import com.umairansariii.campusconnect.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "campusconnect.db"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideDepartmentDao(database: AppDatabase): DepartmentDao {
        return database.departmentDao()
    }
}