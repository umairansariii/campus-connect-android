package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.umairansariii.campusconnect.data.local.entities.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContact(contact: Contact)

    @Query("SELECT * FROM contact WHERE id = :id")
    suspend fun getContactById(id: Int): Contact

    @Query("""
        SELECT * FROM contact
        WHERE universityId = :universityId
        AND (:searchQuery IS NULL OR name LIKE '%' || :searchQuery || '%')
        ORDER BY name ASC
    """)
    fun getContactsByUniversity(universityId: Int, searchQuery: String): Flow<List<Contact>>
}