package com.miguelalvrub.superheroes.features.list.data.biography.remote.api

import com.miguelalvrub.superheroes.features.list.domain.Models

fun BiographyApiModel.toModel(): Models.Biography =
    Models.Biography(this.fullName)