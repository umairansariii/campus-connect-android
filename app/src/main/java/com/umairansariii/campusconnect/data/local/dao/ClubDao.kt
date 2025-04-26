package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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
    suspend fun getClubsByUniversity(universityId: Int, searchQuery: String): Flow<List<Club>>
}