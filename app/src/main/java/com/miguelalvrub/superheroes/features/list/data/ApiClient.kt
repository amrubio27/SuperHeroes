package com.miguelalvrub.superheroes.features.list.data

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.app.left
import com.miguelalvrub.superheroes.app.right
import com.miguelalvrub.superheroes.features.list.data.biography.remote.api.BiographyApiModel
import com.miguelalvrub.superheroes.features.list.data.biography.remote.api.toModel
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.api.SuperHeroApiModel
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.api.toModel
import com.miguelalvrub.superheroes.features.list.data.work.remote.api.WorkApiModel
import com.miguelalvrub.superheroes.features.list.data.work.remote.api.toModel
import com.miguelalvrub.superheroes.features.list.domain.Models
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ApiClient {
    private val apiServices: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dam.sitehub.es/api-curso/superheroes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiServices = retrofit.create(ApiService::class.java)
    }

    suspend fun getBiography(heroId: Int): Either<ErrorApp, Models.Biography> {

        try {
            val response: Response<BiographyApiModel> = apiServices.getBiography(heroId)

            return if (response.isSuccessful) {
                response.body()!!.toModel().right()
            } else {
                ErrorApp.NetworkError.left()
            }

        } catch (ex: TimeoutException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: UnknownHostException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: Exception) {
            return ErrorApp.UnknowError.left()
        }

    }

    suspend fun getSuperHero(): Either<ErrorApp, List<Models.SuperHero>> {
        try {

            val response: Response<List<SuperHeroApiModel>> = apiServices.getSuperHero()

            return if (response.isSuccessful) {
                response.body()!!.map {
                    it.toModel()
                }.right()

            } else {
                ErrorApp.NetworkError.left()
            }
        } catch (ex: TimeoutException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: UnknownHostException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: Exception) {
            return ErrorApp.UnknowError.left()
        }
    }

    suspend fun getWork(heroId: Int): Either<ErrorApp, Models.Work> {
        try {
            val response: Response<WorkApiModel> = apiServices.getWork(heroId)

            if (response.isSuccessful) {
                return response.body()!!.toModel().right()
            } else {
                return ErrorApp.NetworkError.left()
            }
        } catch (ex: TimeoutException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: UnknownHostException) {
            return ErrorApp.NetworkError.left()
        } catch (ex: Exception) {
            return ErrorApp.UnknowError.left()
        }
    }


}