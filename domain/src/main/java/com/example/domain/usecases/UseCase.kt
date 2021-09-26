package com.example.domain.usecases

import kotlinx.coroutines.*

abstract class UseCase<in Params, out Type> {

    abstract suspend fun run(params: Params): Type

    operator fun invoke(
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        params: Params,
        onSuccess: (Type) -> Unit = {},
        onFailure: (Throwable) -> Unit = {}
    ): Job = scope.launch(dispatcher) {
        runCatching { run(params) }
            .onSuccess { onSuccess(it) }
            .onFailure { onFailure(it) }
    }
}
