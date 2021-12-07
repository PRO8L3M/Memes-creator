package com.pro8l3m.domain.usecases

import com.pro8l3m.domain.models.MemeModel
import com.pro8l3m.domain.repositories.MemesRepository
import com.pro8l3m.domain.usecases.base.UseCase
import javax.inject.Inject

class GetMemesUseCase @Inject constructor(
    private val repository: MemesRepository
) : UseCase<Unit, List<MemeModel>>() {

    override suspend fun run(params: Unit): List<MemeModel> = repository.getMemes()
}