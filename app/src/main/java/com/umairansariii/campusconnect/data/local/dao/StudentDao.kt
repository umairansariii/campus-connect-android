package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.dto.AcademicStudent
import com.umairansariii.campusconnect.data.local.dto.EnrolledStudent
import com.umairansariii.campusconnect.data.local.dto.UserStudent
import com.umairansariii.campusconnect.data.local.entities.Academic
import com.umairansariii.campusconnect.data.local.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAcademic(academic: Academic)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAcademic(academic: Academic)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun approveStudent(user: User)

    @Query("""
        SELECT u.id as userId, a.* FROM user u
        JOIN enrollment e ON u.id = e.studentId
        LEFT JOIN academic a ON e.id = a.enrollmentId
        WHERE u.id = :studentId
    """)
    suspend fun getAcademicByStudentId(studentId: Int): AcademicStudent

    @Query("""
        SELECT u.*, e.id as enrollmentId, e.rollNo
        FROM user u
        JOIN enrollment e ON u.id = e.studentId
        WHERE u.id = :studentId
    """)
    suspend fun getStudentById(studentId: Int): UserStudent

    @Query("""
        SELECT
        user.id as userId, user.createdAt, user.firstName, user.lastName, user.email, user.status,
        enrollment.id as enrollmentId, enrollment.rollNo, enrollment.dob, enrollment.gender,
        university.title as universityName,
        campus.title as campusName,
        department.title as departmentName,
        academic.cgpa, academic.semester
        FROM user
        JOIN enrollment ON user.id = enrollment.studentId
        JOIN university ON enrollment.universityId = university.id
        JOIN campus ON enrollment.campusId = campus.id
        JOIN department ON enrollment.departmentId = department.id
        LEFT JOIN academic ON enrollment.id = academic.enrollmentId
        WHERE user.id = :studentId;
    """)
    suspend fun getEnrolledStudentById(studentId: Int): EnrolledStudent

    @Query("""
        SELECT u.*, e.id as enrollmentId, e.rollNo
        FROM user u
        JOIN enrollment e ON u.id = e.studentId
        WHERE e.universityId = :universityId;
    """)
    fun getStudentsByUniversity(universityId: Int): Flow<List<UserStudent>>
}