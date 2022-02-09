package com.rezabintami.movies.view.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rezabintami.common.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(moviesUseCase: MoviesUseCase): ViewModel(){
    val moviesNowPlaying = moviesUseCase.getAllNowPlaying().asLiveData()
}