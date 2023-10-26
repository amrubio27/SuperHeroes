package com.miguelalvrub.superheroes.features.list.data.biography

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.data.biography.remote.BiographyRemoteDataSource
import com.miguelalvrub.superheroes.features.list.domain.BiographyRepository
import com.miguelalvrub.superheroes.features.list.domain.Models

class BiographyDataRepository(
    private val remote: BiographyRemoteDataSource
): BiographyRepository {
    override suspend fun getBiography(id:Int) : Either<ErrorApp, Models.Biography> {
        return remote.getBiography(id)
    }
}