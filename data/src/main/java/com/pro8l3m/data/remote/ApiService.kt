package com.pro8l3m.data.remote

import com.pro8l3m.data.remote.mappers.asRestException
import com.pro8l3m.data.remote.models.api.ApiResponse
import retrofit2.HttpException
import retrofit2.Response

abstract class ApiService {

    suspend fun <T> request(apiCall: suspend () -> ApiResponse<T>): T {
        return try {
            apiCall.invoke().data ?: throw NullPointerException().asRestException()
        } catch (throwable: Throwable) {
            throw throwable.asRestException()
        }
    }

    suspend fun <T> requestApiResponse(apiCall: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return try {
            apiCall.invoke()
        } catch (throwable: HttpException) {
            ApiResponse(data = null)
        } catch (throwable: Throwable) {
            throw throwable.asRestException()
        }
    }
}
