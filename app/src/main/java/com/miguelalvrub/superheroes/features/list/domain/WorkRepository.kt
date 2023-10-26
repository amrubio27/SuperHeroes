package com.miguelalvrub.superheroes.features.list.domain

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp

interface WorkRepository {
    suspend fun getWork(id: Int): Either<ErrorApp, Models.Work>
}