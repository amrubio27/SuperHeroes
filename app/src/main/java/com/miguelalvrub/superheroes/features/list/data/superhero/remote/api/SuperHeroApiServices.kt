package com.miguelalvrub.superheroes.features.list.data.superhero.remote.api

import retrofit2.Response
import retrofit2.http.GET

interface SuperHeroApiServices {
    @GET("heroes.json")
    suspend fun getSuperHero(): Response<List<SuperHeroApiModel>>
}
