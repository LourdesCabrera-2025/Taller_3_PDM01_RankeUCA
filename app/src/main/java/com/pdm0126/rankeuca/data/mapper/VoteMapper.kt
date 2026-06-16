package com.pdm0126.rankeuca.data.mapper

import com.pdm0126.rankeuca.data.database.dao.VoteDTO
import com.pdm0126.rankeuca.model.Vote

private const val IMAGE_SITE = "https://placehold.co/400x300?"

fun VoteDTO.toModel(): Vote {
    return Vote(
        id =  id,
        name = name,
        imageUrl = if (!imageUrl.isNullOrBlank()) imageUrl else "https://placehold.co/400x300?text=No+Image",
        votes = votes
    )
}