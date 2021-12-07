package com.pro8l3m.data.remote.mappers

import com.pro8l3m.data.remote.models.MemeApiModel
import com.pro8l3m.domain.models.MemeModel

fun MemeApiModel.toDomain() = MemeModel(
    id, name, tags, imageUrl
)

fun List<MemeApiModel>.toDomain() = map { it.toDomain() }