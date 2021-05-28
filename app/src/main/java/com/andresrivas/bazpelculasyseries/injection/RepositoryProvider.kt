package com.andresrivas.bazpelculasyseries.injection

import com.andresrivas.bazpelculasyseries.data.repository.MoviesRepositoryRemote
import com.andresrivas.bazpelculasyseries.domain.respository.MoviesRepository

class RepositoryProvider {
    fun provideMoviesRepository() : MoviesRepository {
        return MoviesRepositoryRemote(MoviesRegistry().apiServiceFactory)
    }
}