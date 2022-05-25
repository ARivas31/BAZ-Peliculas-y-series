package com.andresrivas.bazpeliculasyseries.common.domain

import com.andresrivas.bazpeliculasyseries.tools.ResultAPI

interface UseCase<Params, T> {
    suspend fun execute(params: Params? = null): ResultAPI<T>
}