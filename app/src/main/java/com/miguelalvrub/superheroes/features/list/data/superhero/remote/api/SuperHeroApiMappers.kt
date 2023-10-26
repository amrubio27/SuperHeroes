package com.miguelalvrub.superheroes.features.list.data.superhero.remote.api

import com.miguelalvrub.superheroes.features.list.domain.Models

fun SuperHeroApiModel.toModel(): Models.SuperHero =
    Models.SuperHero(this.id, this.name, this.images.sm)