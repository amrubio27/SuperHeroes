package com.miguelalvrub.superheroes.features.list.data.biography.remote

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.data.ApiClient
import com.miguelalvrub.superheroes.features.list.domain.Models

class BiographyRemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getBiography(heroId: Int): Either<ErrorApp, Models.Biography> {
        return apiClient.getBiography(heroId)

    }


}
