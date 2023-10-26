package com.miguelalvrub.superheroes.features.list.data.work.remote

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.app.left
import com.miguelalvrub.superheroes.app.right
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.api.SuperHeroApiModel
import com.miguelalvrub.superheroes.features.list.data.work.remote.api.WorkApiModel
import com.miguelalvrub.superheroes.features.list.data.work.remote.api.WorkApiServices
import com.miguelalvrub.superheroes.features.list.data.work.remote.api.toModel
import com.miguelalvrub.superheroes.features.list.domain.Models
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class WorkRemoteDataSource {
    private val apiServices: WorkApiServices

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dam.sitehub.es/api-curso/superheroes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiServices = retrofit.create(WorkApiServices::class.java)
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