package com.pro8l3m.data.remote.endpoints

import com.pro8l3m.data.remote.models.MemeApiModel
import com.pro8l3m.data.remote.models.api.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface MemesEndpoints {

    @GET("/memes")
    suspend fun getMemes(): ApiResponse<List<MemeApiModel>>
}