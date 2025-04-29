package com.example.travelling.Tables

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Travelling(
    val id: String,
    val name: String,
    val description: String,
    @SerialName("category_id")
    val categoryId: String,
    @SerialName("url")
    val image: String,
    val Country: String = "",
    val Sozdatel: String = ""
)