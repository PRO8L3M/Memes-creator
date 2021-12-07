package com.pro8l3m.domain.usecases.base

import kotlinx.coroutines.*

abstract class UseCase<in Params, out Type> {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onSuccess: (Type) -> Unit = {},
        onFailure: (Throwable) -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.Default
    ): Job = scope.launch(dispatcher) {
        runCatching { run(params) }
            .onSuccess { onSuccess(it) }
            .onFailure { onFailure(it) }
    }
}
