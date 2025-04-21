package com.example.travelling.Tables

import kotlinx.serialization.Serializable


@Serializable
data class Travells(
    val id: String,
    val name: String,
    val description: String,
    val categoryId: String,
    val image: String
)