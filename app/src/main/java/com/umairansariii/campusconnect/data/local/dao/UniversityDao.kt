package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.entities.University
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversity(university: University)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUniversity(university: University)

    @Query("SELECT * FROM university WHERE id = :id")
    suspend fun getUniversityById(id: Int): University

    @Query("SELECT * FROM university WHERE adminId = :adminId")
    fun getUniversitiesByAdmin(adminId: Int): Flow<List<University>>
}