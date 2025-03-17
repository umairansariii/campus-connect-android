package com.umairansariii.campusconnect.presentation.events

sealed interface StudentFormEvent {
    data class ShowViewDialog(val id: Int): StudentFormEvent
    class DismissViewDialog: StudentFormEvent
    data class ShowUpdateDialog(val id: Int): StudentFormEvent
    class DismissUpdateDialog: StudentFormEvent
    data class ShowApproveDialog(val id: Int): StudentFormEvent
    class DismissApproveDialog: StudentFormEvent
    data class StudentCgpaChanged(val studentCgpa: Double): StudentFormEvent
    data class StudentSemesterChanged(val studentSemester: Int): StudentFormEvent
    data class SubmitUpdate(val universityId: Int): StudentFormEvent
    data class SubmitApprove(val studentId: Int): StudentFormEvent
}