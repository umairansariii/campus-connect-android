package com.umairansariii.campusconnect.viewmodel

import androidx.lifecycle.ViewModel
import com.umairansariii.campusconnect.data.local.dao.ClubDao
import com.umairansariii.campusconnect.data.local.dao.DiscussionDao
import com.umairansariii.campusconnect.data.local.dao.EventDao
import com.umairansariii.campusconnect.data.local.dto.ClubUniversity
import com.umairansariii.campusconnect.data.local.dto.DiscussionUniversity
import com.umairansariii.campusconnect.data.local.dto.EventUniversity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val eventDao: EventDao,
    private val clubDao: ClubDao,
    private val discussionDao: DiscussionDao,
): ViewModel() {
    fun getEventsByStudent(studentId: Int): Flow<List<EventUniversity>> {
        return eventDao.getEventsByStudent(studentId)
    }

    fun getClubsByStudent(studentId: Int): Flow<List<ClubUniversity>> {
        return clubDao.getClubsByStudent(studentId)
    }

    fun getDiscussionsByStudent(studentId: Int): Flow<List<DiscussionUniversity>> {
        return discussionDao.getDiscussionsByStudent(studentId)
    }
}