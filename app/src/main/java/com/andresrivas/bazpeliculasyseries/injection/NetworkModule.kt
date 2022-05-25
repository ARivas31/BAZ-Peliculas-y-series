package com.andresrivas.bazpeliculasyseries.injection

import com.andresrivas.bazpeliculasyseries.data.repository.MoviesRepositoryRemote
import com.andresrivas.bazpeliculasyseries.data.services.APIService
import com.andresrivas.bazpeliculasyseries.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesRepository(retrofit: Retrofit): MoviesRepository {
        return MoviesRepositoryRemote(retrofit.create(APIService::class.java))
    }
}