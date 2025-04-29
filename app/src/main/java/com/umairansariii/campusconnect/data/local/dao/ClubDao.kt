package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.dto.ClubUniversity
import com.umairansariii.campusconnect.data.local.entities.Club
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClub(club: Club)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateClub(club: Club)

    @Query("SELECT * FROM club WHERE id = :id")
    suspend fun getClubById(id: Int): Club

    @Query("""
        SELECT * FROM club
        WHERE universityId = :universityId
        AND (:searchQuery IS NULL OR title LIKE '%' || :searchQuery || '%')
    """)
    fun getClubsByUniversity(universityId: Int, searchQuery: String): Flow<List<Club>>

    @Query("""
        SELECT cb.*, u.title as universityName, c.title as campusName
        FROM enrollment e
        JOIN club cb ON e.universityId = cb.universityId
        JOIN university u ON e.universityId = u.id
        JOIN campus c ON e.campusId = c.id
        WHERE e.studentId = :studentId AND cb.isActive = 1
    """)
    fun getClubsByStudent(studentId: Int): Flow<List<ClubUniversity>>
}