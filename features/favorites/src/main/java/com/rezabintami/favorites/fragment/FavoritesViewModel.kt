package com.rezabintami.favorites.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rezabintami.common.domain.usecase.MoviesUseCase

class FavoritesViewModel (moviesUseCase: MoviesUseCase): ViewModel(){
    val favoritesList = moviesUseCase.getFavoriteMovies().asLiveData()
}