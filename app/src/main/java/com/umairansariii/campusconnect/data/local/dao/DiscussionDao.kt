package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.dto.DiscussionUniversity
import com.umairansariii.campusconnect.data.local.entities.Discussion
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscussionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiscussion(discussion: Discussion)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDiscussion(discussion: Discussion)

    @Query("SELECT * FROM discussion WHERE id = :id")
    suspend fun getDiscussionById(id: Int): Discussion

    @Query("""
        SELECT * FROM discussion
        WHERE universityId = :universityId
        AND (:searchQuery IS NULL OR title LIKE '%' || :searchQuery || '%')
    """)
    fun getDiscussionsByUniversity(universityId: Int, searchQuery: String): Flow<List<Discussion>>

    @Query("""
        SELECT dc.*, u.title as universityName
        FROM enrollment e
        JOIN discussion dc ON e.universityId = dc.universityId
        JOIN university u ON e.universityId = u.id
        WHERE e.studentId = :studentId AND dc.isActive = 1
    """)
    fun getDiscussionsByStudent(studentId: Int): Flow<List<DiscussionUniversity>>
}