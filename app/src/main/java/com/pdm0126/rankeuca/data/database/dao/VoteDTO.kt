package com.pdm0126.rankeuca.data.database.dao

import kotlinx.serialization.Serializable

@Serializable
data class VoteDTO(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val votes: Int,

)