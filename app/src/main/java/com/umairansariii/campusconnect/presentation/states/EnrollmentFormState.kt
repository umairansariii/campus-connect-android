package com.umairansariii.campusconnect.presentation.states

import com.umairansariii.campusconnect.data.local.enums.UserGender
import java.util.Date

data class EnrollmentFormState (
    val studentId: Int? = 0,
    val universityId: Int? = null,
    val universityIdError: String? = null,
    val campusId: Int? = null,
    val campusIdError: String? = null,
    val departmentId: Int? = null,
    val departmentIdError: String? = null,
    val rollNo: String = "",
    val rollNoError: String? = null,
    val dob: Date? = null,
    val dobError: String? = null,
    val gender: UserGender? = null,
    val genderError: String? = null,
    val acceptTerms: Boolean = false,
    val acceptTermsError: Boolean = false,
)