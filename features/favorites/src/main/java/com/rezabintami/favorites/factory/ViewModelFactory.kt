package com.rezabintami.favorites.factory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rezabintami.common.domain.usecase.MoviesUseCase
import com.rezabintami.favorites.fragment.FavoritesViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(moviesUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}