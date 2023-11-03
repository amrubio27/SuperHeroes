package com.miguelalvrub.superheroes.features.list.data.superhero.local

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.domain.Models

interface SuperHeroLocalDataSource {
    fun saveSuperHeroes(superHeroes: List<Models.SuperHero>): Either<ErrorApp, List<Models.SuperHero>>
    fun getAllSuperHeroes(): Either<ErrorApp, List<Models.SuperHero>>
}