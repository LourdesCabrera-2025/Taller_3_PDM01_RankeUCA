package com.pdm0126.rankeuca.model

import io.ktor.http.Url
import kotlinx.serialization.Serializable

@Serializable
data class Vote(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int
)