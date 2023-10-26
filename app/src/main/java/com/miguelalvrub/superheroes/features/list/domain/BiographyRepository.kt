package com.miguelalvrub.superheroes.features.list.domain

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp

interface BiographyRepository {
    suspend fun getBiography(id: Int): Either<ErrorApp, Models.Biography>
}