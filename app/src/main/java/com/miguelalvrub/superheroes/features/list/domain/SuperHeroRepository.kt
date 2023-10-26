package com.miguelalvrub.superheroes.features.list.domain

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp

interface SuperHeroRepository {
    suspend fun getSuperHeroes(): Either<ErrorApp, List<Models.SuperHero>>
}