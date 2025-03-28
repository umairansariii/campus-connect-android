package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.data.local.entities.User
import com.umairansariii.campusconnect.domain.usecase.ValidateEmail
import com.umairansariii.campusconnect.domain.usecase.ValidateEmpty
import com.umairansariii.campusconnect.presentation.events.LoginFormEvent
import com.umairansariii.campusconnect.presentation.states.LoginFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {
    private val validateEmail = ValidateEmail()
    private val validateEmpty = ValidateEmpty()
    var state by mutableStateOf(LoginFormState())

    private val _validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = _validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when(event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email, emailError = null)
            }

            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password, passwordError = null)
            }

            is LoginFormEvent.Submit -> {
                submit()
            }
        }
    }

    private fun submit() {
        val emailResult = validateEmail.execute(value = state.email, fieldName = "Email")
        val passwordResult = validateEmpty.execute(value = state.password, fieldName = "Password")

        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            val user = userDao.getUserByEmail(state.email)

            if (user == null) {
                state = state.copy(emailError = "User email does not exist")
                return@launch
            }

            if (user.password != state.password) {
                state = state.copy(passwordError = "Incorrect password")
                return@launch
            }

            _validationEventChannel.send(ValidationEvent.Success(user))
        }
    }

    sealed class ValidationEvent {
        data class Success(val user: User): ValidationEvent()
    }
}