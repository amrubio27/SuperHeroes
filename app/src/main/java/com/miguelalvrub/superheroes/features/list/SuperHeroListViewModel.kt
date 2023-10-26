package com.miguelalvrub.superheroes.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.domain.GetSuperHeroesFeedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroListViewModel(
    private val getSuperHeroesFeedUseCase: GetSuperHeroesFeedUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadSuperHeroesFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            getSuperHeroesFeedUseCase.invoke().fold(
                { responseError(it) },
                { responseGetSuperHeroesFeedSuccess(it) }
            )
        }
    }

    private fun responseGetSuperHeroesFeedSuccess(it: List<GetSuperHeroesFeedUseCase.Output>) {
        _uiState.postValue(UiState(superHero = it))
    }

    private fun responseError(it: ErrorApp) {
        _uiState.postValue(UiState(errorApp = it))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val superHero: List<GetSuperHeroesFeedUseCase.Output>? = null
    )


}