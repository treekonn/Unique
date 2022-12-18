package com.kigya.unique.utils.async

import kotlinx.coroutines.runBlocking

typealias SuspendValueLoader<A, T> = suspend (A) -> T?

class LazyFlowSubject<A : Any, T : Any>(
    private val loader: SuspendValueLoader<A, T>
) {

    private val lazyListenersSubject = LazyListenersSubject<A, T> { arg ->
        runBlocking {
            loader.invoke(arg)
        }
    }

}