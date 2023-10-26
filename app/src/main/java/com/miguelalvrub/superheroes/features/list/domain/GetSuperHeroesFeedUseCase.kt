package com.miguelalvrub.superheroes.features.list.domain

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp

class GetSuperHeroesFeedUseCase(
    private val superHeroRepository: SuperHeroRepository,
    private val biographyRepository: BiographyRepository,
    private val workRepository: WorkRepository
) {
    suspend operator fun invoke(): Either<ErrorApp, List<Output>> {
        return superHeroRepository.getSuperHeroes().map { superHeroes ->
            superHeroes.map { superHero ->
                val biography = biographyRepository.getBiography(superHero.id)
                val work = workRepository.getWork(superHero.id)
                Output(superHero, biography.get(), work.get())
            }
        }
    }

    data class Output(
        val superHero: Models.SuperHero,
        val biography: Models.Biography,
        val work: Models.Work
    )
}