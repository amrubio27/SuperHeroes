package com.miguelalvrub.superheroes.features.list.data.work.remote

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.data.ApiClient
import com.miguelalvrub.superheroes.features.list.domain.Models


class WorkRemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getWork(heroId: Int): Either<ErrorApp, Models.Work> {
        return apiClient.getWork(heroId)
    }
}