package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.ContactDao
import com.umairansariii.campusconnect.data.local.entities.Contact
import com.umairansariii.campusconnect.domain.usecase.ValidateEmpty
import com.umairansariii.campusconnect.domain.usecase.ValidateNull
import com.umairansariii.campusconnect.presentation.events.ContactFormEvent
import com.umairansariii.campusconnect.presentation.states.ContactFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactDao: ContactDao,
): ViewModel() {
    private val validateEmpty = ValidateEmpty()
    private val validateNull = ValidateNull()
    var state by mutableStateOf(ContactFormState())

    fun getContactsByUniversity(universityId: Int): Flow<List<Contact>> {
        return contactDao.getContactsByUniversity(universityId, state.contactQuery)
    }

    fun getContactsByStudent(studentId: Int): Flow<List<Contact>> {
        return contactDao.getContactsByStudent(studentId, state.contactQuery)
    }

    fun onEvent(event: ContactFormEvent) {
        when(event) {
            is ContactFormEvent.ShowDialog -> {
                if (event.id !== null) {
                    viewModelScope.launch {
                        val contactData = contactDao.getContactById(event.id)
                        state = state.copy(
                            showDialog = true,
                            showDialogId = event.id,
                            contactName = contactData.name,
                            contactPhone = contactData.phone,
                            contactEmail = contactData.email,
                            contactType = contactData.type,
                        )
                    }
                } else {
                    state = state.copy(showDialog = true)
                }
            }

            is ContactFormEvent.DismissDialog -> {
                state = state.copy(
                    showDialog = false,
                    showDialogId = null,
                    contactName = "",
                    contactNameError = null,
                    contactPhone = "",
                    contactPhoneError = null,
                    contactEmail = "",
                    contactEmailError = null,
                    contactType = null,
                    contactTypeError = null,
                )
            }

            is ContactFormEvent.ContactNameChanged -> {
                state = state.copy(contactName = event.contactName, contactNameError = null)
            }

            is ContactFormEvent.ContactPhoneChanged -> {
                state = state.copy(contactPhone = event.contactPhone, contactPhoneError = null)
            }

            is ContactFormEvent.ContactEmailChanged -> {
                state = state.copy(contactEmail = event.contactEmail, contactEmailError = null)
            }

            is ContactFormEvent.ContactTypeChanged -> {
                state = state.copy(contactType = event.contactType, contactTypeError = null)
            }

            is ContactFormEvent.ContactQueryChanged -> {
                state = state.copy(contactQuery = event.contactQuery)
            }

            is ContactFormEvent.Submit -> {
                submit(event.universityId)
            }
        }
    }

    private fun submit(universityId: Int) {
        val nameResult = validateEmpty.execute(value = state.contactName, fieldName = "Name")
        val phoneResult = validateEmpty.execute(value = state.contactPhone, fieldName = "Phone")
        val emailResult = validateEmpty.execute(value = state.contactEmail, fieldName = "Email")
        val typeResult = validateNull.execute(value = state.contactType, fieldName = "Type")

        val hasError = listOf(
            nameResult,
            phoneResult,
            emailResult,
            typeResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                contactNameError = nameResult.errorMessage,
                contactPhoneError = phoneResult.errorMessage,
                contactEmailError = emailResult.errorMessage,
                contactTypeError = typeResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            if (state.showDialogId !== null) {
                contactDao.updateContact(
                    Contact(
                        id = state.showDialogId!!,
                        universityId = universityId,
                        name = state.contactName,
                        phone = state.contactPhone,
                        email = state.contactEmail,
                        type = state.contactType!!,
                    )
                )
            } else {
                contactDao.insertContact(
                    Contact(
                        universityId = universityId,
                        name = state.contactName,
                        phone = state.contactPhone,
                        email = state.contactEmail,
                        type = state.contactType!!,
                    )
                )
            }
        }

        state = state.copy(
            showDialog = false,
            showDialogId = null,
            contactName = "",
            contactNameError = null,
            contactPhone = "",
            contactPhoneError = null,
            contactEmail = "",
            contactEmailError = null,
            contactType = null,
            contactTypeError = null,
        )
    }
}