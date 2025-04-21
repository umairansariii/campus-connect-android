package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.entities.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: Event)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvent(event: Event)

    @Query("SELECT * FROM event WHERE id = :id")
    suspend fun getEventById(id: Int): Event

    @Query("SELECT * FROM event WHERE universityId = :universityId")
    fun getEventsByUniversity(universityId: Int): Flow<List<Event>>
}