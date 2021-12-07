package com.pro8l3m.data.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MemeOuterApiModel(
    val memes: List<MemeApiModel>
)

@JsonClass(generateAdapter = true)
data class MemeApiModel(
    val id: Long,
    val name: String,
    val tags: List<String>,
    val imageUrl: String,
)
