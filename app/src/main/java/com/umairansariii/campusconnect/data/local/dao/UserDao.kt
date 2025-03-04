package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.umairansariii.campusconnect.data.local.entities.UserEntity

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}