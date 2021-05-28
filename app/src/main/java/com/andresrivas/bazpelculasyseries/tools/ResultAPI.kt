package com.andresrivas.bazpelculasyseries.tools

sealed class ResultAPI<out T: Any?> {
    class OnSuccess<out T: Any?>(val data: T) : ResultAPI<T>()
    class OnFailure(val exception: Throwable) : ResultAPI<Nothing>()
}