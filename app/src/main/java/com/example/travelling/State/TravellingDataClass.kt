package com.example.travelling.State

import kotlinx.serialization.Serializable

@Serializable
data class TravellingDataClass(
    val name: String = "",
    val description: String = "",
    val categoryId: String = "",
    val image: String = "",
    val Country: String = "",
    val Sozdatel: String? = "",
)