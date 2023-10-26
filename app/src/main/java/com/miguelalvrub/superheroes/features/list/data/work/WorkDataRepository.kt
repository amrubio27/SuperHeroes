package com.miguelalvrub.superheroes.features.list.data.work

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.data.work.remote.WorkRemoteDataSource
import com.miguelalvrub.superheroes.features.list.domain.Models
import com.miguelalvrub.superheroes.features.list.domain.WorkRepository

class WorkDataRepository(
    private val remote: WorkRemoteDataSource
): WorkRepository {
    override suspend fun getWork(id: Int): Either<ErrorApp, Models.Work> {
        return remote.getWork(id)
    }
}