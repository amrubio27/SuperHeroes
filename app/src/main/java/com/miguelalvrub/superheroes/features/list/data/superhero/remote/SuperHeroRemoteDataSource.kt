package com.miguelalvrub.superheroes.features.list.data.superhero.remote

import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.app.left
import com.miguelalvrub.superheroes.app.right
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.api.SuperHeroApiModel
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.api.SuperHeroApiServices
import com.miguelalvrub.superheroes.features.list.data.superhero.remote.api.toModel
import com.miguelalvrub.superheroes.features.list.domain.Models
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class SuperHeroRemoteDataSource {

    private val apiServices: SuperHeroApiServices

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dam.sitehub.es/api-curso/superheroes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiServices = retrofit.create(SuperHeroApiServices::class.java)
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

}
