package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.dto.EventUniversity
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

    @Query("""
        SELECT * FROM event
        WHERE universityId = :universityId
        AND (:searchQuery IS NULL OR title LIKE '%' || :searchQuery || '%')
        ORDER BY date DESC
    """)
    fun getEventsByUniversity(universityId: Int, searchQuery: String): Flow<List<Event>>

    @Query("""
        SELECT ev.*, u.title as universityName, c.title as campusName
        FROM enrollment e
        JOIN event ev ON e.universityId = ev.universityId
        JOIN university u ON e.universityId = u.id
        JOIN campus c ON e.campusId = c.id
        WHERE e.studentId = :studentId AND ev.isActive = 1
        ORDER BY date DESC
    """)
    fun getEventsByStudent(studentId: Int): Flow<List<EventUniversity>>
}