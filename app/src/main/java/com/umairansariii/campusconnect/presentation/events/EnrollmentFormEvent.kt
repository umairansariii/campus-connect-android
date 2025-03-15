package com.umairansariii.campusconnect.presentation.events

import com.umairansariii.campusconnect.data.local.enums.UserGender
import java.util.Date

sealed interface EnrollmentFormEvent {
    data class UniversityChanged(val universityId: Int): EnrollmentFormEvent
    data class CampusChanged(val campusId: Int): EnrollmentFormEvent
    data class DepartmentChanged(val departmentId: Int): EnrollmentFormEvent
    data class RollNoChanged(val rollNo: String): EnrollmentFormEvent
    data class DobChanged(val dob: Date): EnrollmentFormEvent
    data class GenderChanged(val gender: UserGender): EnrollmentFormEvent
    data class Submit(val studentId: Int): EnrollmentFormEvent
}