package com.example.travelling.Tables

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: String,
    @SerialName("Email")
    val email: String,
    @SerialName("Password")
    val password: String
)