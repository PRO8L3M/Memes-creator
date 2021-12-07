package com.pro8l3m.data.remote.models.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiResponse<T>(
    @Json(name = "data")
    val data: T?
)