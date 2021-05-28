package com.andresrivas.bazpelculasyseries.common.domain

import com.andresrivas.bazpelculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single

interface UseCase<Params, T> {
    fun execute(params: Params? = null) : Single<ResultAPI<T>>
}