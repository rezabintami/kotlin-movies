package com.rezabintami.common.di

import com.rezabintami.common.domain.usecase.MoviesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoritesModuleDependencies {
    fun moviesUseCase(): MoviesUseCase
}