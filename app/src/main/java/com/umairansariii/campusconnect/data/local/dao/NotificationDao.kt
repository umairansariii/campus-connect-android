package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.entities.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: Notification)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNotification(notification: Notification)

    @Query("SELECT * FROM notification WHERE id = :notificationId")
    suspend fun getNotificationById(notificationId: Int): Notification

    @Query("SELECT * FROM notification WHERE universityId = :universityId")
    fun getAllNotificationsByUniversity(universityId: Int): Flow<List<Notification>>
}