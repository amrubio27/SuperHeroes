package com.miguelalvrub.superheroes.features.list.data.work.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WorkApiServices {
    @GET("work/{heroId}.json")
    fun getWork(@Path("heroId") heroId: Int): Response<WorkApiModel>
}
