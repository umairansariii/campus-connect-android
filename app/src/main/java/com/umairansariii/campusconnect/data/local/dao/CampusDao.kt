package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.entities.Campus
import kotlinx.coroutines.flow.Flow

@Dao
interface CampusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCampus(campus: Campus)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCampus(campus: Campus)

    @Query("SELECT * FROM campus WHERE id = :id")
    suspend fun getCampusById(id: Int): Campus

    @Query("""
        SELECT * FROM campus
        WHERE universityId = :universityId
        AND (:searchQuery IS NULL OR title LIKE '%' || :searchQuery || '%')
    """)
    fun getCampusesByUniversity(universityId: Int, searchQuery: String): Flow<List<Campus>>
}