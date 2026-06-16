package com.pdm0126.rankeuca.model

import kotlinx.serialization.Serializable

@Serializable
data class Option (
    val id: Int = 0,
    val name: String,
    val imageUrl: String
)