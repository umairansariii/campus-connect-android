package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.entities.Department
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepartment(department: Department)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDepartment(department: Department)

    @Query("SELECT * FROM department WHERE id = :id")
    fun getDepartmentById(id: Int): Flow<Department>

    @Query("""
        SELECT * FROM department 
        WHERE universityId = :universityId
        AND (:searchQuery IS NULL OR title LIKE '%' || :searchQuery || '%')
        LIMIT 5
        """)
    fun getDepartmentsByUniversity(universityId: Int, searchQuery: String): Flow<List<Department>>
}