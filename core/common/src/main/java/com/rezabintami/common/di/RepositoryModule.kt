package com.rezabintami.common.di

import com.rezabintami.common.domain.repository.IMoviesRepository
import com.rezabintami.common.data.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(moviesRepository: MoviesRepository): IMoviesRepository

}