package com.miguelalvrub.superheroes.features.list.data.superhero.remote

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.data.ApiClient
import com.miguelalvrub.superheroes.features.list.domain.Models

class SuperHeroRemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getSuperHero(): Either<ErrorApp, List<Models.SuperHero>> {
        return apiClient.getSuperHero()

    }

}
