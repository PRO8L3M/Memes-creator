package com.pro8l3m.domain.models

import java.io.Serializable

data class MemeModel(
    val id: Long,
    val name: String,
    val tags: List<String>,
    val imageUrl: String
): Serializable