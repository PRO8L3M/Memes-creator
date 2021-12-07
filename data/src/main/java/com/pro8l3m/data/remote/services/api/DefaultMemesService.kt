package com.pro8l3m.data.remote.services.api

import com.pro8l3m.data.remote.ApiService
import com.pro8l3m.data.remote.endpoints.MemesEndpoints
import com.pro8l3m.data.remote.mappers.toDomain
import com.pro8l3m.data.remote.services.MemesService
import com.pro8l3m.domain.models.MemeModel
import javax.inject.Inject

class DefaultMemesService @Inject constructor(
    private val endpoints: MemesEndpoints
) : ApiService(), MemesService {

    override suspend fun getMemes(): List<MemeModel> = request { endpoints.getMemes() }.toDomain()
}