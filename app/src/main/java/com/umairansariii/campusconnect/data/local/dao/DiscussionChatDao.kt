package com.umairansariii.campusconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.umairansariii.campusconnect.data.local.dto.DiscussionChatUser
import com.umairansariii.campusconnect.data.local.entities.DiscussionChat
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscussionChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(discussionChat: DiscussionChat)

    @Query("""
        SELECT dc.*, u.firstName, u.lastName, u.role
        FROM discussion_chat dc
        JOIN user u ON dc.senderId = u.id
        WHERE discussionId = :discussionId
    """)
    fun getChatsByDiscussion(discussionId: Int): Flow<List<DiscussionChatUser>>
}