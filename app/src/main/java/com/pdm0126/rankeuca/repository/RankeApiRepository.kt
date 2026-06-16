package com.pdm0126.rankeuca.repository

import com.pdm0126.rankeuca.data.database.dao.VoteDTO
import com.pdm0126.rankeuca.data.api.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RankeApiRepository {
    suspend fun getVotes(): List<VoteDTO>{
        return KtorClient.client
            .get("https://qjcxdvfzyseuvezacxsd.supabase.co/functions/v1/rankeuca/options")
            .body()
    }
}