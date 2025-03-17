package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.dto.EnrolledStudent
import com.umairansariii.campusconnect.data.local.dto.UserStudent
import com.umairansariii.campusconnect.data.local.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun approveStudent(user: User)

    @Query("SELECT * FROM user WHERE user.id = :studentId")
    suspend fun getStudentById(studentId: Int): User

    @Query("""
        SELECT
        user.id as userId, user.createdAt, user.firstName, user.lastName, user.email, user.status,
        enrollment.id as enrollmentId, enrollment.rollNo, enrollment.dob, enrollment.gender,
        university.title as universityName,
        campus.title as campusName,
        department.title as departmentName
        FROM user
        JOIN enrollment ON user.id = enrollment.studentId
        JOIN university ON enrollment.universityId = university.id
        JOIN campus ON enrollment.campusId = campus.id
        JOIN department ON enrollment.departmentId = department.id
        WHERE user.id = :studentId;
    """)
    suspend fun getEnrolledStudentById(studentId: Int): EnrolledStudent

    @Query("""
        SELECT u.id, u.createdAt, u.firstName, u.lastName, u.email, u.status, e.rollNo
        FROM user u
        JOIN enrollment e ON u.id = e.studentId
        WHERE e.universityId = :universityId;
    """)
    fun getStudentsByUniversity(universityId: Int): Flow<List<UserStudent>>
}