package com.prueba.pruebaapp.data.network

import com.prueba.pruebaapp.data.RepositoryImpl
import com.prueba.pruebaapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/upcoming")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMoviesApiService(retrofit: Retrofit): MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: MovieApiService): Repository{
        return RepositoryImpl(apiService)
    }
}