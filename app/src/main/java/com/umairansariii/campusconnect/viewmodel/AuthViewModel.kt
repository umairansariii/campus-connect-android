package com.umairansariii.campusconnect.viewmodel

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.entities.User
import com.umairansariii.campusconnect.data.store.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authDataStore: DataStore<AuthState>
): ViewModel() {
    val authState = authDataStore.data

    fun setLoggedIn(user: User) {
        viewModelScope.launch {
            authDataStore.updateData { currentState ->
                currentState.copy(
                    id = user.id,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    email = user.email,
                    role = user.role,
                    status = user.status,
                    isAuthenticated = true,
                )
            }
        }
    }

    fun setLoggedOut() {
        viewModelScope.launch {
            authDataStore.updateData { currentState ->
                currentState.copy(
                    id = null,
                    firstName = "",
                    lastName = "",
                    email = "",
                    role = null,
                    status = null,
                    isAuthenticated = false,
                )
            }
        }
    }
}