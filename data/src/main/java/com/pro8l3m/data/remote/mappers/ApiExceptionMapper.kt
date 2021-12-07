package com.pro8l3m.data.remote.mappers

import com.pro8l3m.data.remote.models.api.ApiException
import com.pro8l3m.data.remote.models.api.ApiResponse
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import retrofit2.HttpException
import java.io.IOException

fun Throwable.asRestException(): ApiException {
    return when (this) {
        is HttpException -> this.asHttpError()
        is IOException -> this.asNetworkError()
        else -> this.asUnexpectedError()
    }
}

private fun HttpException.asHttpError(): ApiException.Http {
    return ApiException.Http(
        this.message,
        this
    )
}

private fun IOException.asNetworkError(): ApiException.Network {
    return ApiException.Network(
        this.message,
        this
    )
}

private fun Throwable.asUnexpectedError(): ApiException.Unexpected {
    return ApiException.Unexpected(
        this.message,
        this
    )
}

@Suppress("SwallowedException")
private fun getRestResponseFromBody(responseBody: String): ApiResponse<*>? {
    return try {
        val moshi = Moshi.Builder().build()
        val parameterizedType = Types.newParameterizedType(ApiResponse::class.java, Any::class.java)
        val adapter = moshi.adapter<ApiResponse<*>>(parameterizedType).lenient()
        adapter.fromJson(responseBody)
    } catch (exception: JsonDataException) {
        ApiResponse( null)
    }
}
