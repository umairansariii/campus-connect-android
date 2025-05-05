package com.umairansariii.campusconnect.presentation.states

data class DiscussionFormState (
    val showDialog: Boolean = false,
    val showDialogId: Int? = null,
    val discussionTitle: String = "",
    val discussionTitleError: String? = null,
    val discussionDescription: String = "",
    val discussionDescriptionError: String? = null,
    val discussionIsActive: Boolean = true,
    val discussionQuery: String = "",
)
