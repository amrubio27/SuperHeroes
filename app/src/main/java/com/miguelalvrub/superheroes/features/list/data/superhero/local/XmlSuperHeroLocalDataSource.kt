package com.miguelalvrub.superheroes.features.list.data.superhero.local

import android.content.Context
import com.google.gson.Gson
import com.miguelalvrub.superheroes.app.Either
import com.miguelalvrub.superheroes.app.ErrorApp
import com.miguelalvrub.superheroes.app.left
import com.miguelalvrub.superheroes.app.right
import com.miguelalvrub.superheroes.features.list.domain.Models

class XmlSuperHeroLocalDataSource(
    context: Context
): SuperHeroLocalDataSource {

    private val sharedPref = context.getSharedPreferences("superheroes", Context.MODE_PRIVATE)

    private val editor = sharedPref.edit()
    private val gson = Gson()


    override fun saveSuperHeroes(superHeroes: List<Models.SuperHero>): Either<ErrorApp, List<Models.SuperHero>> {
        return try {
            superHeroes.forEach {
                editor.putString(it.id.toString(), gson.toJson(superHeroes))
            }
            editor.apply()
            superHeroes.right()
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }

    override fun getAllSuperHeroes(): Either<ErrorApp, List<Models.SuperHero>> {
        return try {
            val superheroes: MutableList<Models.SuperHero> = mutableListOf()
            sharedPref.all.forEach { map ->
                superheroes.add(gson.fromJson(map.value as String, Models.SuperHero::class.java))
            }
            superheroes.right()
        } catch (ex: Exception) {
            ErrorApp.DataError.left()
        }
    }
}