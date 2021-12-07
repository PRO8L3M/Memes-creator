package com.pro8l3m.data.remote.services

import com.pro8l3m.domain.models.MemeModel

interface MemesService {

    suspend fun getMemes(): List<MemeModel>
}