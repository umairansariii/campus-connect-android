package com.umairansariii.campusconnect.data.local.dto

import com.umairansariii.campusconnect.data.local.enums.UserGender
import com.umairansariii.campusconnect.data.local.enums.UserStatus
import java.util.Date

data class EnrolledStudent (
    val userId: Int,
    val createdAt: Date,
    val firstName: String,
    val lastName: String,
    val email: String,
    val status: UserStatus,
    val enrollmentId: Int,
    val rollNo: String,
    val dob: Date,
    val gender: UserGender,
    val universityName: String,
    val campusName: String,
    val departmentName: String,
    val cgpa: Double?,
    val semester: Int?,
)