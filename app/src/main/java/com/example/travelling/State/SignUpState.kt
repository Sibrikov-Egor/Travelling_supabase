package com.example.travelling.State


sealed class SignUpScreenEvent {
    data class UsernameUpdated(val newUsername: String): SignUpScreenEvent()
    data class EmailUpdated(val newEmail: String): SignUpScreenEvent()
    data class PasswordUpdated(val newPassword: String): SignUpScreenEvent()
}

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
)
