package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.umairansariii.campusconnect.data.local.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user ORDER BY firstName ASC")
    fun getUsers(): Flow<List<User>>
}