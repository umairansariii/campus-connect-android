package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umairansariii.campusconnect.data.local.dto.ClubChatUser
import com.umairansariii.campusconnect.data.local.entities.ClubChat
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(clubChat: ClubChat)

    @Query("""
        SELECT c.*, u.firstName, u.lastName, u.role
        FROM club_chat c
        JOIN user u ON c.senderId = u.id
        WHERE clubId = :clubId
    """)
    fun getChatsByClub(clubId: Int): Flow<List<ClubChatUser>>
}