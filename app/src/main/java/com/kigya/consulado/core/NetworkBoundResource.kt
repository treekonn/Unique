package com.kigya.consulado.core

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(

    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shoudFetch: (ResultType) -> Boolean = { true }

) = flow {
    val data = query().first()
    val flow = if (shoudFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchResult(fetch())
            query().map {
                Resource.Success(it)
            }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}