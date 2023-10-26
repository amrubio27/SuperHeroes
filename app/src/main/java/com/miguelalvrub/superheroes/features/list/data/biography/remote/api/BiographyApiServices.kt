package com.miguelalvrub.superheroes.features.list.data.biography.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BiographyApiServices {
    @GET("biography/{heroId}.json")
    fun getBiography(@Path("heroId") heroId: Int): Response<BiographyApiModel>

}