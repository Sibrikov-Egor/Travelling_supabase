package com.example.travelling.Tables

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val id: String,
    val name: String
)