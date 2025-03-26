package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.local.entities.User
import com.umairansariii.campusconnect.data.local.enums.UserRole
import com.umairansariii.campusconnect.data.local.enums.UserStatus
import com.umairansariii.campusconnect.domain.usecase.ValidateEmail
import com.umairansariii.campusconnect.domain.usecase.ValidateEmptyAlpha
import com.umairansariii.campusconnect.domain.usecase.ValidatePassword
import com.umairansariii.campusconnect.domain.usecase.ValidateRepeatedPassword
import com.umairansariii.campusconnect.presentation.events.RegisterFormEvent
import com.umairansariii.campusconnect.presentation.states.RegisterFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor (
    private val userDao: UserDao
): ViewModel() {
    private val validateEmptyAlpha = ValidateEmptyAlpha()
    private val validateEmail = ValidateEmail()
    private val validatePassword = ValidatePassword()
    private val validateRepeatedPassword = ValidateRepeatedPassword()
    var state by mutableStateOf(RegisterFormState())

    private val _validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = _validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterFormEvent) {
        when(event) {
            is RegisterFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName, firstNameError = null)
            }

            is RegisterFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName, lastNameError = null)
            }

            is RegisterFormEvent.EmailChanged -> {
                state = state.copy(email = event.email, emailError = null)
            }

            is RegisterFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password, passwordError = null)
            }

            is RegisterFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword, repeatedPasswordError = null)
            }

            is RegisterFormEvent.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {
        val firstNameResult = validateEmptyAlpha.execute(value = state.firstName, fieldName = "First name")
        val lastNameResult = validateEmptyAlpha.execute(value = state.lastName, fieldName = "Last name")
        val emailResult = validateEmail.execute(value = state.email, fieldName = "Email")
        val passwordResult = validatePassword.execute(value = state.password, fieldName = "Password")
        val repeatedPasswordResult = validateRepeatedPassword.execute(password = state.password, repeatedPassword = state.repeatedPassword)

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            repeatedPasswordResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            val userId = userDao.insertUser(
                User(
                    createdAt = Date(),
                    firstName = state.firstName,
                    lastName = state.lastName,
                    email = state.email,
                    password = state.password,
                    role = UserRole.STUDENT,
                    status = UserStatus.PENDING
                )
            )

            val insertedUser = userDao.getUserById(userId.toInt())

            _validationEventChannel.send(ValidationEvent.Success(insertedUser))
        }

        state = state.copy(
            firstName = "",
            firstNameError = null,
            lastName = "",
            lastNameError = null,
            email = "",
            emailError = null,
            password = "",
            passwordError = null,
            repeatedPassword = "",
            repeatedPasswordError = null,
        )
    }

    sealed class ValidationEvent {
        data class Success(val user: User): ValidationEvent()
    }
}