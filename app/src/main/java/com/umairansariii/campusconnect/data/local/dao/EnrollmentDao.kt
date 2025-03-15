package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umairansariii.campusconnect.data.local.entities.Campus
import com.umairansariii.campusconnect.data.local.entities.Department
import com.umairansariii.campusconnect.data.local.entities.Enrollment
import com.umairansariii.campusconnect.data.local.entities.University
import kotlinx.coroutines.flow.Flow

@Dao
interface EnrollmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnrollment(enrollment: Enrollment)

    @Query("SELECT * FROM university")
    fun getUniversities(): Flow<List<University>>

    @Query("SELECT * FROM campus WHERE universityId = :universityId")
    fun getCampusesByUniversity(universityId: Int? = null): Flow<List<Campus>>

    @Query("SELECT * FROM department WHERE universityId = :universityId")
    fun getDepartmentsByUniversity(universityId: Int? = null): Flow<List<Department>>
}