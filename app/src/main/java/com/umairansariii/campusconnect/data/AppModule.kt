package com.umairansariii.campusconnect.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.room.Room
import com.umairansariii.campusconnect.data.local.AppDatabase
import com.umairansariii.campusconnect.data.local.dao.CampusDao
import com.umairansariii.campusconnect.data.local.dao.ClubDao
import com.umairansariii.campusconnect.data.local.dao.DepartmentDao
import com.umairansariii.campusconnect.data.local.dao.EnrollmentDao
import com.umairansariii.campusconnect.data.local.dao.EventDao
import com.umairansariii.campusconnect.data.local.dao.NotificationDao
import com.umairansariii.campusconnect.data.local.dao.StudentDao
import com.umairansariii.campusconnect.data.local.dao.UniversityDao
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.store.auth.AuthSerializer
import com.umairansariii.campusconnect.data.store.auth.AuthState
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
    fun provideEnrollmentDao(database: AppDatabase): EnrollmentDao {
        return database.enrollmentDao()
    }

    @Provides
    fun provideUniversityDao(database: AppDatabase): UniversityDao {
        return database.universityDao()
    }

    @Provides
    fun provideDepartmentDao(database: AppDatabase): DepartmentDao {
        return database.departmentDao()
    }

    @Provides
    fun provideCampusDao(database: AppDatabase): CampusDao {
        return database.campusDao()
    }

    @Provides
    fun provideNotificationDao(database: AppDatabase): NotificationDao {
        return database.notificationDao()
    }

    @Provides
    fun provideEventDao(database: AppDatabase): EventDao {
        return database.eventDao()
    }

    @Provides
    fun provideClubDao(database: AppDatabase): ClubDao {
        return database.clubDao()
    }

    @Provides
    fun provideStudentDao(database: AppDatabase): StudentDao {
        return database.studentDao()
    }

    private val Context.authDataStore: DataStore<AuthState> by dataStore(
        fileName = "auth.json",
        serializer = AuthSerializer
    )

    @Provides
    @Singleton
    fun provideAuthDataStore(@ApplicationContext context: Context): DataStore<AuthState> {
        return context.authDataStore
    }
}