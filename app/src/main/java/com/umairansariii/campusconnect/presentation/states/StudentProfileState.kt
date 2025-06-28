package com.umairansariii.campusconnect.presentation.states

import com.umairansariii.campusconnect.data.local.enums.UserGender
import com.umairansariii.campusconnect.data.local.enums.UserStatus
import java.util.Date

data class StudentProfileState (
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val status: UserStatus? = null,
    val universityName: String = "",
    val departmentName: String = "",
    val campusName: String = "",
    val rollNo: String = "",
    val dob: Date? = null,
    val gender: UserGender? = null,
    val cgpa: String = "",
    val semester: String = "",
)
