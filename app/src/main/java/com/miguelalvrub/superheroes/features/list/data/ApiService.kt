package com.miguelalvrub.superheroes.features.list.data

import com.miguelalvrub.superheroes.features.list.data.biography.remote.api.BiographyApiModel
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.api.SuperHeroApiModel
import com.miguelalvrub.superheroes.features.list.data.work.remote.api.WorkApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("heroes.json")
    suspend fun getSuperHero(): Response<List<SuperHeroApiModel>>

    @GET("work/{heroId}.json")
    suspend fun getWork(@Path("heroId") heroId: Int): Response<WorkApiModel>

    @GET("biography/{heroId}.json")
    suspend fun getBiography(@Path("heroId") heroId: Int): Response<BiographyApiModel>

}