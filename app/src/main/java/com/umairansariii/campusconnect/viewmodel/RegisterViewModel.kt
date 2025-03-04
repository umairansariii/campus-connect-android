package com.umairansariii.campusconnect.viewmodel

import androidx.lifecycle.ViewModel
import com.umairansariii.campusconnect.data.local.dao.UserDao
import com.umairansariii.campusconnect.presentation.events.RegisterFormEvent
import com.umairansariii.campusconnect.presentation.states.RegisterFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val userDao: UserDao
): ViewModel() {
    private val _state = MutableStateFlow(RegisterFormState())

    fun onEvent(event: RegisterFormEvent) {
        when(event) {
            is RegisterFormEvent.FirstNameChanged -> {
                _state.update {
                    it.copy(firstName = event.firstName, firstNameError = null)
                }
            }

            is RegisterFormEvent.LastNameChanged -> {
                _state.update {
                    it.copy(lastName = event.lastName, lastNameError = null)
                }
            }

            is RegisterFormEvent.EmailChanged -> {
                _state.update {
                    it.copy(email = event.email, emailError = null)
                }
            }

            is RegisterFormEvent.PasswordChanged -> {
                _state.update {
                    it.copy(password = event.password, passwordError = null)
                }
            }

            is RegisterFormEvent.RepeatedPasswordChanged -> {
                _state.update {
                    it.copy(repeatedPassword = event.repeatedPassword, repeatedPasswordError = null)
                }
            }

            is RegisterFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {

    }
}