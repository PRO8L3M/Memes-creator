package com.pro8l3m.data.repositories

import com.pro8l3m.data.remote.services.MemesService
import com.pro8l3m.domain.models.MemeModel
import com.pro8l3m.domain.repositories.MemesRepository
import javax.inject.Inject

class DefaultMemesRepository @Inject constructor(
    private val service: MemesService
) : MemesRepository {

    override suspend fun getMemes(): List<MemeModel> = service.getMemes()

}