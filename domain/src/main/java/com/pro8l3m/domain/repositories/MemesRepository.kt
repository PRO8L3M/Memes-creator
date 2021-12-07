package com.pro8l3m.domain.repositories

import com.pro8l3m.domain.models.MemeModel

interface MemesRepository {

    suspend fun getMemes(): List<MemeModel>
}