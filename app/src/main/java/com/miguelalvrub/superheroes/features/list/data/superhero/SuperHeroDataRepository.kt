package com.miguelalvrub.superheroes.features.list.data.superhero

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.features.list.data.superhero.local.XmlSuperHeroLocalDataSource
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.SuperHeroRemoteDataSource
import com.miguelalvrub.superheroes.features.list.domain.Models
import com.miguelalvrub.superheroes.features.list.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val local: XmlSuperHeroLocalDataSource,
    private val remote: SuperHeroRemoteDataSource
) : SuperHeroRepository {
    override suspend fun getSuperHeroes(): Either<ErrorApp, List<Models.SuperHero>> {
        val list = local.getAllSuperHeroes()
        return if (list.isLeft() || list.get().isEmpty()) {
            remote.getSuperHero().map { superheroes ->
                local.saveSuperHeroes(superheroes)
                superheroes
            }
        } else {
            list
        }
    }

    /*
    override suspend fun getSuperHeroes(): Either<ErrorApp, List<Models.SuperHero>> {
        return remote.getSuperHero()
    }
     */

    /**override fun getAllSuperHeroes(): Either<ErrorApp, List<SuperHero>> {
    val list = localsource.getAllSuperHeroes()
    return if (list.isLeft() || list.get().isEmpty()){
    remotesource.getSuperHeroes().map { superheroes ->
    localsource.saveSuperHeroes(superheroes)
    superheroes
    }
    } else{
    list
    }
    }*/
}