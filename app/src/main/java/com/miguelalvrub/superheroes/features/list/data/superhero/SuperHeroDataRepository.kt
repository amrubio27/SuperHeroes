package com.miguelalvrub.superheroes.features.list.data.superhero

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.SuperHeroRemoteDataSource
import com.miguelalvrub.superheroes.features.list.domain.Models
import com.miguelalvrub.superheroes.features.list.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val remote: SuperHeroRemoteDataSource
): SuperHeroRepository {
    override suspend fun getSuperHeroes() : Either<ErrorApp, List<Models.SuperHero>> {
        return remote.getSuperHero()
    }
}