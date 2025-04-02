package com.example.travelling.Item

import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: String,
    val username: String,

)